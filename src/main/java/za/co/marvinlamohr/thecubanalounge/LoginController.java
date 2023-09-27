package za.co.marvinlamohr.thecubanalounge;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.event.ActionEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class LoginController implements Initializable{
    @FXML
    private Button loginButton,cancelButton,closeButton;
    @FXML
    private Label loginErrorMessage,usernameRequiredLabel,passwordRequiredLabel,roleRequiredLabel,usernameLabel,passwordLabel,roleLabel;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private ComboBox roleComboBox;
    @FXML
    private ImageView brandingImageView;
    @FXML
    private BorderPane loginPane;

    private String[] role = {"Administrator","Employee","Guest"};


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        File brandingFile = new File("assets/TheCubanaLounge.png");
        Image brandingImage = new Image(brandingFile.toURI().toString());
        brandingImageView.setImage(brandingImage);

        List roleList = new ArrayList();
        for(String role: role){
            roleList.add(role);
        }
        ObservableList listRole = FXCollections.observableArrayList(roleList);
        roleComboBox.setItems(listRole);
    }

    public void loginButtonOnAction(ActionEvent event){

        if(usernameField.getText().isBlank()==false && passwordField.getText().isBlank()==false && roleComboBox.getSelectionModel().getSelectedItem().toString().isBlank()==false){
            validateUserLogin();
        }
        else{
            loginErrorMessage.setText("Invalid login, Username, Password and Role is required!");
            usernameRequiredLabel.setText("required *");
            passwordRequiredLabel.setText("required *");
            roleRequiredLabel.setText("required *");
            usernameField.setText("");
            passwordField.setText("");
            roleComboBox.getSelectionModel().clearSelection();
        }
    }

    public void cancelButtonOnAction(ActionEvent event){
        loginErrorMessage.setText("");
        usernameRequiredLabel.setText("");
        passwordRequiredLabel.setText("");
        roleRequiredLabel.setText("");
        usernameField.setText("");
        passwordField.setText("");
        roleComboBox.getSelectionModel().clearSelection();
    }

    public void closeButtonOnAction(ActionEvent event){
        Stage closeWindow = (Stage) closeButton.getScene().getWindow();
        closeWindow.close();
    }

    public void validateUserLogin() {
        Database database = new Database();
        Connection connection = database.getConnection();

        String verifyLogin = "SELECT count(1) FROM employee WHERE employeeUsername = '" + usernameField.getText() + "' AND employeePassword = '" + passwordField.getText() + "' AND employeeRole = '" + roleComboBox.getSelectionModel().getSelectedItem() + "'";

        try {
            Statement statement = connection.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while (queryResult.next()) {
                Employee.employeeActive = usernameField.getText().toUpperCase();

                if (queryResult.getInt(1) == 1) {
                    Parent splashscreen = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
                    Stage dashboardscreen = new Stage();
                    dashboardscreen.initStyle(StageStyle.UNDECORATED);
                    dashboardscreen.setScene(new Scene(splashscreen,800, 500));
                    dashboardscreen.show();
                    loginPane.getScene().getWindow().hide();
                }
                else {
                    loginErrorMessage.setText("Invalid login, Username, Password or Role is incorrect!");
                    usernameRequiredLabel.setText("              *");
                    passwordRequiredLabel.setText("              *");
                    roleRequiredLabel.setText("              *");
                    usernameField.setText("");
                    passwordField.setText("");
                    roleComboBox.getSelectionModel().clearSelection();
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
            ex.getCause();
        }
    }
}