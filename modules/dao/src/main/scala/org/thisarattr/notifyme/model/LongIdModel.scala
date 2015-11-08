/**
 *
 */
package org.thisarattr.notifyme.model

import java.time.ZonedDateTime

/**
 * @author Thisara
 *
 */
trait LongIdModel[T] extends IdModel[Long, T] {
  override val id: Long
 
}