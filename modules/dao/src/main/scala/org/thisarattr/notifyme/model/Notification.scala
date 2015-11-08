/**
 *
 */
package org.thisarattr.notifyme.model

/**
 * @author Thisara
 *
 */
case class Notification(override val id: Long, var notifyType: Int) extends LongIdModel[Notification] {
  override def withNewId(id: Long): Notification = this.copy(id = id)
}

object test {
  var ns: Notification = Notification(1, 1);
}