/**
 *
 */
package org.thisarattr.notifyme.model

/**
 * @author Thisara
 *
 */
case class Notification(override val id: Long, var notifyType: Int, val ack: Boolean, val callbackUrl: String, val status: Integer, val priority: Integer, val smsData: SmsData) extends LongIdModel[Notification] {
  override def withNewId(id: Long): Notification = this.copy(id = id)
}

object test {
  //var ns: Notification = Notification(1, 1);
}

case class SmsData(val subject: String, val recipients: List[String], val template: String, val params: Map[String, String] ){
}