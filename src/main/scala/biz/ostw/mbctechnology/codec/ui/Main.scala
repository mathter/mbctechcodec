package biz.ostw.mbctechnology.codec.ui

import java.io.FileInputStream
import java.net.URL
import java.util.ResourceBundle

import biz.ostw.fsi.translator.InputStreamSource
import biz.ostw.mbctechnology.codec.fsi.{MqiFileDestination, MqiTranslator, Parameter}
import javafx.beans.property.ReadOnlyObjectWrapper
import javafx.beans.value.ObservableValue
import javafx.collections.FXCollections
import javafx.event.{Event, EventHandler, EventType}
import javafx.fxml.{FXML, FXMLLoader, Initializable}
import javafx.scene.control.{MenuBar, MenuItem, TableColumn, TableView}
import javafx.scene.{Parent, Scene}
import javafx.stage.{FileChooser, Stage}
import javafx.util.Callback

import scala.collection.JavaConverters

class Main extends Initializable {

  @FXML
  var menuBar: MenuBar = _

  @FXML
  var fileOpenMenuItem: MenuItem = _

  @FXML
  var closeMenuItem: MenuItem = _

  @FXML
  var aboutMenuItem: MenuItem = _

  @FXML
  var parameterTableView: TableView[Parameter] = _

  @FXML
  var parameterNameTableColumn: TableColumn[Parameter, String] = _

  @FXML
  var parameterUnitTableColumn: TableColumn[Parameter, String] = _

  @FXML
  var parameterTargetTableColumn: TableColumn[Parameter, String] = _

  @FXML
  var parameterLimitTableColumn: TableColumn[Parameter, String] = _

  override def initialize(location: URL, resources: ResourceBundle): Unit = {

    this.parameterTableView.setEditable(true)

    this.parameterNameTableColumn.setEditable(true)
    //            this.parameterNameTableColumn.setCellFactory(TextFieldTableCell.fo)
    this.parameterNameTableColumn.setOnEditCommit(new EventHandler[TableColumn.CellEditEvent[Parameter, String]]() {
      override def handle(event: TableColumn.CellEditEvent[Parameter, String]): Unit = {
        event.getRowValue.name_$eq(event.getNewValue)
      }
    })
    this.parameterNameTableColumn.setCellValueFactory(new Callback[TableColumn.CellDataFeatures[Parameter, String], ObservableValue[String]]() {
      override def call(param: TableColumn.CellDataFeatures[Parameter, String]) = new ReadOnlyObjectWrapper[String](param.getValue.name)
    })

    this.fileOpenMenuItem.addEventHandler(EventType.ROOT, new EventHandler[Event]() {

      @Override def handle(event: Event): Unit = {

        Option(new FileChooser)
          .map(d => {
            d.setTitle("Открыть mqi файл...")
            d.getExtensionFilters.add(new FileChooser.ExtensionFilter("MQI файлы", "*.mqi"))
            d.showOpenDialog(Main.this.menuBar.getScene.getWindow)
          })
          .map(file => {
            new MqiTranslator().translate(new InputStreamSource(new FileInputStream(file), file.toURI), new MqiFileDestination)
          })
          .map(mqiFile => {
            Main.this.parameterTableView.setItems(FXCollections.observableArrayList(JavaConverters.asJavaCollection(mqiFile.parameters)))
          })
      }
    })

    this.closeMenuItem.addEventHandler(EventType.ROOT, new EventHandler[Event]() {
      override def handle(event: Event): Unit = {
        Main.this.menuBar.getScene.getWindow.hide()
      }
    })

    this.aboutMenuItem.addEventHandler(EventType.ROOT, new EventHandler[Event]() {
      override def handle(event: Event): Unit = {

        About().show()
      }
    })
  }
}

object Main {

  def apply(stage: Stage): Stage =
    try {
      val loader = new FXMLLoader
      val is = classOf[Main].getResourceAsStream("main.fxml")
      val parent: Parent = loader.load(is)
      stage.setTitle("MBC Technology codec")
      val scene = new Scene(parent)
      stage.setScene(scene)
      stage
    } catch {
      case e: Exception =>
        throw new RuntimeException(e)
    }
}