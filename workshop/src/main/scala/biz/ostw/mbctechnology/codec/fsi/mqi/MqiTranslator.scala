package biz.ostw.mbctechnology.codec.fsi.mqi

import java.util.zip.{GZIPInputStream, GZIPOutputStream}

import biz.ostw.fsi.translator._
import biz.ostw.fsi.xml.{DocumentPartDestination, XmlTranslator}

class MqiTranslator extends XmlTranslator {

  override def translate[S, D](source: Source[S], destination: Destination[D]): D = {

    (if (source.isInstanceOf[InputStreamSource]) {
      if (!destination.isInstanceOf[MqiFileDestination]) {
        throw new InvalidDestinationException(destination)
      }

      (if (source.asInstanceOf[InputStreamSource].inputStream.isInstanceOf[GZIPInputStream]) {
        Option(source.asInstanceOf[InputStreamSource])
      } else {
        Option(source.asInstanceOf[InputStreamSource]).map(iss => {
          new InputStreamSource(new GZIPInputStream(iss.inputStream), iss.uri)
        })
      }).map(super.translate(_, new DocumentPartDestination)).map(_.childs.foldLeft(new MqiFile())(_.add(_))).get
    } else {
      if (destination.isInstanceOf[OutputStreamDestination]) {
        Option(destination.asInstanceOf[OutputStreamDestination].outputStream).map(os => {
          if (os.isInstanceOf[GZIPInputStream]) {
            super.translate(source, destination)
          } else {
            val gzipOs = new GZIPOutputStream(os)
            val result = super.translate(source, new OutputStreamDestination(gzipOs, destination.uri))

            gzipOs.close()

            result
          }
        })
      } else {
        throw new InvalidDestinationException(destination)
      }
    }).asInstanceOf[D]
  }
}
