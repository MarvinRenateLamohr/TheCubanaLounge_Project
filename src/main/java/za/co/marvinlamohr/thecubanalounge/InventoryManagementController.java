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

public class InventoryManagementController implements Initializable {

    @FXML
    private Button createInventoryButton,updateInventoryButton,cancelButton,inventoryShowButton,deleteInventoryButton,clearTableButton,searchInventoryButton,closeButton;
    @FXML
    private Label inventoryItemIdLabel,inventoryItemNameLabel,inventoryItemPriceLabel,inventoryItemCategoryLabel,titleLabel;
    @FXML
    private Label inventoryItemIdRequiredLabel,inventoryItemNameRequiredLabel,inventoryItemPriceRequiredLabel,inventoryItemCategoryRequiredLabel;
    @FXML
    private TextField inventoryItemIdTextField,inventoryItemNameTextField,inventoryItemPriceTextField,inventorySearchTextField;
    @FXML
    private ComboBox<Inventory> inventoryItemCategoryComboBox;
    @FXML
    private TableView<Inventory> displayInventoryTable;
    @FXML
    private TableColumn<Inventory, Integer> inventoryItemId;
    @FXML
    private TableColumn<Inventory, String> inventoryItemName;
    @FXML
    private TableColumn<Inventory, Double> inventoryItemPrice;
    @FXML
    private TableColumn<Inventory, String> inventoryItemCategory;


    @FXML
    private AnchorPane inventoryPane;


    ObservableList<Inventory> displayInventory = FXCollections.observableArrayList();

    private String[] category = {"Breakfast","Mains","Burgers","Sides","Desserts","Beverages"};


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List categoryList = new ArrayList();
        for(String category: category){
            categoryList.add(category);
        }
        ObservableList listTime = FXCollections.observableArrayList(categoryList);
        inventoryItemCategoryComboBox.setItems(listTime);


        inventoryItemId.setCellValueFactory(new PropertyValueFactory<Inventory,Integer>("inventoryItemId"));
        inventoryItemName.setCellValueFactory(new PropertyValueFactory<Inventory,String>("inventoryItemName"));
        inventoryItemPrice.setCellValueFactory(new PropertyValueFactory<Inventory,Double>("inventoryItemPrice"));
        inventoryItemCategory.setCellValueFactory(new PropertyValueFactory<Inventory,String>("inventoryItemCategory"));
    }

    public void createInventoryButtonOnAction(ActionEvent event){
        if(inventoryItemIdTextField.getText().isBlank()==false && inventoryItemNameTextField.getText().isBlank()==false && inventoryItemPriceTextField.getText().isBlank()==false && inventoryItemCategoryComboBox.toString().isBlank()==false){
            validateInventory();
        }
        else{
            inventoryItemIdRequiredLabel.setText("required *");
            inventoryItemNameRequiredLabel.setText("required *");
            inventoryItemPriceRequiredLabel.setText("required *");
            inventoryItemCategoryRequiredLabel.setText("required *");

            inventoryItemIdTextField.setText("");
            inventoryItemNameTextField.setText("");
            inventoryItemPriceTextField.setText("");
            inventoryItemCategoryComboBox.getSelectionModel().clearSelection();
        }
    }
    public void updateInventoryButtonOnAction(ActionEvent event){
        Database database = new Database();
        Connection connection = database.getConnection();

        String updateInventory = "UPDATE inventory SET inventoryItemId = '"+inventoryItemIdTextField.getText()+"', inventoryItemName = '"+inventoryItemNameTextField.getText()+"', inventoryItemPrice = '"+inventoryItemPriceTextField.getText()+"', inventoryItemCategory = '"+inventoryItemCategoryComboBox.getSelectionModel().getSelectedItem()+"' WHERE inventoryItemId = '"+inventoryItemIdTextField.getText()+"'";

        try{
            Statement statement = connection.createStatement();
            statement.executeUpdate(updateInventory);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Message");
            alert.setHeaderText(null);
            alert.setContentText("Inventory was successfully updated !");
            alert.showAndWait();

            inventoryItemIdTextField.setText("");
            inventoryItemNameTextField.setText("");
            inventoryItemPriceTextField.setText("");
            inventoryItemCategoryComboBox.getSelectionModel().clearSelection();

            displayInventory.clear();
        }
        catch(Exception ex){
            ex.printStackTrace();
            ex.getCause();
        }
    }
    public void cancelButtonOnAction(ActionEvent event){
        inventoryItemIdRequiredLabel.setText("");
        inventoryItemNameRequiredLabel.setText("");
        inventoryItemPriceRequiredLabel.setText("");
        inventoryItemCategoryRequiredLabel.setText("");

        inventoryItemIdTextField.setText("");
        inventoryItemNameTextField.setText("");
        inventoryItemPriceTextField.setText("");
        inventoryItemCategoryComboBox.getSelectionModel().clearSelection();
    }
    public void inventoryShowButtonOnAction(ActionEvent event){
        Database database = new Database();
        Connection connection = database.getConnection();

        String showInventory = "SELECT * FROM inventory";

        displayInventory.clear();

        try{
            Statement statement = connection.createStatement();
            ResultSet queryResult = statement.executeQuery(showInventory);

            while(queryResult.next()){
                displayInventory.add(new Inventory(queryResult.getInt("inventoryItemId"),queryResult.getString("inventoryItemName"),queryResult.getDouble("inventoryItemPrice"),queryResult.getString("inventoryItemCategory")));
                displayInventoryTable.setItems(displayInventory);
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
            ex.getCause();
        }
    }
    public void deleteInventoryButtonOnAction(ActionEvent event){
        Database database = new Database();
        Connection connection = database.getConnection();

        String deleteReservation = "DELETE FROM inventory WHERE inventoryItemId = '" + inventoryItemIdTextField.getText() + "'";

        Inventory deleteSelectedIndex = displayInventoryTable.getSelectionModel().getSelectedItem();
        displayInventoryTable.getSelectionModel().getSelectedIndex();
        displayInventory.remove(deleteSelectedIndex);

        try{
            Statement statement = connection.createStatement();
            statement.executeUpdate(deleteReservation);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Message");
            alert.setHeaderText(null);
            alert.setContentText("Inventory was successfully deleted !");
            alert.showAndWait();

            inventoryItemIdTextField.setText("");
            inventoryItemNameTextField.setText("");
            inventoryItemPriceTextField.setText("");
            inventoryItemCategoryComboBox.getSelectionModel().clearSelection();
        }
        catch(Exception ex){
            ex.printStackTrace();
            ex.getCause();
        }
    }
    public void clearTableInventoryButtonOnAction(ActionEvent event){
        inventoryItemIdTextField.setText("");
        inventoryItemNameTextField.setText("");
        inventoryItemPriceTextField.setText("");
        inventoryItemCategoryComboBox.getSelectionModel().clearSelection();

        displayInventory.clear();
    }
    public void searchInventoryButtonOnAction(ActionEvent event){
        Database database = new Database();
        Connection connection = database.getConnection();

        String searchInventory = "SELECT * FROM inventory WHERE inventoryItemId = '" + inventorySearchTextField.getText() + "' OR inventoryItemName = '" + inventorySearchTextField.getText() + "'OR inventoryItemPrice = '" + inventorySearchTextField.getText() + "'OR inventoryItemCategory = '" + inventorySearchTextField.getText() + "'";

        displayInventory.clear();

        try{
            Statement statement = connection.createStatement();
            ResultSet queryResult = statement.executeQuery(searchInventory);

            while(queryResult.next()){
                displayInventory.add(new Inventory(queryResult.getInt("inventoryItemId"), queryResult.getString("inventoryItemName"), queryResult.getDouble("inventoryItemPrice"), queryResult.getString("inventoryItemCategory")));
                displayInventoryTable.setItems(displayInventory);
            }
            inventorySearchTextField.setText("");
        }
        catch(Exception ex){
            ex.printStackTrace();
            ex.getCause();
        }
    }

    public void inventorySelected(){
        Inventory selectedInventory = displayInventoryTable.getSelectionModel().getSelectedItem();
        displayInventoryTable.getSelectionModel().getSelectedIndex();

        inventoryItemIdTextField.setText(String.valueOf(selectedInventory.getInventoryItemId()));
        inventoryItemNameTextField.setText(selectedInventory.getInventoryItemName());
        inventoryItemPriceTextField.setText(selectedInventory.getInventoryItemPrice().toString());
    }
    public void validateInventory(){
        Database database = new Database();
        Connection connection = database.getConnection();

        String inventoryItemId = inventoryItemIdTextField.getText();
        String inventoryItemName = inventoryItemNameTextField.getText();
        String inventoryItemPrice = inventoryItemPriceTextField.getText();
        String inventoryItemCategory = String.valueOf(inventoryItemCategoryComboBox.getSelectionModel().getSelectedItem());

        String captureReservationData = "INSERT INTO inventory values ('"+inventoryItemId+"','"+inventoryItemName+"','"+inventoryItemPrice+"','"+inventoryItemCategory+"')";

        try{
            Statement statement = connection.createStatement();
            statement.executeUpdate(captureReservationData);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Message");
            alert.setHeaderText(null);
            alert.setContentText("Inventory successfully added !");
            alert.showAndWait();

            inventoryItemIdRequiredLabel.setText("");
            inventoryItemNameRequiredLabel.setText("");
            inventoryItemPriceRequiredLabel.setText("");
            inventoryItemCategoryRequiredLabel.setText("");

            inventoryItemIdTextField.setText("");
            inventoryItemNameTextField.setText("");
            inventoryItemPriceTextField.setText("");
            inventoryItemCategoryComboBox.getSelectionModel().clearSelection();
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

        inventoryPane.getScene().getWindow().hide();
    }
}
