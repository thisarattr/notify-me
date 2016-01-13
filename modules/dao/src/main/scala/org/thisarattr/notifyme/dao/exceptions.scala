package org.thisarattr.notifyme.dao

/**
 * Created by Thisara on 10/11/15.
 */
object exceptions {
  case class SavingFailed(msg: String = "") extends RuntimeException(msg)
  case class RemoveFailed(msg: String = "") extends RuntimeException(msg)
  case class UpdateFailed(msg: String = "") extends RuntimeException(msg)
}
