package org.thisarattr.notifyme.dao

import scala.concurrent.Future
/**
 * Created by Thisara on 10/11/15.
 */
trait CounterDAO {
  def getNextId(counter: String): Future[Long]
}
