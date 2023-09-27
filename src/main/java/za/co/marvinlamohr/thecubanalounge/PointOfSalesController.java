package za.co.marvinlamohr.thecubanalounge;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.JobSettings;
import javafx.print.PageLayout;
import javafx.print.PrinterJob;
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
import java.util.Optional;
import java.util.ResourceBundle;

public class PointOfSalesController implements Initializable {

    @FXML
    private Button addToOrderButton, deleteOrderButton, removeItemButton, confirmPaymentButton, printButton, displayButton, closeButton;

    @FXML
    private Button breakfastButton, mainsButton, burgersButton, sidesButton, dessertButton, beveragesButton;

    @FXML
    private RadioButton cashRadioButton, creditRadioButton, debitRadioButton, voucherRadioButton;

    @FXML
    private ToggleGroup paymentMethod;

    @FXML
    private Label messageLabel, transactionLabel, menuLabel, orderLabel, recieptLabel, paymentLabel;

    @FXML
    private TableView<Inventory> viewItemsDisplayTable;
    @FXML
    private TableColumn<Inventory, Integer> itemId;

    @FXML
    private TableColumn<Inventory, String> itemName;

    @FXML
    private TableColumn<Inventory, Double> itemPrice;

    @FXML
    private TableView<Inventory> orderItemesDisplayTable;

    @FXML
    private TableColumn<Inventory, Integer> itemId2;

    @FXML
    private TableColumn<Inventory, String> itemName2;

    @FXML
    private TableColumn<Inventory, Double> itemPrice2;

    @FXML
    private TextField totalTextField, paymentTextField, changeTextField;
    @FXML
    private TextArea recieptDisplayTextArea;

    @FXML
    private AnchorPane posPane;

    ObservableList<Inventory> displayMenu = FXCollections.observableArrayList();
    ObservableList<Inventory> orderMenu = FXCollections.observableArrayList();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        itemId.setCellValueFactory(new PropertyValueFactory<Inventory,Integer>("inventoryItemId"));
        itemName.setCellValueFactory(new PropertyValueFactory<Inventory,String>("inventoryItemName"));
        itemPrice.setCellValueFactory(new PropertyValueFactory<Inventory,Double>("inventoryItemPrice"));

        itemId2.setCellValueFactory(new PropertyValueFactory<Inventory,Integer>("inventoryItemId"));
        itemName2.setCellValueFactory(new PropertyValueFactory<Inventory,String>("inventoryItemName"));
        itemPrice2.setCellValueFactory(new PropertyValueFactory<Inventory,Double>("inventoryItemPrice"));
    }


    public void breakfastButtonOnAction(ActionEvent event){
        Database database = new Database();
        Connection connection = database.getConnection();

        String breakfastMenu = "SELECT * FROM inventory WHERE inventoryItemCategory = 'Breakfast'";

        displayMenu.clear();

        try{
            Statement statement = connection.createStatement();
            ResultSet queryResult = statement.executeQuery(breakfastMenu);

            while(queryResult.next()){
                displayMenu.add(new Inventory(queryResult.getInt("inventoryItemId"), queryResult.getString("inventoryItemName"), queryResult.getDouble("inventoryItemPrice"),queryResult.getString("inventoryItemCategory")));
                viewItemsDisplayTable.setItems(displayMenu);
            }

        }catch(Exception ex){
            ex.printStackTrace();
            ex.getCause();
        }
    }
    public void mainsButtonOnAction(ActionEvent event){
        Database database = new Database();
        Connection connection = database.getConnection();

        String mainsMenu = "SELECT * FROM inventory WHERE inventoryItemCategory = 'Mains'";

        displayMenu.clear();

        try{
            Statement statement = connection.createStatement();
            ResultSet queryResult = statement.executeQuery(mainsMenu);

            while(queryResult.next()){
                displayMenu.add(new Inventory(queryResult.getInt("inventoryItemId"), queryResult.getString("inventoryItemName"), queryResult.getDouble("inventoryItemPrice"),queryResult.getString("inventoryItemCategory")));
                viewItemsDisplayTable.setItems(displayMenu);
            }

        }catch(Exception ex){
            ex.printStackTrace();
            ex.getCause();
        }
    }
    public void burgersButtonOnAction(ActionEvent event){
        Database database = new Database();
        Connection connection = database.getConnection();

        String burgersMenu = "SELECT * FROM inventory WHERE inventoryItemCategory = 'Burgers'";

        displayMenu.clear();

        try{
            Statement statement = connection.createStatement();
            ResultSet queryResult = statement.executeQuery(burgersMenu);

            while(queryResult.next()){
                displayMenu.add(new Inventory(queryResult.getInt("inventoryItemId"), queryResult.getString("inventoryItemName"), queryResult.getDouble("inventoryItemPrice"),queryResult.getString("inventoryItemCategory")));
                viewItemsDisplayTable.setItems(displayMenu);
            }

        }catch(Exception ex){
            ex.printStackTrace();
            ex.getCause();
        }
    }
    public void sidesButtonOnAction(ActionEvent event){
        Database database = new Database();
        Connection connection = database.getConnection();

        String sidesMenu = "SELECT * FROM inventory WHERE inventoryItemCategory = 'Sides'";

        displayMenu.clear();

        try{
            Statement statement = connection.createStatement();
            ResultSet queryResult = statement.executeQuery(sidesMenu);

            while(queryResult.next()){
                displayMenu.add(new Inventory(queryResult.getInt("inventoryItemId"), queryResult.getString("inventoryItemName"), queryResult.getDouble("inventoryItemPrice"),queryResult.getString("inventoryItemCategory")));
                viewItemsDisplayTable.setItems(displayMenu);
            }

        }catch(Exception ex){
            ex.printStackTrace();
            ex.getCause();
        }
    }
    public void dessertButtonOnAction(ActionEvent event){
        Database database = new Database();
        Connection connection = database.getConnection();

        String dessertMenu = "SELECT * FROM inventory WHERE inventoryItemCategory = 'Desserts'";

        displayMenu.clear();

        try{
            Statement statement = connection.createStatement();
            ResultSet queryResult = statement.executeQuery(dessertMenu);

            while(queryResult.next()){
                displayMenu.add(new Inventory(queryResult.getInt("inventoryItemId"), queryResult.getString("inventoryItemName"), queryResult.getDouble("inventoryItemPrice"),queryResult.getString("inventoryItemCategory")));
                viewItemsDisplayTable.setItems(displayMenu);
            }

        }catch(Exception ex){
            ex.printStackTrace();
            ex.getCause();
        }
    }
    public void beveragesButtonOnAction(ActionEvent event){
        Database database = new Database();
        Connection connection = database.getConnection();

        String beveragesMenu = "SELECT * FROM inventory WHERE inventoryItemCategory = 'Beverages'";

        displayMenu.clear();

        try{
            Statement statement = connection.createStatement();
            ResultSet queryResult = statement.executeQuery(beveragesMenu);

            while(queryResult.next()){
                displayMenu.add(new Inventory(queryResult.getInt("inventoryItemId"), queryResult.getString("inventoryItemName"), queryResult.getDouble("inventoryItemPrice"),queryResult.getString("inventoryItemCategory")));
                viewItemsDisplayTable.setItems(displayMenu);
            }

        }catch(Exception ex){
            ex.printStackTrace();
            ex.getCause();
        }
    }

    public void addToOrderButtonOnAction(ActionEvent event){
        Inventory selectedItem = viewItemsDisplayTable.getSelectionModel().getSelectedItem();

        if(selectedItem != null){
            orderMenu.add(new Inventory(selectedItem.getInventoryItemId(),selectedItem.getInventoryItemName(),selectedItem.getInventoryItemPrice(),selectedItem.getInventoryItemCategory()));
            orderItemesDisplayTable.setItems(orderMenu);

            calculateTotal();
        }
    }

    public void deleteOrderButtonOnAction(ActionEvent event){
        orderMenu.clear();

        calculateTotal();
    }

    public void removeItemButtonOnAction(ActionEvent event){
        Inventory orderItem = orderItemesDisplayTable.getSelectionModel().getSelectedItem();
        orderMenu.remove(orderItem);

        calculateTotal();
    }

    public void paymentMethodRadioButtonOnAction(ActionEvent event){
        double total = Double.parseDouble(totalTextField.getText());

        if(cashRadioButton.isSelected()){
            transactionLabel.setText(cashRadioButton.getText());
            paymentTextField.setText("");
        }
        else if(creditRadioButton.isSelected()){
            transactionLabel.setText(creditRadioButton.getText());
            paymentTextField.setText(String.valueOf(total));
        }
        else if(debitRadioButton.isSelected()){
            transactionLabel.setText(debitRadioButton.getText());
            paymentTextField.setText(String.valueOf(total));
        }
        else if(voucherRadioButton.isSelected()){
            transactionLabel.setText(voucherRadioButton.getText());
            paymentTextField.setText(String.valueOf(total));
        }
        else{
            transactionLabel.setText("");
        }
    }
    public void confirmPaymentButtonOnAction(ActionEvent event){
        validatePayment();
    }

    public void printButtonOnAction(ActionEvent event){
        PrinterJob printerJob = PrinterJob.createPrinterJob();

        boolean printProceed = printerJob.showPrintDialog(printButton.getScene().getWindow());

        JobSettings printSettings = printerJob.getJobSettings();

        PageLayout pageLayout = printSettings.getPageLayout();

        double pageWidth = pageLayout.getPrintableWidth();
        double pageHeight = pageLayout.getPrintableHeight();

        TextArea reciept = new TextArea(recieptDisplayTextArea.getText());
        reciept.setPrefSize(pageWidth,pageHeight);
        reciept.setWrapText(true);

        try{
            if(printProceed){
                boolean printedJob = printerJob.printPage(reciept);

                if(printedJob){
                    printerJob.endJob();

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Reciept printed successfully!");
                    alert.showAndWait();

                    changeTextField.setText("");
                    paymentMethod.getSelectedToggle().setSelected(false);
                    messageLabel.setText("");
                    transactionLabel.setText("");
                    recieptDisplayTextArea.clear();
                }
                else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Receipt print failed!");
                    alert.showAndWait();
                }
            }
        }catch(Exception ex){
          ex.printStackTrace();
          ex.getCause();
        }

    }
    public void validatePayment() {
        Database database = new Database();
        Connection connection = database.getConnection();

        double total = Double.parseDouble(totalTextField.getText());
        double payment = Double.parseDouble(paymentTextField.getText());

        String credit = "1111";
        String debit = "2222";
        String voucher = "123456789";

        try {
            if (cashRadioButton.isSelected()) {
                if (payment >= total) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Payment successful, Thank you for the support!");
                    alert.showAndWait();

                    calculateChange();
                    bill();

                    displayMenu.clear();
                    orderMenu.clear();
                    totalTextField.setText("");
                    paymentTextField.setText("");
                    messageLabel.setText("Approved!");
                }
                else if (payment < total) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Payment unsuccessful, Insufficient funds check total!");
                    alert.showAndWait();

                    paymentTextField.setText("");
                    messageLabel.setText("Declined!");
                }
            }
            else if (creditRadioButton.isSelected()) {
                TextInputDialog inputCreditDialog = new TextInputDialog();
                inputCreditDialog.getDialogPane();
                inputCreditDialog.setTitle("Information Message");
                inputCreditDialog.setHeaderText(null);
                inputCreditDialog.setContentText("Please enter pin: ");

                Optional<String> result = inputCreditDialog.showAndWait();
                TextField input = inputCreditDialog.getEditor();

                    if (input.getText().toString().equals(credit)) {
                        calculateChange();
                        bill();

                        totalTextField.setText("");
                        paymentTextField.setText("");
                        displayMenu.clear();
                        orderMenu.clear();
                        messageLabel.setText("Approved!");
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Incorrect pin number entered!");
                        alert.showAndWait();

                        messageLabel.setText("Declined!");
                    }
            }
            else if (debitRadioButton.isSelected()) {
                TextInputDialog inputDebitDialog = new TextInputDialog();
                inputDebitDialog.setTitle("Information Message");
                inputDebitDialog.setHeaderText(null);
                inputDebitDialog.setContentText("Please enter pin: ");

                Optional<String> result = inputDebitDialog.showAndWait();
                TextField input = inputDebitDialog.getEditor();

                    if (input.getText().toString().equals(debit)) {
                        calculateChange();
                        bill();

                        totalTextField.setText("");
                        paymentTextField.setText("");
                        displayMenu.clear();
                        orderMenu.clear();
                        messageLabel.setText("Approved!");
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Incorrect pin number entered!");
                        alert.showAndWait();

                        messageLabel.setText("Declined!");
                    }
            } else if (voucherRadioButton.isSelected()) {
                TextInputDialog inputVoucherDialog = new TextInputDialog();
                inputVoucherDialog.setTitle("Information Message");
                inputVoucherDialog.setHeaderText(null);
                inputVoucherDialog.setContentText("Please enter pin: ");

                Optional<String> result = inputVoucherDialog.showAndWait();
                TextField input = inputVoucherDialog.getEditor();

                    if (input.getText().toString().equals(voucher)) {
                        calculateChange();
                        bill();

                        totalTextField.setText("");
                        paymentTextField.setText("");
                        displayMenu.clear();
                        orderMenu.clear();
                        messageLabel.setText("Approved!");
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Incorrect voucher number entered!");
                        alert.showAndWait();

                        messageLabel.setText("Declined!");
                    }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            ex.getCause();
        }
    }
    public void calculateTotal(){
        double totalDue = 0.0;

        for(int i=0; i<orderItemesDisplayTable.getItems().size(); i++){
            double calculateTotal = Double.valueOf(String.valueOf(orderItemesDisplayTable.getColumns().get(2).getCellObservableValue(i).getValue()));
            totalDue = calculateTotal + totalDue;
        }
        totalTextField.setText(Double.toString(totalDue));
    }
    public void calculateChange(){
        double total = Double.parseDouble(totalTextField.getText());
        double payment = Double.parseDouble(paymentTextField.getText());

        double change = payment - total;
        changeTextField.setText(Double.toString(change));
    }

    public void bill(){
        double total = Double.parseDouble(totalTextField.getText());
        double payment = Double.parseDouble(paymentTextField.getText());
        double change = Double.parseDouble(changeTextField.getText());
        String transaction = transactionLabel.getText();

        recieptDisplayTextArea.clear();

        recieptDisplayTextArea.setText(recieptDisplayTextArea.getText()+"*********************************************************\n");
        recieptDisplayTextArea.setText(recieptDisplayTextArea.getText()+"       ************ The Cubana Lounge ************       \n");
        recieptDisplayTextArea.setText(recieptDisplayTextArea.getText()+"*********************************************************\n");
        recieptDisplayTextArea.setText(recieptDisplayTextArea.getText()+"                            Tel: 021 345 7492                    \n");
        recieptDisplayTextArea.setText(recieptDisplayTextArea.getText()+"            Email: thecubanalounge@outlook.com           \n");
        recieptDisplayTextArea.setText(recieptDisplayTextArea.getText()+"              Address: 23 Long Street, Cape Town           \n");
        recieptDisplayTextArea.setText(recieptDisplayTextArea.getText()+"                     www.thecubanalounge.com                 \n");
        recieptDisplayTextArea.setText(recieptDisplayTextArea.getText()+"===================================\n");
        recieptDisplayTextArea.setText(recieptDisplayTextArea.getText()+"   -----Item-----                                     -----Price-----\n");
        recieptDisplayTextArea.setText(recieptDisplayTextArea.getText()+"===================================\n");
        recieptDisplayTextArea.setText(recieptDisplayTextArea.getText()+"\n");

        for(int i=0; i< orderItemesDisplayTable.getItems().size(); i++){
            String item = String.valueOf(String.valueOf(orderItemesDisplayTable.getColumns().get(1).getCellObservableValue(i).getValue()));
            double price = Double.valueOf(String.valueOf(orderItemesDisplayTable.getColumns().get(2).getCellObservableValue(i).getValue()));

            recieptDisplayTextArea.setText(recieptDisplayTextArea.getText()+item+"\t"+"                                         "+price+"\n");
        }

        recieptDisplayTextArea.setText(recieptDisplayTextArea.getText()+"\n");
        recieptDisplayTextArea.setText(recieptDisplayTextArea.getText()+"Total: R "+total+"\n");
        recieptDisplayTextArea.setText(recieptDisplayTextArea.getText()+"Payment: R "+payment+"\n");
        recieptDisplayTextArea.setText(recieptDisplayTextArea.getText()+"Change: R "+change+"\n");
        recieptDisplayTextArea.setText(recieptDisplayTextArea.getText()+"\n");
        recieptDisplayTextArea.setText(recieptDisplayTextArea.getText()+"Transaction: "+transaction+"\n");
        recieptDisplayTextArea.setText(recieptDisplayTextArea.getText()+"\n");
        recieptDisplayTextArea.setText(recieptDisplayTextArea.getText()+"*********************************************************\n");
        recieptDisplayTextArea.setText(recieptDisplayTextArea.getText()+"     ************ Thank You  Come Again ************     \n");
        recieptDisplayTextArea.setText(recieptDisplayTextArea.getText()+"*********************************************************\n");
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

        posPane.getScene().getWindow().hide();
    }
}
