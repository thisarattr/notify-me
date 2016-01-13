package org.thisarattr.notifyme.dao.mongo

import org.thisarattr.notifyme.dao.{ BasicCrudDAO, CounterDAO }
import org.thisarattr.notifyme.dao.exceptions.SavingFailed
import org.thisarattr.notifyme.model.LongIdModel
import play.api.libs.json.OFormat
import reactivemongo.api.DB

import scala.concurrent.{ ExecutionContext, Future }

/**
 * Created by Thisara on 10/11/15.
 */
class BasicLongCrudDAO[T <: LongIdModel[T]: OFormat](
  collectionName: String, db: DB, counterDAO: CounterDAO)(
    implicit val ctx: ExecutionContext) extends BasicCrudDAO[Long, T](collectionName, db) {

  override def save(entity: T): Future[Long] = entity.id match {
    case Some(id) => persist(entity)
    case None     => getNextId flatMap saveEntityWithNewId(entity)
  }

  private def getNextId: Future[Long] = counterDAO.getNextId(collectionName)

  private def saveEntityWithNewId(entity: T)(id: Long): Future[Long] =
    persist(entity.withNewId(Some(id)))

  private def persist(t: T) = dao.save(t) map {
    res =>
      if (res.ok)
        t.id.get
      else
        throw SavingFailed(res.errmsg.get)

  }
}
