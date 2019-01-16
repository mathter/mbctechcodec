package biz.ostw.mbctechnology.codec;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.MenuBar;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {

        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        biz.ostw.mbctechnology.codec.ui.Main.newInstance(stage).show();
    }
}
