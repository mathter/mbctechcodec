package biz.ostw.mbctechnology.codec.fsi.mqi

import biz.ostw.fsi.xml.{AttributePart, Context, DocumentPart, ElementPart}
import biz.ostw.mbctechnology.codec.ui.{OrdinalParameter, Parameter}

import scala.collection.mutable.ArrayBuffer

class MqiFile(ctx: Context = new Context) extends DocumentPart {

  def versionPart(): Option[AttributePart] = {
    this.mqfileElement().map(_.attributes.filter("version" == _.name).headOption).head
  }

  def version(): String = {
    this.versionPart.map(_.value).orNull
  }

  def version(value: String): Unit = {
    this.versionPart.map(_.value(value))
  }

  def datatypePart(): Option[AttributePart] = {
    this.mqfileElement().map(_.attributes.filter("datatype" == _.name).headOption).head
  }

  def datatype(): String = {
    this.datatypePart.map(_.value).orNull
  }

  def datatype(value: String): Unit = {
    this.datatypePart.map(_.value(value))
  }

  def lotPart(): Option[AttributePart] = {
    this.mqfileElement().map(_.attributes.filter("lot" == _.name).headOption).head
  }

  def lot(): String = {
    this.lotPart.map(_.value).orNull
  }

  def lot(value: String): Unit = {
    this.lotPart.map(_.value(value))
  }

  def validdatePart(): Option[AttributePart] = {
    this.mqfileElement().map(_.attributes.filter("validdate" == _.name).headOption).head
  }

  def validdate(): String = {
    this.validdatePart.map(_.value).orNull
  }

  def validdate(value: String): Unit = {
    this.validdatePart.map(_.value(value))
  }

  def levelPart(): Option[AttributePart] = {
    this.controllElement.map(_.attributes.filter("level" == _.name).headOption).head
  }

  def level(): String = {
    this.levelPart().map(_.value).orNull
  }

  def level(value: String): Unit = {
    this.levelPart().map(_.value(value))
  }

  def machineNamesPart(): Option[AttributePart] = {
    this.machineElement().map(_.attributes.filter("name" == _.name).headOption).head
  }

  def machineNames(): Array[String] = {

    this.machineNamesPart().map(_.value).map(_.split(",")).getOrElse(Array[String]())
  }

  def machineNames(value: Array[String]): Unit = {
    if (value != null && value.length > 0) {
      this.machineNamesPart().map(_.value(value.drop(1).fold(value.head)(_ + "," + _.trim)))
    }
    else {
      this.machineElement.map(_.attribute("name", null))
    }
  }

  def systemParameters(): Array[Parameter[String]] = {
    val parameters = ArrayBuffer[Parameter[String]]()

    this.versionPart().map(parameters += new MqiParameter("Version", _))
    this.datatypePart().map(parameters += new MqiParameter("Datatype", _))
    this.lotPart().map(parameters += new MqiParameter("Lot", _))
    this.validdatePart().map(parameters += new MqiParameter("Valid date", _))
    this.levelPart().map(parameters += new MqiParameter("Level", _))
    this.machineNamesPart().map(parameters += new MqiParameter("Machine names", _))

    parameters.toArray
  }

  def parameters(): Array[OrdinalParameter[String]] = {
    this.machineElement().map(_.getByType[ElementPart].filter(_.name == "para").foldLeft(new ArrayBuffer[OrdinalParameter[String]])((a, e) => {
      a += new MqiOrdinalParameter(e)
    })).getOrElse(new ArrayBuffer[OrdinalParameter[String]]).toArray
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
