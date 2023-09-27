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


public class EmployeeManagementController implements Initializable {

    @FXML
    private Button createEmployeeButton,updateEmployeeButton,cancelButton,employeesShowButton,deleteEmployeeButton,clearTableButton,searchEmployeeButton,closeButton;
    @FXML
    private Label employeeIdLabel,employeeFirstNameLabel,employeeLastNameLabel,employeeGenderLabel,employeeContactNumberLabel,employeePositionLabel;
    @FXML
    private Label employeeIdRequiredLabel,employeeFirstNameRequiredLabel,employeeLastNameRequiredLabel,employeeGenderRequiredLabel,employeeContactNumberRequiredLabel,employeePositionRequiredLabel,employeeSalaryRequiredLabel,employeeRoleRequiredLabel,employeeUsernameRequiredLabel,employeePasswordRequiredLabel;
    @FXML
    private TableView<Employee> displayEmployeeTable;

    @FXML
    private TableColumn<Employee, Long> employeeId;
    @FXML
    private TableColumn<Employee, String> employeeFirstName;
    @FXML
    private TableColumn<Employee, String> employeeLastName;
    @FXML
    private TableColumn<Employee, String> employeeGender;
    @FXML
    private TableColumn<Employee, Long> employeeContactNumber;
    @FXML
    private TableColumn<Employee, String> employeePosition;
    @FXML
    private TableColumn<Employee, Double> employeeSalary;
    @FXML
    private ComboBox<Employee> employeeGenderComboBox;
    @FXML
    private ComboBox<Employee> employeePositionComboBox;
    @FXML
    private ComboBox<Employee> employeeRoleComboBox;
    @FXML
    private TextField employeeIdTextField,employeeFirstNameTextField,employeeLastNameTextField,employeeContactNumberTextField,searchEmployeeTextField,employeeSalaryTextField,employeeUsernameTextField;
    @FXML
    private PasswordField employeePasswordPasswordField;
    @FXML
    private AnchorPane employeePane;



    ObservableList<Employee> displayEmployee = FXCollections.observableArrayList();

    private String[] gender = {"Male","Female","Other"};
    private String[] position = {"Cleaner","Waiter","Chef","Cashier","Supervisor","Manager","CEO"};
    private String[] role = {"Administrator","Employee","Guest"};


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List genderList = new ArrayList();
        for(String gender: gender){
            genderList.add(gender);
        }
        ObservableList listGender = FXCollections.observableArrayList(genderList);
        employeeGenderComboBox.setItems(listGender);

        List positionList = new ArrayList();
        for(String position: position){
            positionList.add(position);
        }
        ObservableList listPosition = FXCollections.observableArrayList(positionList);
        employeePositionComboBox.setItems(listPosition);

        List roleList = new ArrayList();
        for(String role: role){
            roleList.add(role);
        }
        ObservableList listRole = FXCollections.observableArrayList(roleList);
        employeeRoleComboBox.setItems(listRole);


        employeeId.setCellValueFactory(new PropertyValueFactory<Employee,Long>("employeeId"));
        employeeFirstName.setCellValueFactory(new PropertyValueFactory<Employee,String>("employeeFirstName"));
        employeeLastName.setCellValueFactory(new PropertyValueFactory<Employee,String>("employeeLastName"));
        employeeGender.setCellValueFactory(new PropertyValueFactory<Employee,String>("employeeGender"));
        employeeContactNumber.setCellValueFactory(new PropertyValueFactory<Employee,Long>("employeeContactNumber"));
        employeePosition.setCellValueFactory(new PropertyValueFactory<Employee,String>("employeePosition"));
        employeeSalary.setCellValueFactory(new PropertyValueFactory<Employee,Double>("employeeSalary"));
    }

    public void createEmployeeButtonOnAction(){
        if(employeeIdTextField.getText().isBlank()==false && employeeFirstNameTextField.getText().isBlank()==false && employeeLastNameTextField.getText().isBlank()==false && employeeGenderComboBox.toString().isBlank()==false && employeeContactNumberTextField.getText().isBlank()==false && employeePositionComboBox.toString().isBlank()==false && employeeRoleComboBox.toString().isBlank()==false && employeeSalaryTextField.toString().isBlank()==false && employeeUsernameTextField.toString().isBlank()==false && employeePasswordPasswordField.toString().isBlank()==false){
            validateEmployeeRegistration();
        }
        else{
            employeeIdRequiredLabel.setText("required *");
            employeeFirstNameRequiredLabel.setText("required *");
            employeeLastNameRequiredLabel.setText("required *");
            employeeGenderRequiredLabel.setText("required *");
            employeeContactNumberRequiredLabel.setText("required *");
            employeePositionRequiredLabel.setText("required *");
            employeeRoleRequiredLabel.setText("required *");
            employeeSalaryRequiredLabel.setText("required *");
            employeeUsernameRequiredLabel.setText("required *");
            employeePasswordRequiredLabel.setText("required *");

            employeeIdTextField.setText("");
            employeeFirstNameTextField.setText("");
            employeeLastNameTextField.setText("");
            employeeGenderComboBox.getSelectionModel().clearSelection();
            employeeContactNumberTextField.setText("");
            employeePositionComboBox.getSelectionModel().clearSelection();
            employeeRoleComboBox.getSelectionModel().clearSelection();
            employeeSalaryTextField.setText("");
            employeeUsernameTextField.setText("");
            employeePasswordPasswordField.setText("");
        }
    }
    public void updateEmployeeButtonOnAction(){
        Database database = new Database();
        Connection connection = database.getConnection();

        String updateEmployee = "UPDATE employee SET employeeFirstName = '"+employeeFirstNameTextField.getText()+"', employeeLastName = '"+employeeLastNameTextField.getText()+"', employeeGender = '"+employeeGenderComboBox.getSelectionModel().getSelectedItem()+"', employeeContactNumber = '"+employeeContactNumberTextField.getText()+"', employeePosition = '"+employeePositionComboBox.getSelectionModel().getSelectedItem()+"', employeeRole = '"+employeeRoleComboBox.getSelectionModel().getSelectedItem()+"', employeeSalary = '"+employeeSalaryTextField.getText()+"', employeeUsername = '"+employeeUsernameTextField.getText()+"', employeePassword = '"+employeePasswordPasswordField.getText()+"' WHERE employeeId = '"+employeeIdTextField.getText()+"'";

        try{
            Statement statement = connection.createStatement();
            statement.executeUpdate(updateEmployee);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Message");
            alert.setHeaderText(null);
            alert.setContentText("Employee was successfully updated !");
            alert.showAndWait();

            employeeIdTextField.setText("");
            employeeFirstNameTextField.setText("");
            employeeLastNameTextField.setText("");
            employeeGenderComboBox.getSelectionModel().clearSelection();
            employeeContactNumberTextField.setText("");
            employeePositionComboBox.getSelectionModel().clearSelection();
            employeeRoleComboBox.getSelectionModel().clearSelection();
            employeeSalaryTextField.setText("");
            employeeUsernameTextField.setText("");
            employeePasswordPasswordField.setText("");

            displayEmployee.clear();
        }
        catch(Exception ex){
            ex.printStackTrace();
            ex.getCause();
        }
    }
    public void cancelButtonOnAction(){
        employeeIdRequiredLabel.setText("");
        employeeFirstNameRequiredLabel.setText("");
        employeeLastNameRequiredLabel.setText("");
        employeeGenderRequiredLabel.setText("");
        employeeContactNumberRequiredLabel.setText("");
        employeePositionRequiredLabel.setText("");
        employeeRoleRequiredLabel.setText("");
        employeeSalaryRequiredLabel.setText("");
        employeeUsernameRequiredLabel.setText("");
        employeePasswordRequiredLabel.setText("");

        employeeIdTextField.setText("");
        employeeFirstNameTextField.setText("");
        employeeLastNameTextField.setText("");
        employeeGenderComboBox.getSelectionModel().clearSelection();
        employeeContactNumberTextField.setText("");
        employeePositionComboBox.getSelectionModel().clearSelection();
        employeeRoleComboBox.getSelectionModel().clearSelection();
        employeeSalaryTextField.setText("");
        employeeUsernameTextField.setText("");
        employeePasswordPasswordField.setText("");
    }
    public void employeesShowButtonOnAction(){
        Database database = new Database();
        Connection connection = database.getConnection();

        String showEmployees = "SELECT * FROM employee";

        displayEmployee.clear();

        try{
            Statement statement = connection.createStatement();
            ResultSet queryResult = statement.executeQuery(showEmployees);

            while(queryResult.next()){
                displayEmployee.add(new Employee(queryResult.getLong("employeeId"), queryResult.getString("employeeFirstName"), queryResult.getString("employeeLastName"), queryResult.getString("employeeGender"), queryResult.getLong("employeeContactNumber"), queryResult.getString("employeePosition"), queryResult.getString("employeeRole"), queryResult.getDouble("employeeSalary"),queryResult.getString("employeeUsername")));
                displayEmployeeTable.setItems(displayEmployee);
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
            ex.getCause();
        }
    }
    public void deleteEmployeeButtonOnAction(){
        Database database = new Database();
        Connection connection = database.getConnection();

        String deleteEmployee = "DELETE FROM employee WHERE employeeId = '" + employeeIdTextField.getText() + "'";

        Employee deleteSelectedIndex = displayEmployeeTable.getSelectionModel().getSelectedItem();
        displayEmployeeTable.getSelectionModel().getSelectedIndex();
        displayEmployee.remove(deleteSelectedIndex);

        try{
            Statement statement = connection.createStatement();
            statement.executeUpdate(deleteEmployee);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Message");
            alert.setHeaderText(null);
            alert.setContentText("Employee was successfully deleted !");
            alert.showAndWait();

            employeeIdTextField.setText("");
            employeeFirstNameTextField.setText("");
            employeeLastNameTextField.setText("");
            employeeGenderComboBox.getSelectionModel().clearSelection();
            employeeContactNumberTextField.setText("");
            employeePositionComboBox.getSelectionModel().clearSelection();
            employeeRoleComboBox.getSelectionModel().clearSelection();
            employeeSalaryTextField.setText("");
            employeeUsernameTextField.setText("");
            employeePasswordPasswordField.setText("");
        }
        catch(Exception ex){
            ex.printStackTrace();
            ex.getCause();
        }
    }
    public void clearTableButtonOnAction(){
        employeeIdTextField.setText("");
        employeeFirstNameTextField.setText("");
        employeeLastNameTextField.setText("");
        employeeGenderComboBox.getSelectionModel().clearSelection();
        employeeContactNumberTextField.setText("");
        employeePositionComboBox.getSelectionModel().clearSelection();
        employeeRoleComboBox.getSelectionModel().clearSelection();
        employeeSalaryTextField.setText("");
        employeeUsernameTextField.setText("");
        employeePasswordPasswordField.setText("");

        displayEmployee.clear();
    }
    public void searchEmployeeButtonOnAction(){
        Database database = new Database();
        Connection connection = database.getConnection();

        String searchEmployee = "SELECT * FROM employee WHERE employeeId = '" + searchEmployeeTextField.getText() + "' OR employeeFirstName = '" + searchEmployeeTextField.getText() + "'OR employeeLastName = '" + searchEmployeeTextField.getText() + "'OR employeeGender = '" + searchEmployeeTextField.getText() + "' OR employeeContactNumber = '" + searchEmployeeTextField.getText() + "' OR employeePosition = '" + searchEmployeeTextField.getText() + "' OR employeeSalary = '" + searchEmployeeTextField.getText() + "'";

        displayEmployee.clear();

        try{
            Statement statement = connection.createStatement();
            ResultSet queryResult = statement.executeQuery(searchEmployee);

            while (queryResult.next()) {
                displayEmployee.add(new Employee(queryResult.getLong("employeeId"), queryResult.getString("employeeFirstName"), queryResult.getString("employeeLastName"), queryResult.getString("employeeGender"), queryResult.getLong("employeeContactNumber"), queryResult.getString("employeePosition"), queryResult.getString("employeeRole"), queryResult.getDouble("employeeSalary"), queryResult.getString("employeeUsername")));
                displayEmployeeTable.setItems(displayEmployee);
            }
            searchEmployeeTextField.setText("");
        }
        catch(Exception ex){
            ex.printStackTrace();
            ex.getCause();
        }
    }

    public void employeeSelected(){
        Employee selectedEmployee = displayEmployeeTable.getSelectionModel().getSelectedItem();
        displayEmployeeTable.getSelectionModel().getSelectedIndex();

        employeeIdTextField.setText(selectedEmployee.getEmployeeId().toString());
        employeeFirstNameTextField.setText(selectedEmployee.getEmployeeFirstName());
        employeeLastNameTextField.setText(selectedEmployee.getEmployeeLastName());
        employeeContactNumberTextField.setText(selectedEmployee.getEmployeeContactNumber().toString());
        employeeSalaryTextField.setText(selectedEmployee.getEmployeeSalary().toString());
        employeeUsernameTextField.setText(selectedEmployee.getEmployeeUsername());
    }

    public void validateEmployeeRegistration(){
        Database database = new Database();
        Connection connection = database.getConnection();

        String employeeId = employeeIdTextField.getText();
        String employeeFirstName = employeeFirstNameTextField.getText();
        String employeeLastName = employeeLastNameTextField.getText();
        String employeeGender = String.valueOf(employeeGenderComboBox.getSelectionModel().getSelectedItem());
        String employeeContactNumber = employeeContactNumberTextField.getText();
        String employeePosition = String.valueOf(employeePositionComboBox.getSelectionModel().getSelectedItem());
        String employeeRole = String.valueOf(employeeRoleComboBox.getSelectionModel().getSelectedItem());
        String employeeSalary = employeeSalaryTextField.getText();
        String employeeUsername = employeeUsernameTextField.getText();
        String employeePassword = employeePasswordPasswordField.getText();

        String captureEmployeeRegistrationData = "INSERT INTO employee values ('"+employeeId+"','"+employeeFirstName+"','"+employeeLastName+"','"+employeeGender+"','"+employeeContactNumber+"','"+employeePosition+"','"+employeeRole+"','"+employeeSalary+"','"+employeeUsername+"','"+employeePassword+"')";

        try{
            Statement statement = connection.createStatement();
            statement.executeUpdate(captureEmployeeRegistrationData);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Message");
            alert.setHeaderText(null);
            alert.setContentText("Employee successfully registered, Welcome to TheCubanaLounge "+employeeFirstName+" "+employeeLastName+" !");
            alert.showAndWait();

            employeeIdRequiredLabel.setText("");
            employeeFirstNameRequiredLabel.setText("");
            employeeLastNameRequiredLabel.setText("");
            employeeGenderRequiredLabel.setText("");
            employeeContactNumberRequiredLabel.setText("");
            employeePositionRequiredLabel.setText("");
            employeeRoleRequiredLabel.setText("");
            employeeSalaryRequiredLabel.setText("");
            employeeUsernameRequiredLabel.setText("");
            employeePasswordRequiredLabel.setText("");

            employeeIdTextField.setText("");
            employeeFirstNameTextField.setText("");
            employeeLastNameTextField.setText("");
            employeeGenderComboBox.getSelectionModel().clearSelection();
            employeeContactNumberTextField.setText("");
            employeePositionComboBox.getSelectionModel().clearSelection();
            employeeRoleComboBox.getSelectionModel().clearSelection();
            employeeSalaryTextField.setText("");
            employeeUsernameTextField.setText("");
            employeePasswordPasswordField.setText("");
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

        employeePane.getScene().getWindow().hide();
    }
}
