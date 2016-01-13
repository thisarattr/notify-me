package org.thisarattr.notifyme.dao

import org.thisarattr.notifyme.dao
import org.thisarattr.notifyme.dao.exceptions.{ RemoveFailed, UpdateFailed }
import play.api.libs.json._
import play.modules.reactivemongo.json._
import reactivemongo.api.DB
import reactivemongo.bson.BSONObjectID
import reactivemongo.extensions.json.dao.JsonDao

import scala.concurrent.{ ExecutionContext, Future }

/**
 * Created by Thisara on 10/11/15.
 */
abstract class BasicCrudDAO[ID: Writes, T: OFormat](
  collectionName: String, val db: DB)(
    implicit ctx: ExecutionContext) extends CrudDAO[ID, T] {

  lazy val dao = JsonDao[T, BSONObjectID](db, collectionName)
  lazy val collection = dao.collection

  override def delete(id: ID): Future[Unit] =
    dao.remove(Json.obj("id" -> id))
      .map(res => if (res.hasErrors) {
        throw RemoveFailed(res.errmsg.get)
      })

  override def update(id: ID, updates: JsValue): Future[Unit] =
    dao.update(Json.obj("id" -> id), Json.obj("$set" -> updates))
      .map(res => if (res.hasErrors) {
        throw UpdateFailed(res.errmsg.get)
      })

  override def findById(id: ID): Future[Option[T]] =
    dao.findOne(Json.obj("id" -> id))

  override def findAll(): Future[List[T]] =
    dao.findAll()

}