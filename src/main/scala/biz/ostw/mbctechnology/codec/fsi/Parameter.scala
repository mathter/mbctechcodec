package biz.ostw.mbctechnology.codec.fsi

case class Parameter(val name: String, val unit: String, val target: String, val limit: String) {

  def apply(name: String, unit: String, target: String, limit: String): Parameter
    = new Parameter(name, unit, target, limit)
}
