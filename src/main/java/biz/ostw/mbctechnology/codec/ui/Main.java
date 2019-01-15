package biz.ostw.mbctechnology.codec.ui;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class Main implements Initializable {

    @FXML
    private MenuItem aboutMenuItem;

    public void initialize(URL location, ResourceBundle resources) {

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
