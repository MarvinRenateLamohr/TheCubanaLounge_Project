package za.co.marvinlamohr.thecubanalounge;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SplashController implements Initializable {
    @FXML
    private Label brandLabel,loadingLabel,percentageLabel;
    @FXML
    private ImageView brandingImageView;
    @FXML
    private ProgressBar progressBar;
    @FXML
    private AnchorPane splashPane;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        File brandingFile = new File("assets/TheCubanaLounge.png");
        Image brandingImage = new Image(brandingFile.toURI().toString());
        brandingImageView.setImage(brandingImage);

        splashscreen();
    }

    public void splashscreen(){
        new Thread(){
            public void run(){
                try{
                    Thread.sleep(4000);
                    }
                catch(Exception ex){
                    ex.printStackTrace();
                    ex.getCause();
                }

                Platform.runLater(new Runnable(){
                    public void run(){
                        try{
                            Parent splashscreen = FXMLLoader.load(getClass().getResource("login.fxml"));
                            Stage userLogin = new Stage();
                            userLogin.initStyle(StageStyle.UNDECORATED);
                            userLogin.setScene(new Scene(splashscreen,600, 400));
                            userLogin.show();

                            splashPane.getScene().getWindow().hide();

                        }catch(IOException ex){
                            ex.printStackTrace();
                            ex.getCause();
                        }

                    }
                });
            }
        }.start();
    }
}
