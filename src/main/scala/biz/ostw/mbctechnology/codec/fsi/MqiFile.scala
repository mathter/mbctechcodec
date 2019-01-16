package biz.ostw.mbctechnology.codec.fsi

import biz.ostw.fsi.xml.{Context, DocumentPart, ElementPart}

import scala.collection.mutable.ArrayBuffer

class MqiFile(ctx: Context = new Context) extends DocumentPart {

  def version(): String = {
    this.mqfileElement.map(_.attribute("version")).orNull
  }

  def version(value: String): Unit = {
    this.mqfileElement.map(_.attribute("version", value))
  }

  def datatype(): String = {
    this.mqfileElement.map(_.attribute("datatype")).orNull
  }

  def datatype(value: String): Unit = {
    this.mqfileElement.map(_.attribute("datatype", value))
  }

  def lot(): String = {
    this.mqfileElement.map(_.attribute("lot")).orNull
  }

  def lot(value: String): Unit = {
    this.mqfileElement.map(_.attribute("lot", value))
  }

  def validdate(): String = {
    this.mqfileElement.map(_.attribute("validdate")).orNull
  }

  def validdate(value: String): Unit = {
    this.mqfileElement.map(_.attribute("validdate", value))
  }

  def level(): String = {
    this.controllElement().map(_.attribute("level")).orNull
  }

  def level(value: String): Unit = {
    this.controllElement().map(_.attribute("level", value))
  }

  def machineNames(): Array[String] = {
    Option(this.machineElement.map(_.attribute("name")).orNull).map(_.split(",")).getOrElse(Array[String]())
  }

  def machineNames(value: Array[String]): Unit = {
    if (value != null && value.length > 0) {
      this.machineElement.map(_.attribute("name", value.drop(1).fold(value.head)(_ + "," + _.trim)))
    }
    else {
      this.machineElement.map(_.attribute("name", null))
    }
  }

  def parameters(): Array[Parameter] = {
    null
  }

  private def mqfileElement(): Option[ElementPart] = {
    this.getByType[ElementPart].find(_.name() == "mqifile")
  }

  private def controllElement(): Option[ElementPart] = {
    this.mqfileElement.map(_.getByType[ElementPart].find(_.name == "control").orNull)
  }

  private def machineElement(): Option[ElementPart] = {
    this.controllElement().map(_.getByType[ElementPart].find(_.name == "machine").orNull)
  }
}
