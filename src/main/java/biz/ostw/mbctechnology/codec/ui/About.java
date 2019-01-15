package biz.ostw.mbctechnology.codec.ui;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.InputStream;

public class About {

    @FXML
    private AnchorPane ap;

    @FXML
    public void onCloseClick(Event event) {

        ((Node)event.getSource()).getScene().getWindow().hide();
    }

    public static final Stage newInstance() {
        try {
            FXMLLoader loader = new FXMLLoader();
            InputStream is = About.class.getResourceAsStream("about.fxml");
            Parent parent = loader.load(is);
            Stage stage = new Stage();
            stage.setTitle("About MBC Technology codec");
            Scene scene = new Scene(parent);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);

            return stage;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
