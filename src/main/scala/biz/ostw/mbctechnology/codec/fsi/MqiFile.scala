package biz.ostw.mbctechnology.codec.fsi

import biz.ostw.fsi.xml.{Context, DocumentPart, ElementPart}

class MqiFile(ctx: Context = new Context) extends DocumentPart {

  def version(): String = {
    this.getByType[ElementPart].filter(_.name() == "mqifile").map(_.attribute("version")).last
  }

  def version(value: String): Unit = {
    this.getByType[ElementPart].filter(_.name() == "mqifile").map(_.attribute("version", value))
  }

  def datatype(): String = {
    this.getByType[ElementPart].filter(_.name() == "mqifile").map(_.attribute("datatype")).last
  }

  def datatype(value: String): Unit = {
    this.getByType[ElementPart].filter(_.name() == "mqifile").map(_.attribute("datatype", value))
  }

  def lot(): String = {
    this.getByType[ElementPart].filter(_.name() == "mqifile").map(_.attribute("lot")).last
  }

  def lot(value: String): Unit = {
    this.getByType[ElementPart].filter(_.name() == "mqifile").map(_.attribute("lot", value))
  }

  def validdate(): String = {
    this.getByType[ElementPart].filter(_.name() == "mqifile").map(_.attribute("validdate")).last
  }

  def validdate(value: String): Unit = {
    this.getByType[ElementPart].filter(_.name() == "mqifile").map(_.attribute("validdate", value))
  }
}
