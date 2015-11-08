/**
 *
 */
package org.thisarattr.notifyme.model

import java.time.ZonedDateTime

/**
 * @author Thisara
 *
 */
trait IdModel[ID, T] {
  def id: ID
  def withNewId(id: ID): T
  
  val createdAt: ZonedDateTime = ZonedDateTime.now()
  var updatedAt: Option[ZonedDateTime] = None

}