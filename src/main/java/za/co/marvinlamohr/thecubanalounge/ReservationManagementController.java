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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ReservationManagementController implements Initializable {

    @FXML
    private Button createReservationButton,updateReservationButton,cancelButton,reservationShowButton,deleteReservationButton,clearTableButton,searchReservationButton,closeButton;
    @FXML
    private Label reservationFirstNameLabel,reservationLastNameLabel,reservationContactNumberLabel,reservationTimeLabel,reservationDateLabel,titleLabel;
    @FXML
    private Label reservationFirstNameRequiredLabel,reservationLastNameRequiredLabel,reservationContactNumberRequiredLabel,reservationTimeRequiredLabel,reservationDateRequiredLabel;
    @FXML
    private TextField reservationFirstNameTextField,reservationLastNameTextField,reservationContactNumberTextField,reservationSearchTextField;
    @FXML
    private ComboBox<Reservation> reservationTimeComboBox;
    @FXML
    private DatePicker reservationDateDatePicker;
    @FXML
    private TableView<Reservation> displayReservationTable;
    @FXML
    private TableColumn<Reservation, String> reservationFirstName;
    @FXML
    private TableColumn<Reservation, String> reservationLastName;
    @FXML
    private TableColumn<Reservation, Long> reservationContactNumber;
    @FXML
    private TableColumn<Reservation, String> reservationTime;
    @FXML
    private TableColumn<Reservation, String> reservationDate;

    @FXML
    private AnchorPane reservationPane;

    ObservableList<Reservation> displayReservation = FXCollections.observableArrayList();

    private String[] time = {"09:00","9:30","10:00","10:30","11:00","11:30","12:00","12:30","13:00",
                             "13:30","14:00","14:30","15:00","15:30","16:00","16:30","17:00","17:30","18:00",
                             "18:30","19:00","19:30","20:00"};


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List timeList = new ArrayList();
        for(String time: time){
            timeList.add(time);
        }
        ObservableList listTime = FXCollections.observableArrayList(timeList);
        reservationTimeComboBox.setItems(listTime);


        reservationFirstName.setCellValueFactory(new PropertyValueFactory<Reservation,String>("reservationFirstName"));
        reservationLastName.setCellValueFactory(new PropertyValueFactory<Reservation,String>("reservationLastName"));
        reservationContactNumber.setCellValueFactory(new PropertyValueFactory<Reservation,Long>("reservationContactNumber"));
        reservationTime.setCellValueFactory(new PropertyValueFactory<Reservation, String>("reservationTime"));
        reservationDate.setCellValueFactory(new PropertyValueFactory<Reservation, String>("reservationDate"));
    }

    public void createReservationButtonOnAction(ActionEvent event){
        if(reservationFirstNameTextField.getText().isBlank()==false && reservationLastNameTextField.getText().isBlank()==false && reservationContactNumberTextField.getText().isBlank()==false && reservationTimeComboBox.toString().isBlank()==false && reservationDateDatePicker.toString().isBlank()==false){
            validateReservation();
        }
        else{
            reservationFirstNameRequiredLabel.setText("required *");
            reservationLastNameRequiredLabel.setText("required *");
            reservationContactNumberRequiredLabel.setText("required *");
            reservationTimeRequiredLabel.setText("required *");
            reservationDateRequiredLabel.setText("required *");

            reservationFirstNameTextField.setText("");
            reservationLastNameTextField.setText("");
            reservationContactNumberTextField.setText("");
            reservationTimeComboBox.getSelectionModel().clearSelection();
            reservationDateDatePicker.setValue(null);
        }
    }

    public void updateReservationButtonOnAction(ActionEvent event){
        Database database = new Database();
        Connection connection = database.getConnection();

        String updateReservation = "UPDATE reservation SET reservationFirstName = '"+reservationFirstNameTextField.getText()+"', reservationLastName = '"+reservationLastNameTextField.getText()+"', reservationContactNumber = '"+reservationContactNumberTextField.getText()+"', reservationTime = '"+reservationTimeComboBox.getSelectionModel().getSelectedItem()+"', reservationDate = '"+reservationDateDatePicker.valueProperty().get()+"' WHERE reservationFirstName = '"+reservationFirstNameTextField.getText()+"' AND reservationLastName = '"+reservationLastNameTextField.getText()+"'";

        try{
            Statement statement = connection.createStatement();
            statement.executeUpdate(updateReservation);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Message");
            alert.setHeaderText(null);
            alert.setContentText("Reservation was successfully updated !");
            alert.showAndWait();

            reservationFirstNameTextField.setText("");
            reservationLastNameTextField.setText("");
            reservationContactNumberTextField.setText("");
            reservationTimeComboBox.getSelectionModel().clearSelection();
            reservationDateDatePicker.setValue(null);

            displayReservation.clear();
        }
        catch(Exception ex){
            ex.printStackTrace();
            ex.getCause();
        }
    }

    public void cancelButtonOnAction(ActionEvent event){
        reservationFirstNameRequiredLabel.setText("");
        reservationLastNameRequiredLabel.setText("");
        reservationContactNumberRequiredLabel.setText("");
        reservationTimeRequiredLabel.setText("");
        reservationDateRequiredLabel.setText("");

        reservationFirstNameTextField.setText("");
        reservationLastNameTextField.setText("");
        reservationContactNumberTextField.setText("");
        reservationTimeComboBox.getSelectionModel().clearSelection();
        reservationDateDatePicker.setValue(null);
    }

    public void reservationsShowButtonOnAction(ActionEvent event){
        Database database = new Database();
        Connection connection = database.getConnection();

        String showReservations = "SELECT * FROM reservation";

        displayReservation.clear();

        try{
            Statement statement = connection.createStatement();
            ResultSet queryResult = statement.executeQuery(showReservations);

            while(queryResult.next()){
                displayReservation.add(new Reservation(queryResult.getString("reservationFirstName"), queryResult.getString("reservationLastName"), queryResult.getLong("reservationContactNumber"), queryResult.getString("reservationTime"), queryResult.getString("reservationDate")));
                displayReservationTable.setItems(displayReservation);
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
            ex.getCause();
        }
    }

    public void deleteReservationButtonOnAction(ActionEvent event){
        Database database = new Database();
        Connection connection = database.getConnection();

        String deleteReservation = "DELETE FROM reservation WHERE reservationFirstName = '" + reservationFirstNameTextField.getText() + "' AND reservationLastName = '" + reservationLastNameTextField.getText() + "'";

        Reservation deleteSelectedIndex = displayReservationTable.getSelectionModel().getSelectedItem();
        displayReservationTable.getSelectionModel().getSelectedIndex();
        displayReservation.remove(deleteSelectedIndex);

        try{
            Statement statement = connection.createStatement();
            statement.executeUpdate(deleteReservation);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Message");
            alert.setHeaderText(null);
            alert.setContentText("Reservation was successfully deleted !");
            alert.showAndWait();

            reservationFirstNameTextField.setText("");
            reservationLastNameTextField.setText("");
            reservationContactNumberTextField.setText("");
            reservationTimeComboBox.getSelectionModel().clearSelection();
            reservationDateDatePicker.setValue(null);
        }
        catch(Exception ex){
            ex.printStackTrace();
            ex.getCause();
        }
    }

    public void clearTableButtonOnAction(ActionEvent event){
        reservationFirstNameTextField.setText("");
        reservationLastNameTextField.setText("");
        reservationContactNumberTextField.setText("");
        reservationTimeComboBox.getSelectionModel().clearSelection();
        reservationDateDatePicker.setValue(null);

        displayReservation.clear();
    }

    public void searchReservationButtonButtonOnAction(ActionEvent event){
        Database database = new Database();
        Connection connection = database.getConnection();

        String searchReservation = "SELECT * FROM reservation WHERE reservationFirstName = '" + reservationSearchTextField.getText() + "' OR reservationLastName = '" + reservationSearchTextField.getText() + "'OR reservationContactNumber = '" + reservationSearchTextField.getText() + "'OR reservationTime = '" + reservationSearchTextField.getText() + "' OR reservationDate = '" + reservationSearchTextField.getText() + "'";

        displayReservation.clear();

        try{
            Statement statement = connection.createStatement();
            ResultSet queryResult = statement.executeQuery(searchReservation);

            while(queryResult.next()){
                displayReservation.add(new Reservation(queryResult.getString("reservationFirstName"), queryResult.getString("reservationLastName"), queryResult.getLong("reservationContactNumber"), queryResult.getString("reservationTime"), queryResult.getString("reservationDate")));
                displayReservationTable.setItems(displayReservation);
            }
            reservationSearchTextField.setText("");
        }
        catch(Exception ex){
            ex.printStackTrace();
            ex.getCause();
        }
    }

    public void reservationSelected(){
        Reservation selectedReservation = displayReservationTable.getSelectionModel().getSelectedItem();
        displayReservationTable.getSelectionModel().getSelectedIndex();

        reservationFirstNameTextField.setText(selectedReservation.getReservationFirstName());
        reservationLastNameTextField.setText(selectedReservation.getReservationLastName());
        reservationContactNumberTextField.setText(selectedReservation.getReservationContactNumber().toString());
    }

    public void validateReservation(){
        Database database = new Database();
        Connection connection = database.getConnection();

        String reservationFirstName = reservationFirstNameTextField.getText();
        String reservationLastName = reservationLastNameTextField.getText();
        String reservationContactNumber = reservationContactNumberTextField.getText();
        String reservationTime = String.valueOf(reservationTimeComboBox.getSelectionModel().getSelectedItem());
        String reservationDate = String.valueOf(reservationDateDatePicker.valueProperty().get());

        String captureReservationData = "INSERT INTO reservation values ('"+reservationFirstName+"','"+reservationLastName+"','"+reservationContactNumber+"','"+reservationTime+"','"+reservationDate+"')";

        try{
            Statement statement = connection.createStatement();
            statement.executeUpdate(captureReservationData);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Message");
            alert.setHeaderText(null);
            alert.setContentText("Reservation successfully added, We will see you on "+reservationDate+" "+reservationFirstName+" "+reservationLastName+" !");
            alert.showAndWait();

            reservationFirstNameRequiredLabel.setText("");
            reservationLastNameRequiredLabel.setText("");
            reservationContactNumberRequiredLabel.setText("");
            reservationTimeRequiredLabel.setText("");
            reservationDateRequiredLabel.setText("");

            reservationFirstNameTextField.setText("");
            reservationLastNameTextField.setText("");
            reservationContactNumberTextField.setText("");
            reservationTimeComboBox.getSelectionModel().clearSelection();
            reservationDateDatePicker.setValue(null);
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

        reservationPane.getScene().getWindow().hide();
    }
}
