package biz.ostw.mbctechnology.codec.ui

import javafx.event.Event
import javafx.fxml.{FXML, FXMLLoader}
import javafx.scene.layout.AnchorPane
import javafx.scene.{Node, Parent, Scene}
import javafx.stage.{Modality, Stage, StageStyle}

class About {

  @FXML
  var ap: AnchorPane = _

  @FXML
  def onCloseClick(event: Event): Unit = {
    event.getSource.asInstanceOf[Node].getScene.getWindow.hide()
  }
}

object About {
  def apply(unit: Unit): Stage = {
    val loader = new FXMLLoader
    val is = classOf[About].getResourceAsStream("about.fxml")
    val parent: Parent = loader.load(is)
    val stage = new Stage
    stage.setTitle("About MBC Technology codec")
    val scene = new Scene(parent)
    stage.initModality(Modality.APPLICATION_MODAL)
    stage.initStyle(StageStyle.UNDECORATED)
    stage.setScene(scene)
    stage
  }
}
