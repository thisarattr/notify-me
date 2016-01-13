package org.thisarattr.notifyme.dao

import play.api.libs.json.JsValue

import scala.concurrent.Future
import scala.util.Try

trait CrudDAO[ID, T] {

  def findById(id: ID): Future[Option[T]]

  def save(entity: T): Future[ID]

  def delete(id: ID): Future[Unit]

  def update(id: ID, updates: JsValue): Future[Unit]

  def findAll(): Future[List[T]]
}