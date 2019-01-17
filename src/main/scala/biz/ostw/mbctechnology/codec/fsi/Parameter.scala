package biz.ostw.mbctechnology.codec.fsi

case class Parameter(var name: String, var unit: String, var target: String, var limit: String) {

  def apply(name: String, unit: String, target: String, limit: String): Parameter
    = new Parameter(name, unit, target, limit)
}
