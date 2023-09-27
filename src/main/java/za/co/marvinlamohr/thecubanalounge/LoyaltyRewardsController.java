package za.co.marvinlamohr.thecubanalounge;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class LoyaltyRewardsController implements Initializable {

    @FXML
    private Button rewardsSignUpButton,checkInButton,searchButton,rewardsShowButton,redeemButton,clearButton,cancelSignUpButton,cancelCheckInButton,cancelPointsButton,closeButton;

    @FXML
    private Label welcomeSignUpLabel,signUpLabel,welcomeCheckInLabel,checkInLabel,helloRewardsSearchLabel,pointsSearchLabel,rewardsMemberSearchLabel,rewardsMemberSearchPointsLabel;

    @FXML
    private Label rewardsSignUpFirstNameLabel,rewardsSignUpLastNameLabel,rewardsSignUpCardNumberLabel,rewardsCheckInFirstNameLabel,rewardsCheckInLastNameLabel,rewardsCheckInCardNumberLabel,rewardsSearchFirstNameLabel,rewardsSearchLastNameLabel,rewardsSearchCardNumberLabel;

    @FXML
    private Label rewardsMember,rewardsPoints;

    @FXML
    private Label rewardsSignUpFirstNameRequiredLabel,rewardsSignUpLastNameRequiredLabel,rewardsSignUpCardNumberRequiredLabel,rewardsCheckInFirstNameRequiredLabel,rewardsCheckInLastNameRequiredLabel,rewardsCheckInCardNumberRequiredLabel,rewardsSearchFirstNameRequiredLabel,rewardsSearchLastNameRequiredLabel,rewardsSearchCardNumberRequiredLabel;

    @FXML
    private TextField rewardsSignUpFirstNameTextField,rewardsSignUpLastNameTextField,rewardsSignUpCardNumberTextField,rewardsCheckInFirstNameTextField,rewardsCheckInLastNameTextField,rewardsCheckInCardNumberTextField,rewardsSearchFirstNameTextField,rewardsSearchLastNameTextField,rewardsSearchCardNumberTextField;

    @FXML
    private TableView<Prize> displayRewardsTable;

    @FXML
    private TableColumn<Prize, String> rewardsViewNumber;

    @FXML
    private TableColumn<Prize, String> rewardsViewRewards;

    @FXML
    private TableColumn<Prize, Integer> rewardsViewPoints;

    @FXML
    private AnchorPane rewardsPane;

    ObservableList<Prize> displayRewards = FXCollections.observableArrayList();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        rewardsViewNumber.setCellValueFactory(new PropertyValueFactory<Prize,String>("prizeId"));
        rewardsViewRewards.setCellValueFactory(new PropertyValueFactory<Prize,String>("prizeName"));
        rewardsViewPoints.setCellValueFactory(new PropertyValueFactory<Prize,Integer>("prizePoints"));
    }

    public void signUpButtonOnAction(ActionEvent event){
        if(rewardsSignUpFirstNameTextField.getText().isBlank()==false && rewardsSignUpLastNameTextField.getText().isBlank()==false && rewardsSignUpCardNumberTextField.getText().isBlank()==false){
            validateRewardsMemberSignUp();
        }
        else{
            rewardsSignUpFirstNameRequiredLabel.setText("required *");
            rewardsSignUpLastNameRequiredLabel.setText("required *");
            rewardsSignUpCardNumberRequiredLabel.setText("required *");

            rewardsSignUpFirstNameTextField.setText("");
            rewardsSignUpLastNameTextField.setText("");
            rewardsSignUpCardNumberTextField.setText("");
        }
    }

    public void signUpCancelButtonOnAction(ActionEvent event){
        rewardsSignUpFirstNameRequiredLabel.setText("");
        rewardsSignUpLastNameRequiredLabel.setText("");
        rewardsSignUpCardNumberRequiredLabel.setText("");

        rewardsSignUpFirstNameTextField.setText("");
        rewardsSignUpLastNameTextField.setText("");
        rewardsSignUpCardNumberTextField.setText("");
    }

    public void checkInButtonOnAction(ActionEvent event){
        if(rewardsCheckInFirstNameTextField.getText().isBlank()==false && rewardsCheckInLastNameTextField.getText().isBlank()==false && rewardsCheckInCardNumberTextField.getText().isBlank()==false){
            validateRewardsMemberCheckIn();
        }
        else{
            rewardsCheckInFirstNameRequiredLabel.setText("required *");
            rewardsCheckInLastNameRequiredLabel.setText("required *");
            rewardsCheckInCardNumberRequiredLabel.setText("required *");

            rewardsCheckInFirstNameTextField.setText("");
            rewardsCheckInLastNameTextField.setText("");
            rewardsCheckInCardNumberTextField.setText("");
        }
    }

    public void checkInCancelButtonOnAction(ActionEvent event){
        rewardsCheckInFirstNameRequiredLabel.setText("");
        rewardsCheckInLastNameRequiredLabel.setText("");
        rewardsCheckInCardNumberRequiredLabel.setText("");

        rewardsCheckInFirstNameTextField.setText("");
        rewardsCheckInLastNameTextField.setText("");
        rewardsCheckInCardNumberTextField.setText("");
    }

    public void searchButtonOnAction(ActionEvent event){
        if(rewardsSearchFirstNameTextField.getText().isBlank()==false && rewardsSearchLastNameTextField.getText().isBlank()==false && rewardsSearchCardNumberTextField.getText().isBlank()==false){
            validateRewardsMemberSearch();
        }
        else{
            rewardsSearchFirstNameRequiredLabel.setText("required *");
            rewardsSearchLastNameRequiredLabel.setText("required *");
            rewardsSearchCardNumberRequiredLabel.setText("required *");

            rewardsSearchFirstNameTextField.setText("");
            rewardsSearchLastNameTextField.setText("");
            rewardsSearchCardNumberTextField.setText("");
        }
    }

    public void searchCancelButtonOnAction(ActionEvent event){
        rewardsSearchFirstNameRequiredLabel.setText("");
        rewardsSearchLastNameRequiredLabel.setText("");
        rewardsSearchCardNumberRequiredLabel.setText("");

        rewardsSearchFirstNameTextField.setText("");
        rewardsSearchLastNameTextField.setText("");
        rewardsSearchCardNumberTextField.setText("");
    }

    public void rewardsButtonOnAction(ActionEvent event){
        Database database = new Database();
        Connection connection = database.getConnection();

        String showRewards = "SELECT * FROM prize";

        displayRewards.clear();

        try{
            Statement statement = connection.createStatement();
            ResultSet queryResult = statement.executeQuery(showRewards);

            while(queryResult.next()){
                displayRewards.add(new Prize(queryResult.getInt("prizeId"), queryResult.getString("prizeName"), queryResult.getInt("prizePoints")));
                displayRewardsTable.setItems(displayRewards);
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
            ex.getCause();
        }
    }
    public void redeemButtonOnAction(ActionEvent event){
        Database database = new Database();
        Connection connection = database.getConnection();

        String member = rewardsMember.getText();
        int points = Integer.parseInt(rewardsPoints.getText());

        Prize selectedPrize = displayRewardsTable.getSelectionModel().getSelectedItem();

        String claimReward = "UPDATE reward SET rewardsMemberPoints = rewardsMemberPoints - '"+selectedPrize.getPrizePoints()+"' WHERE rewardsMemberFirstName = '"+rewardsSearchFirstNameTextField.getText()+"' AND rewardsMemberLastName = '"+rewardsSearchLastNameTextField.getText()+"'AND rewardsMemberCardNumber = '"+rewardsSearchCardNumberTextField.getText()+"'";

        try{
            if(points >= selectedPrize.getPrizePoints()){
                Statement statement = connection.createStatement();
                statement.executeUpdate(claimReward);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Hi there "+member+", You used "+selectedPrize.getPrizePoints()+" of your points to claim "+selectedPrize.getPrizeName()+" enjoy !");
                alert.showAndWait();

                rewardsMember.setText("");
                rewardsPoints.setText("");
                displayRewards.clear();

                rewardsSearchFirstNameTextField.setText("");
                rewardsSearchLastNameTextField.setText("");
                rewardsSearchCardNumberTextField.setText("");
            }
            else if(points < selectedPrize.getPrizePoints()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Hi there "+member+", You unfortunately do not have enough points to claim this reward !");
                alert.showAndWait();

                rewardsMember.setText("");
                rewardsPoints.setText("");
                displayRewards.clear();

                rewardsSearchFirstNameTextField.setText("");
                rewardsSearchLastNameTextField.setText("");
                rewardsSearchCardNumberTextField.setText("");
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
            ex.getCause();
        }
    }

    public void clearButtonOnAction(ActionEvent event){
        displayRewards.clear();
        rewardsMember.setText("");
        rewardsPoints.setText("");

        rewardsSearchFirstNameTextField.setText("");
        rewardsSearchLastNameTextField.setText("");
        rewardsSearchCardNumberTextField.setText("");
    }

    public void validateRewardsMemberSignUp(){
        Database database = new Database();
        Connection connection = database.getConnection();

        int rewardsMemberPoints = 200;

        String rewardsMemberFirstName = rewardsSignUpFirstNameTextField.getText();
        String rewardsMemberLastName = rewardsSignUpLastNameTextField.getText();
        String rewardsMemberCardNumber = rewardsSignUpCardNumberTextField.getText();

        String captureRewardData = "INSERT INTO reward values ('"+rewardsMemberFirstName+"','"+rewardsMemberLastName+"','"+rewardsMemberCardNumber+"','"+rewardsMemberPoints+"')";

        try{
            Statement statement = connection.createStatement();
            statement.executeUpdate(captureRewardData);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Message");
            alert.setHeaderText(null);
            alert.setContentText("TheCubanaLounge rewards sign up was successful, Welcome to TheCubanaLounge rewards "+rewardsMemberFirstName.toUpperCase()+" "+rewardsMemberLastName.toUpperCase()+" !");
            alert.showAndWait();

            rewardsSignUpFirstNameRequiredLabel.setText("");
            rewardsSignUpLastNameRequiredLabel.setText("");
            rewardsSignUpCardNumberRequiredLabel.setText("");

            rewardsSignUpFirstNameTextField.setText("");
            rewardsSignUpLastNameTextField.setText("");
            rewardsSignUpCardNumberTextField.setText("");
        }
        catch(Exception ex){
            ex.printStackTrace();
            ex.getCause();
        }
    }

    public void validateRewardsMemberCheckIn(){
        Database database = new Database();
        Connection connection = database.getConnection();

        int checkInPoints = 50;

        String rewardsMemberCheckInFirstName = rewardsCheckInFirstNameTextField.getText();
        String rewardsMemberCheckInLastName = rewardsCheckInLastNameTextField.getText();

        String updateReward = "UPDATE reward SET rewardsMemberPoints = rewardsMemberPoints +'"+checkInPoints+"' WHERE rewardsMemberFirstName = '"+rewardsCheckInFirstNameTextField.getText()+"'AND rewardsMemberLastName = '"+rewardsCheckInLastNameTextField.getText()+"' AND rewardsMemberCardNumber = '"+rewardsCheckInCardNumberTextField.getText()+"'";

        try{
            Statement statement = connection.createStatement();
            statement.executeUpdate(updateReward);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Message");
            alert.setHeaderText(null);
            alert.setContentText("Welcome back to TheCubanaLounge "+rewardsMemberCheckInFirstName+" "+rewardsMemberCheckInLastName+", You earned 50 additional points we hope you enjoyed your visit !");
            alert.showAndWait();

            rewardsCheckInFirstNameRequiredLabel.setText("");
            rewardsCheckInLastNameRequiredLabel.setText("");
            rewardsCheckInCardNumberRequiredLabel.setText("");

            rewardsCheckInFirstNameTextField.setText("");
            rewardsCheckInLastNameTextField.setText("");
            rewardsCheckInCardNumberTextField.setText("");
        }
        catch(Exception ex){
            ex.printStackTrace();
            ex.getCause();
        }
    }

    public void validateRewardsMemberSearch(){
        Database database = new Database();
        Connection connection = database.getConnection();

        String member = rewardsSearchFirstNameTextField.getText() +" "+ rewardsSearchLastNameTextField.getText();
        String points = "SELECT rewardsMemberPoints FROM reward WHERE rewardsMemberFirstName = '" + rewardsSearchFirstNameTextField.getText() + "' AND rewardsMemberLastName = '" + rewardsSearchLastNameTextField.getText() + "' AND rewardsMemberCardNumber = '" + rewardsSearchCardNumberTextField.getText() + "'";

        try{
            Statement statement = connection.createStatement();
            ResultSet queryResult = statement.executeQuery(points);

            while(queryResult.next()){
                rewardsMember.setText(member.toUpperCase());
                rewardsPoints.setText(queryResult.getString("rewardsMemberPoints"));
            }
            rewardsSearchFirstNameRequiredLabel.setText("");
            rewardsSearchLastNameRequiredLabel.setText("");
            rewardsSearchCardNumberRequiredLabel.setText("");
        }
        catch(Exception ex){
            ex.printStackTrace();
            ex.getCause();
        }
    }

    public void closeButtonOnAction(ActionEvent event){
        try{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to return to the dashboard ?");
            alert.showAndWait();

            Parent splashscreen = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
            Stage dashboardscreen = new Stage();
            dashboardscreen.initStyle(StageStyle.UNDECORATED);
            dashboardscreen.setScene(new Scene(splashscreen,800, 500));
            dashboardscreen.show();
        }
        catch(Exception ex){
            ex.printStackTrace();
            ex.getCause();
        }
        Stage closeWindow = (Stage) closeButton.getScene().getWindow();
        closeWindow.close();

        rewardsPane.getScene().getWindow().hide();
    }
}
