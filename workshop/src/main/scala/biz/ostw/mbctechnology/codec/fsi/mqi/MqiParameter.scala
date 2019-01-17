package biz.ostw.mbctechnology.codec.fsi.mqi

import biz.ostw.fsi.xml.AttributePart
import biz.ostw.mbctechnology.codec.ui.Parameter

class MqiParameter(var _name: String, protected val part: AttributePart) extends Parameter[String] {

  override def name(): String = _name

  override def name(value: String): Unit = throw new UnsupportedOperationException

  override def desc(): String = ""

  override def desc(value: String): Unit = {}

  override def value(): String = this.part.value

  override def value(value: String): Unit = this.part.value(value)
}
