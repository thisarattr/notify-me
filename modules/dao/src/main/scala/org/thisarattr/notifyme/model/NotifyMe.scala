package org.thisarattr.notifyme.model

object NotifyMe {

  def main(args: Array[String]) {
    println(apply(layout, 10))
  }

  def apply(f: (Int, String )=> String, v: Int) = f(v, " Test")

  def layout[Int](x: Int, y: String) = "[" + x.toString() + y + "]"
  
  val multiplier = (i:Int) => i * 10

}