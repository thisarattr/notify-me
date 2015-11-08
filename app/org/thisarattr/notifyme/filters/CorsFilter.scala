package org.thisarattr.notifyme.filters

import play.api.mvc.Filter
import play.api.mvc.RequestHeader
import scala.concurrent.Future
import play.api.mvc.Result
import play.api.libs.concurrent.Execution.Implicits.defaultContext

object CorsFilter extends Filter {

  val headers = List(
    "Access-Control-Allow-Origin" -> "*",
    "Access-Control-Allow-Headers" -> "Origin, X-Requested-With, Content-Type, Accept, Authorization,X-Auth-Token, x-json, x-prototype-version")
  val optionsHeaders = List(
    "Access-Control-Allow-Origin" -> "*",
    "Access-Control-Allow-Methods" -> "POST, GET, OPTIONS, PUT, DELETE, PATCH",
    "Access-Control-Max-Age" -> "3600",
    "Access-Control-Allow-Headers" -> "Origin, X-Requested-With, Content-Type, Accept, Authorization, X-Auth-Token, x-json, x-prototype-version",
    "Access-Control-Allow-Credentials" -> "true")

  def apply(nextFilter: (RequestHeader) => Future[Result])(requestHeader: RequestHeader): Future[Result] = {
    nextFilter(requestHeader).map { result => result.withHeaders(headers: _*) }
  }

}