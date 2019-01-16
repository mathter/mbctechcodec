package biz.ostw.mbctechnology.codec.fsi

case class ParameterName(val value: String) {

  def apply(value: String): ParameterName = new ParameterName(value)
}
