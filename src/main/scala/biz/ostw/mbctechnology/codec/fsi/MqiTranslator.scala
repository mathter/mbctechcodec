package biz.ostw.mbctechnology.codec.fsi

import java.util.zip.GZIPInputStream

import biz.ostw.fsi.translator._
import biz.ostw.fsi.xml.{DocumentPartDestination, XmlTranslator}

class MqiTranslator extends XmlTranslator {

  override def translate[S, D](source: Source[S], destination: Destination[D]): D = {

    (if (source.isInstanceOf[InputStreamSource]) {
      if (!destination.isInstanceOf[MqiFileDestination]) {
        throw new InvalidDestinationException(destination)
      }

      (if (source.isInstanceOf[GZIPInputStream]) {
        Option(source.asInstanceOf[InputStreamSource])
      } else {
        Option(source.asInstanceOf[InputStreamSource]).map(iss => {
          new InputStreamSource(new GZIPInputStream(iss.inputStream), iss.uri)
        })
      }).map(super.translate(_, new DocumentPartDestination)).map(_.childs.foldLeft(new MqiFile())(_.add(_))).get
    } else {
      super.translate(source, destination)
    }).asInstanceOf[D]
  }
}
