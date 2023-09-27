package za.co.marvinlamohr.thecubanalounge;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class TheCubanaLoungeApplication extends Application {
    @Override
    public void start(Stage splash) throws IOException {
        Parent splashscreen = FXMLLoader.load(getClass().getResource("splash.fxml"));
        splash.initStyle(StageStyle.UNDECORATED);
        splash.setScene(new Scene(splashscreen,600, 400));
        splash.show();
    }
    public static void main(String[] args) {
        launch();
    }
}
