package biz.ostw.mbctechnology.codec

import javafx.application.Application
import javafx.stage.Stage

class App extends Application {

  override def start(stage: Stage): Unit = {
    ui.Main(stage).show()
  }

  def main(args: Array[String]): Unit = {
    Application.launch(classOf[App], args: _*)
  }
}
