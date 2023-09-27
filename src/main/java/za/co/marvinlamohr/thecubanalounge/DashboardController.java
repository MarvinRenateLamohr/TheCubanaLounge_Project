package za.co.marvinlamohr.thecubanalounge;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    @FXML
    private Label brandLabel, userLabel;

    @FXML
    private ImageView brandingImageView, userImageView;

    @FXML
    private Button employeeManagementButton, inventoryManagementButton,pointOfSalesButton,reservationManagementButton,loyaltyRewardsButton,logoutButton;

    @FXML
    private AnchorPane dashboardPane;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        File brandingFile = new File("assets/TheCubanaLounge.png");
        Image brandingImage = new Image(brandingFile.toURI().toString());
        brandingImageView.setImage(brandingImage);

        activeUser();
    }

    public void activeUser(){
        userLabel.setText(Employee.employeeActive);
    }

    public void employeeManagementButtonOnAction(ActionEvent event){

        try{
            Parent splashscreen = FXMLLoader.load(getClass().getResource("employeemanagement.fxml"));
            Stage employeeManagement = new Stage();
            employeeManagement.initStyle(StageStyle.UNDECORATED);
            employeeManagement.setScene(new Scene(splashscreen,1120, 620));
            employeeManagement.show();
        }
        catch(Exception ex){
            ex.printStackTrace();
            ex.getCause();
        }
        dashboardPane.getScene().getWindow().hide();
    }

    public void pointOfSalesButtonOnAction(ActionEvent event){

        try{
            Parent splashscreen = FXMLLoader.load(getClass().getResource("pointofsales.fxml"));
            Stage pointOfSales = new Stage();
            pointOfSales.initStyle(StageStyle.UNDECORATED);
            pointOfSales.setScene(new Scene(splashscreen,1120, 690));
            pointOfSales.show();
        }
        catch(Exception ex){
            ex.printStackTrace();
            ex.getCause();
        }
        dashboardPane.getScene().getWindow().hide();
    }

    public void inventoryManagementButtonOnAction(ActionEvent event){

        try{
            Parent splashscreen = FXMLLoader.load(getClass().getResource("inventorymanagement.fxml"));
            Stage inventoryManagement = new Stage();
            inventoryManagement.initStyle(StageStyle.UNDECORATED);
            inventoryManagement.setScene(new Scene(splashscreen,1120, 620));
            inventoryManagement.show();
        }
        catch(Exception ex){
            ex.printStackTrace();
            ex.getCause();
        }
        dashboardPane.getScene().getWindow().hide();
    }

    public void reservationManagementButtonOnAction(ActionEvent event){

        try{
            Parent splashscreen = FXMLLoader.load(getClass().getResource("reservationmanagement.fxml"));
            Stage reservationManagement = new Stage();
            reservationManagement.initStyle(StageStyle.UNDECORATED);
            reservationManagement.setScene(new Scene(splashscreen,1120, 620));
            reservationManagement.show();
        }
        catch(Exception ex){
            ex.printStackTrace();
            ex.getCause();
        }
        dashboardPane.getScene().getWindow().hide();
    }

    public void loyaltyRewardsButtonOnAction(ActionEvent event){

        try{
            Parent splashscreen = FXMLLoader.load(getClass().getResource("loyaltyrewards.fxml"));
            Stage loyaltyRewards = new Stage();
            loyaltyRewards.initStyle(StageStyle.UNDECORATED);
            loyaltyRewards.setScene(new Scene(splashscreen,1120, 620));
            loyaltyRewards.show();
        }
        catch(Exception ex){
            ex.printStackTrace();
            ex.getCause();
        }
        dashboardPane.getScene().getWindow().hide();
    }

    public void logoutButtonOnAction(ActionEvent event){

        try{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to logout ?");
            alert.showAndWait();

            Parent splashscreen = FXMLLoader.load(getClass().getResource("login.fxml"));
            Stage userLogin = new Stage();
            userLogin.initStyle(StageStyle.UNDECORATED);
            userLogin.setScene(new Scene(splashscreen,600, 400));
            userLogin.show();
        }
        catch(Exception ex){
            ex.printStackTrace();
            ex.getCause();
        }
        Stage logoutWindow = (Stage) logoutButton.getScene().getWindow();
        logoutWindow.close();

        dashboardPane.getScene().getWindow().hide();
    }
}
