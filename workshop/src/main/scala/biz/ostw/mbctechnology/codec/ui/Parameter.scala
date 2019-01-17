package biz.ostw.mbctechnology.codec.ui

trait Parameter[T] {

  def name(): String

  def name(value: String): Unit

  def desc(): String

  def desc(value: String): Unit

  def value(): T

  def value(value: T): Unit
}
