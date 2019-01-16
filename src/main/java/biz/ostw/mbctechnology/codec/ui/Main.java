package biz.ostw.mbctechnology.codec.ui;

import biz.ostw.fsi.translator.InputStreamSource;
import biz.ostw.mbctechnology.codec.fsi.MqiFile;
import biz.ostw.mbctechnology.codec.fsi.MqiFileDestination;
import biz.ostw.mbctechnology.codec.fsi.MqiTranslator;
import biz.ostw.mbctechnology.codec.fsi.Parameter;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Main implements Initializable {

    @FXML
    private MenuBar menuBar;

    @FXML
    private MenuItem fileOpenMenuItem;

    @FXML
    private MenuItem closeMenuItem;

    @FXML
    private MenuItem aboutMenuItem;

    @FXML
    TableView<Parameter> parameterTableView;

    @FXML
    TableColumn<Parameter, String> parameterNameTableColumn;

    @FXML
    TableColumn<Parameter, String> parameterUnitTableColumn;

    @FXML
    TableColumn<Parameter, String> parameterTargetTableColumn;

    @FXML
    TableColumn<Parameter, String> parameterLimitTableColumn;

    public void initialize(URL location, ResourceBundle resources) {

        this.parameterNameTableColumn.setEditable(false);
        this.parameterNameTableColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Parameter, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Parameter, String> param) {
                return new ReadOnlyObjectWrapper<>(param.getValue().name());
            }
        });

        this.parameterUnitTableColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Parameter, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Parameter, String> param) {
                return new ReadOnlyObjectWrapper<>(param.getValue().unit());
            }
        });

        this.parameterTargetTableColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Parameter, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Parameter, String> param) {
                return new ReadOnlyObjectWrapper<>(param.getValue().target());
            }
        });

        this.parameterLimitTableColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Parameter, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Parameter, String> param) {
                return new ReadOnlyObjectWrapper<>(param.getValue().limit());
            }
        });


        this.fileOpenMenuItem.addEventHandler(EventType.ROOT, new EventHandler<Event>() {
            @Override
            public void handle(Event event) {

                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Открыть mqi файл...");
                fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("MQI файлы", "*.mqi"));
                File file = fileChooser.showOpenDialog(Main.this.menuBar.getScene().getWindow());

                if (file != null) {
                    try {
                        MqiFile mqiFile = new MqiTranslator().translate(new InputStreamSource(new FileInputStream(file), file.toURI()), new MqiFileDestination());

                        Main.this.parameterTableView.setItems(FXCollections.observableArrayList(mqiFile.parameters()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        this.closeMenuItem.addEventHandler(EventType.ROOT, new EventHandler<Event>() {
            @Override
            public void handle(Event event) {

                Main.this.menuBar.getScene().getWindow().hide();
            }
        });

        this.aboutMenuItem.addEventHandler(EventType.ROOT, new EventHandler<Event>() {
            public void handle(Event event) {

                About.newInstance().show();
            }
        });
    }

    public static final Stage newInstance(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader();
            InputStream is = About.class.getResourceAsStream("main.fxml");
            Parent parent = loader.load(is);
            stage.setTitle("MBC Technology codec");
            Scene scene = new Scene(parent);
            stage.setScene(scene);

            return stage;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
