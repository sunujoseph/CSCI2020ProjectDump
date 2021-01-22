import java.io._

class q3 {
val x = $("x")
  println(x)
  val y = $("y")
  println(y)


}
class $(val text2d: Array[Array[Char]]) {
  val x = $("x")
  val y = $("y")
  val x2 = $("2")
  val y2 = $("")

  def ^(that: $)  = text2d + x2
  def +(that: $) = x + y + text2d
  override def toString = x2 + text2d + x + y
}
object $ {
  def apply(x: String) = toString
  println(toString)
}
//tried to create this according to your code given.
//tried to combine these but could make sure if it works because my complier is buggy and e haven't had enough time for
//to learn scala properly. I know what we are combining several val into string