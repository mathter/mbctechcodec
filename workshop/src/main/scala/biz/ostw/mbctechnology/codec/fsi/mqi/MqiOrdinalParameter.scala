package biz.ostw.mbctechnology.codec.fsi.mqi

import biz.ostw.fsi.xml.ElementPart
import biz.ostw.mbctechnology.codec.ui.OrdinalParameter

class MqiOrdinalParameter(private val part: ElementPart) extends OrdinalParameter[String] {

  override def limit(): String = this.part.attribute("limit")

  override def limit(value: String): Unit = this.part.attribute("limit", value)

  override def name(): String = this.part.attribute("name")

  override def name(value: String): Unit = this.part.attribute("name", value)

  override def desc(): String = ""

  override def desc(value: String): Unit = {}

  override def value(): String = this.part.attribute("target")

  override def value(value: String): Unit = this.part.attribute("target", value)

  override def unit(): String = this.part.attribute("unit")

  override def unit(value: String): Unit = this.part.attribute("unit", value)
}
