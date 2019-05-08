package biz.ostw.mbctechnology.codec.ui

import java.io.{File, FileInputStream}
import java.net.URL
import java.util.ResourceBundle

import biz.ostw.fsi.translator.{InputStreamSource, OutputStreamDestination, PartSource}
import biz.ostw.mbctechnology.codec.fsi.mqi.{MqiFile, MqiFileDestination, MqiTranslator}
import javafx.beans.property.ReadOnlyObjectWrapper
import javafx.beans.value.ObservableValue
import javafx.collections.FXCollections
import javafx.event.{Event, EventHandler, EventType}
import javafx.fxml.{FXML, FXMLLoader, Initializable}
import javafx.scene.control.Alert.AlertType
import javafx.scene.control._
import javafx.scene.control.cell.TextFieldTableCell
import javafx.scene.image.Image
import javafx.scene.layout.Pane
import javafx.scene.{Parent, Scene}
import javafx.stage.{FileChooser, Stage, WindowEvent}
import javafx.util.Callback

import scala.collection.JavaConverters

class Main extends Initializable {

  @FXML
  var menuBar: MenuBar = _

  @FXML
  var fileOpenMenuItem: MenuItem = _

  @FXML
  var fileSaveMenuItem: MenuItem = _

  @FXML
  var fileSaveAsMenuItem: MenuItem = _

  @FXML
  var closeMenuItem: MenuItem = _

  @FXML
  var aboutMenuItem: MenuItem = _

  @FXML
  var parameterTableView: TableView[OrdinalParameter[String]] = _

  @FXML
  var parameterNameTableColumn: TableColumn[OrdinalParameter[String], String] = _

  @FXML
  var parameterUnitTableColumn: TableColumn[OrdinalParameter[String], String] = _

  @FXML
  var parameterTargetTableColumn: TableColumn[OrdinalParameter[String], String] = _

  @FXML
  var parameterLimitTableColumn: TableColumn[OrdinalParameter[String], String] = _

  @FXML
  var systemPropertyTable: TableView[Parameter[String]] = _

  @FXML
  var systemPropertyNameColumn: TableColumn[Parameter[String], String] = _

  @FXML
  var systemPropertyValueColumn: TableColumn[Parameter[String], String] = _

  @FXML
  var splitPane: SplitPane = _

  private var mqiFile: MqiFile = _

  private var path: File = _

  private var isChanged = false

  override def initialize(location: URL, resources: ResourceBundle): Unit = {

    this.initMenu()
    this.initParameterTableView()
  }

  private def initParameterTableView(): Unit = {
    this.parameterTableView.setEditable(true)

    this.parameterNameTableColumn.setEditable(true)
    this.parameterNameTableColumn.setCellFactory(TextFieldTableCell.forTableColumn())
    this.parameterNameTableColumn.setOnEditCommit(new EventHandler[TableColumn.CellEditEvent[OrdinalParameter[String], String]]() {
      override def handle(event: TableColumn.CellEditEvent[OrdinalParameter[String], String]): Unit = {
        event.getRowValue.name(event.getNewValue)
        Main.this.isChanged = true
      }
    })
    this.parameterNameTableColumn.setCellValueFactory(new Callback[TableColumn.CellDataFeatures[OrdinalParameter[String], String], ObservableValue[String]]() {
      override def call(param: TableColumn.CellDataFeatures[OrdinalParameter[String], String]) = new ReadOnlyObjectWrapper[String](param.getValue.name)
    })


    this.parameterUnitTableColumn.setEditable(true)
    this.parameterUnitTableColumn.setCellFactory(TextFieldTableCell.forTableColumn())
    this.parameterUnitTableColumn.setOnEditCommit(new EventHandler[TableColumn.CellEditEvent[OrdinalParameter[String], String]]() {
      override def handle(event: TableColumn.CellEditEvent[OrdinalParameter[String], String]): Unit = {
        event.getRowValue.unit(event.getNewValue)
        Main.this.isChanged = true
      }
    })
    this.parameterUnitTableColumn.setCellValueFactory(new Callback[TableColumn.CellDataFeatures[OrdinalParameter[String], String], ObservableValue[String]]() {
      override def call(param: TableColumn.CellDataFeatures[OrdinalParameter[String], String]) = new ReadOnlyObjectWrapper[String](param.getValue.unit)
    })

    this.parameterTargetTableColumn.setEditable(true)
    this.parameterTargetTableColumn.setCellFactory(TextFieldTableCell.forTableColumn())
    this.parameterTargetTableColumn.setOnEditCommit(new EventHandler[TableColumn.CellEditEvent[OrdinalParameter[String], String]]() {
      override def handle(event: TableColumn.CellEditEvent[OrdinalParameter[String], String]): Unit = {
        event.getRowValue.value(event.getNewValue)
        Main.this.isChanged = true
      }
    })
    this.parameterTargetTableColumn.setCellValueFactory(new Callback[TableColumn.CellDataFeatures[OrdinalParameter[String], String], ObservableValue[String]]() {
      override def call(param: TableColumn.CellDataFeatures[OrdinalParameter[String], String]) = new ReadOnlyObjectWrapper[String](param.getValue.value)
    })

    this.parameterLimitTableColumn.setEditable(true)
    this.parameterLimitTableColumn.setCellFactory(TextFieldTableCell.forTableColumn())
    this.parameterLimitTableColumn.setOnEditCommit(new EventHandler[TableColumn.CellEditEvent[OrdinalParameter[String], String]]() {
      override def handle(event: TableColumn.CellEditEvent[OrdinalParameter[String], String]): Unit = {
        event.getRowValue.limit(event.getNewValue)
        Main.this.isChanged = true
      }
    })
    this.parameterLimitTableColumn.setCellValueFactory(new Callback[TableColumn.CellDataFeatures[OrdinalParameter[String], String], ObservableValue[String]]() {
      override def call(param: TableColumn.CellDataFeatures[OrdinalParameter[String], String]) = new ReadOnlyObjectWrapper[String](param.getValue.limit)
    })

    this.systemPropertyTable.setEditable(true)

    this.systemPropertyNameColumn.setCellFactory(TextFieldTableCell.forTableColumn())
    this.systemPropertyNameColumn.setCellValueFactory(new Callback[TableColumn.CellDataFeatures[Parameter[String], String], ObservableValue[String]]() {
      override def call(param: TableColumn.CellDataFeatures[Parameter[String], String]) = new ReadOnlyObjectWrapper[String](param.getValue.name)
    })

    this.systemPropertyValueColumn.setEditable(true)
    1
    this.systemPropertyValueColumn.setCellFactory(TextFieldTableCell.forTableColumn())
    this.systemPropertyValueColumn.setCellValueFactory(new Callback[TableColumn.CellDataFeatures[Parameter[String], String], ObservableValue[String]]() {
      override def call(param: TableColumn.CellDataFeatures[Parameter[String], String]) = new ReadOnlyObjectWrapper[String](param.getValue.value)
    })
    this.systemPropertyValueColumn.setOnEditCommit(new EventHandler[TableColumn.CellEditEvent[Parameter[String], String]]() {
      override def handle(event: TableColumn.CellEditEvent[Parameter[String], String]): Unit = {
        event.getRowValue.value(event.getNewValue)
        Main.this.isChanged = true
      }
    })

    this.splitPane.prefHeightProperty().bind(this.splitPane.getParent.asInstanceOf[Pane].heightProperty())
    this.splitPane.prefWidthProperty().bind(this.splitPane.getParent.asInstanceOf[Pane].widthProperty())
  }

  private def initMenu(): Unit = {
    this.fileOpenMenuItem.addEventHandler(EventType.ROOT, new EventHandler[Event]() {

      @Override def handle(event: Event): Unit = {

        Option(new FileChooser)
          .map(d => {
            d.setTitle("Открыть mqi файл...")
            d.getExtensionFilters.add(new FileChooser.ExtensionFilter("MQI файлы", "*.mqi"))
            d.showOpenDialog(Main.this.menuBar.getScene.getWindow)
          })
          .map(file => {
            Main.this.mqiFile = new MqiTranslator().translate(new InputStreamSource(new FileInputStream(file), file.toURI), new MqiFileDestination)
            Main.this.path = file
            Main.this.mqiFile
          })
          .map(mqiFile => {
            Main.this.parameterTableView.setItems(FXCollections.observableArrayList(JavaConverters.asJavaCollection(mqiFile.parameters)))
            Main.this.systemPropertyTable.setItems(FXCollections.observableArrayList(JavaConverters.asJavaCollection(mqiFile.systemParameters())))
          })
      }
    })

    this.fileSaveMenuItem.addEventHandler(EventType.ROOT, new EventHandler[Event] {
      override def handle(event: Event): Unit = {
        Main.this.save("Сохранить", Main.this.path.getParentFile, Main.this.path.getName, (file) => {
          Main.this.path = file
          Main.this.isChanged = false
        })
      }
    })

    this.fileSaveAsMenuItem.addEventHandler(EventType.ROOT, new EventHandler[Event] {
      override def handle(event: Event): Unit = {
        Main.this.save("Сохранить как ...", null, null, (file) => {
          Main.this.path = file
          Main.this.isChanged = false
        })
      }
    })

    this.closeMenuItem.addEventHandler(EventType.ROOT, new EventHandler[Event]() {
      override def handle(event: Event): Unit = {

        if (Main.this.isChanged) {
          val alert = new Alert(AlertType.CONFIRMATION, "Файл " + Main.this.path.getName + " был изменен. Сохранить изменения?")
          alert.showAndWait()

          if (alert.getResult == ButtonType.OK) {
            Main.this.save("Сохранить", Main.this.path.getParentFile, Main.this.path.getName, null)
          }
        }

        Main.this.menuBar.getScene.getWindow.hide()
      }
    })

    this.aboutMenuItem.addEventHandler(EventType.ROOT, new EventHandler[Event]() {
      override def handle(event: Event): Unit = {

        About().show()
      }
    })
  }

  private def save(title: String, initialParent: File, initialFileName: String, after: (File) => Unit): Unit = {
    Option(new FileChooser)
      .map(d => {
        d.setTitle(title)
        d.getExtensionFilters.add(new FileChooser.ExtensionFilter("MQI файлы", "*.mqi"))
        Option(initialParent).foreach(d.setInitialDirectory(_))
        Option(initialFileName).foreach(d.setInitialFileName(_))
        d.showSaveDialog(Main.this.menuBar.getScene.getWindow)
      })
      .map(file => {
        if (file != null) {
          if (after != null) {
            after(file)
          }
          new OutputStreamDestination(file)
        } else {
          null
        }
      })
      .map(osd => {
        if (osd != null) {
          new MqiTranslator().translate(new PartSource(Main.this.mqiFile), osd)
          osd.outputStream.flush
          osd.outputStream.close
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
      stage.getIcons.add(new Image(classOf[Main].getResourceAsStream("main.png")))

      val scene = new Scene(parent)
      stage.setScene(scene)
      stage
    } catch {
      case e: Exception =>
        throw new RuntimeException(e)
    }
}