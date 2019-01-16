package biz.ostw.mbctechnology.codec.fsi

case class Unit(val value: String) {

  def apply(value: String): Unit = new Unit(value);
}
