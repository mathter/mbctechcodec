package biz.ostw.mbctechnology.codec

import javafx.application.Application
import javafx.stage.Stage

class Main extends Application {

  override def start(stage: Stage): Unit = {
    ui.Main(stage).show()
  }
}

object MainA {

  def main(args: Array[String]): Unit = {
    Application.launch(classOf[Main], args: _*)
  }
}
