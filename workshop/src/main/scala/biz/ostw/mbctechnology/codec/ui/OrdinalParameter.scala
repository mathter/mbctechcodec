package biz.ostw.mbctechnology.codec.ui

trait OrdinalParameter[T] extends Parameter[T] {

  def limit(): T

  def limit(value: T): Unit

  def unit(): String

  def unit(value: String): Unit
}
