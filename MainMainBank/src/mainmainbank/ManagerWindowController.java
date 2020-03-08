/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainmainbank;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.AbstractList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;

import javax.swing.*;

/**
 * FXML Controller class
 *
 * @author Randel Reyes
 */
public class ManagerWindowController extends Bank implements Initializable {

    private final int fieldsSize = 10;
    //for welcome message
    @FXML private Label session_username;
    @FXML private Label passStatus;

    //for setters (tab)
    @FXML private TextField setInitialDeposit;
    @FXML private TextField setMaintainingBalance;
    @FXML private TextField setInitialCredit;
    @FXML private TextField setMinWithdraw;
    @FXML private TextField setInterest;
    @FXML private TextField setMaxWithdraw;
    
    //for creation of client (tab)
    @FXML private TextField accountID;
    @FXML private TextField firstname;
    @FXML private TextField middlename;
    @FXML private TextField lastname;
    @FXML private TextField extensioname;
    @FXML private DatePicker bday;
    @FXML private ComboBox<String> gender;
    @FXML private TextField address;
    @FXML private TextField maritalstatus;
    @FXML private TextField citizenship;
    
    //for teller tab
    @FXML private TextField tellerID;
    @FXML private TextField tellerFirstname;
    @FXML private TextField tellerMiddlename;
    @FXML private TextField tellerLastname;
    @FXML private TextField tellerExtension;
    @FXML private DatePicker tellerBday;
    @FXML private ComboBox<String> tellerGender;
    @FXML private TextField tellerAddress;
    @FXML private TextField tellerMaritalStatus;
    @FXML private TextField tellerCitizenship;
    @FXML private TextField tbUsername;
    @FXML private PasswordField tbPassword;
    @FXML private PasswordField tbRetypePassword;

    //for look account tab teller
    private final ObservableList<Teller> tellers = FXCollections.observableArrayList();
    @FXML private TextField omniBox;
    @FXML private TableView<Teller> tableTeller;
    @FXML private TableColumn<Teller, Integer> tvAccountID;
    @FXML private TableColumn<Teller, String> tvFirstName;
    @FXML private TableColumn<Teller, String> tvMiddlename;
    @FXML private TableColumn<Teller, String> tbLastName;
    @FXML private TableColumn<Teller, String> tbAddress;
    @FXML private TableColumn<Teller, Integer> tvAge;
    @FXML private TableColumn<Teller, Void> col_Update;
    @FXML private TableColumn<Teller, Void> col_remove;
    @FXML private TableColumn<Teller, Void> col_Peek;

    //for look account tab client
    private final ObservableList<Client> clients = FXCollections.observableArrayList();
    @FXML private TextField omniBox1;
    @FXML private TableView<Client> tableClient;
    @FXML private TableColumn<Client, Integer> col_clientID;
    @FXML private TableColumn<Client, String> col_clientFirstName;
    @FXML private TableColumn<Client, String> col_clientMiddleName;
    @FXML private TableColumn<Client, String> col_clientLastName;
    @FXML private TableColumn<Client, Double> col_clientSavings;
    @FXML private TableColumn<Client, Double> col_clientDebt;
    @FXML private TableColumn<Client, Void> col_remove1;
    @FXML private TableColumn<Client, Void> col_Peek1;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */

    private String[] clientFields = new String[fieldsSize];//client fields
    private String[] tellerFields = new String[12]; //teller fields
    private final ObservableList<String> genders = FXCollections.observableArrayList("Male","Female","Rather not to state");
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initGenders();
        initSettings();
        initDefault();
        initGeneratorID();

    }

    private void initSettings(){
        session_username.setText(Bank.getSESSION_USERNAME());
        setInitialDeposit.setText(String.valueOf(Bank.getINITIAL_DEPOSIT()));
        setMaintainingBalance.setText(String.valueOf(Bank.getMAINTAINING_BALANCE()));
        setInitialCredit.setText(String.valueOf(Bank.getINITIAL_CREDIT()));
        setMinWithdraw.setText(String.valueOf(Bank.getMIN_WITHDRWAl()));
        setInterest.setText(String.valueOf(Bank.getINTEREST()));
        setMaxWithdraw.setText(String.valueOf(Bank.getMAX_WITHDRAWAL()));
    }
    
    private void initGenders(){
        gender.setItems(genders);
        tellerGender.setItems(genders);
    }
    
    private void initDefault(){
        extensioname.setText(" ");//initialize the value client
        tellerExtension.setText(" ");//initialize the value teller
    }
    
    private void initGeneratorID(){
        accountID.setText(String.valueOf(super.generateClientID()));
        tellerID.setText(String.valueOf(super.generateTellerID()));
    }

    //save button for bank settings
    @FXML
    void saveSetters(ActionEvent event) {
        
        try{
            Bank.setINITIAL_CREDIT(Double.parseDouble(setInitialDeposit.getText()));
            Bank.setMAINTAINING_BALANCE(Double.parseDouble(setMaintainingBalance.getText()));
            Bank.setINITIAL_DEPOSIT(Double.parseDouble(setInitialCredit.getText()));
            Bank.setMIN_WITHDRWAl(Double.parseDouble(setMinWithdraw.getText()));
            Bank.setMAX_WITHDRAWAL(Double.parseDouble(setMaxWithdraw.getText()));
            Bank.setINTEREST(Double.parseDouble(setInterest.getText()));
            JOptionPane.showMessageDialog(null, "All setters are saved in the system", "Saved",
                            JOptionPane.INFORMATION_MESSAGE);
        }
        catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Invalid setters", "Invalid",
                            JOptionPane.OK_OPTION);
        }
        
    }
    
    //for creation of client tab
    @FXML
    void SaveClientAccount(ActionEvent event) {
             
        try{
            fields(clientFields, accountID, firstname, middlename, lastname, extensioname, bday, gender, address, maritalstatus, citizenship);

            if(gender.getValue() == null)
                throw new InputMismatchException();

            for(String field: clientFields){
                if(field.isEmpty())//if empty error is empty field
                    throw new InputMismatchException();
            }

            String status = super.createAccountClient(clientFields);

            if(status.equals("Declined"))//when client age is < 18
                throw new IllegalArgumentException();
            
            JOptionPane.showMessageDialog(null, "Successfully Added", "Saved",
                            JOptionPane.INFORMATION_MESSAGE);
            
            accountID.setText(String.valueOf(super.generateClientID()));//creates new client ID
        }
        
        catch(NumberFormatException e){//bday error
            JOptionPane.showMessageDialog(null, "Birthdate is invalid", "Invalid",
                            JOptionPane.ERROR_MESSAGE);
        }
        catch(IllegalArgumentException e){//if age is < 18
            JOptionPane.showMessageDialog(null, "Client age must be over 18", "Declined",
                            JOptionPane.ERROR_MESSAGE);
        }
        catch(InputMismatchException e){//empty fields
            JOptionPane.showMessageDialog(null, "Please input all fields", "Empty Field(s)",
                            JOptionPane.ERROR_MESSAGE);
        }
        
    }

    //for creation of teller account
    @FXML
    void checkPassword(KeyEvent event) throws AWTException {
        Robot robot = new Robot();//simulates press of f3
        robot.keyPress(114); //f3 key

        if(tbRetypePassword.getText().equals(tbPassword.getText())){
            passStatus.setText("Password match");
        }else{
            passStatus.setText("Password not match");
        }
    }

    @FXML
    void setupUsername(MouseEvent event) {
        //default username is first name and last name
        String fname = tellerFirstname.getText().substring(0, 1).toUpperCase() + tellerFirstname.getText().substring(1);
        String lname =  tellerLastname.getText().substring(0, 1).toUpperCase() + tellerLastname.getText().substring(1);
        tbUsername.setText(fname.concat(lname)); //sets the textfield
    }

    @FXML
    void SaveTellerAccount(ActionEvent event) {
        try {

            fields(tellerFields, tellerID, tellerFirstname, tellerMiddlename, tellerLastname, tellerExtension, tellerBday, tellerGender, tellerAddress, tellerMaritalStatus, tellerCitizenship);
            tellerFields[10] = tbUsername.getText();
            tellerFields[11] = tbRetypePassword.getText();

            if (tellerGender.getValue() == null)
                throw new InputMismatchException();

            for (String field : tellerFields) {
                if (field.isEmpty())//if empty error is empty field
                    throw new InputMismatchException();
            }

            String status = super.createAccoutTeller(tellerFields);

            if (status.equals("Declined"))//when teller age is < 20
                throw new IllegalArgumentException();

            JOptionPane.showMessageDialog(null, "Successfully Added", "Saved",
                    JOptionPane.INFORMATION_MESSAGE);

            tellerID.setText(String.valueOf(super.generateTellerID()));//creates new tellerID
        } catch (NumberFormatException e) {//birthday error
            JOptionPane.showMessageDialog(null, "Birth date is invalid", "Invalid",
                    JOptionPane.ERROR_MESSAGE);
        } catch (InputMismatchException e) {//empty fields
            JOptionPane.showMessageDialog(null, "Please input all fields", "Empty Field(s)",
                    JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException e) {//if age is < 18
            JOptionPane.showMessageDialog(null, "Teller age must be over 20", "Declined",
                    JOptionPane.ERROR_MESSAGE);
        }

    }

    private void fields(String[] tellerFields, TextField tellerID, TextField tellerFirstname, TextField tellerMiddlename, TextField tellerLastname, TextField tellerExtension, DatePicker tellerBday, ComboBox<String> tellerGender, TextField tellerAddress, TextField tellerMaritalStatus, TextField tellerCitizenship) {
        tellerFields[0] = tellerID.getText();
        tellerFields[1] = tellerFirstname.getText();
        tellerFields[2] = tellerMiddlename.getText();
        tellerFields[3] = tellerLastname.getText();
        tellerFields[4] = tellerExtension.getText();
        tellerFields[5] = String.valueOf(tellerBday.getValue());
        tellerFields[6] = String.valueOf(tellerGender.getValue());
        tellerFields[7] = tellerAddress.getText();
        tellerFields[8] = tellerMaritalStatus.getText();
        tellerFields[9] = tellerCitizenship.getText();
    }

    /* look for teller account */
    private void initColsTeller(){
        tvAccountID.setCellValueFactory(new PropertyValueFactory<>("tellerID"));
        tvFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        tvMiddlename.setCellValueFactory(new PropertyValueFactory<>("middleName"));
        tbLastName.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        tbAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        tvAge.setCellValueFactory(new PropertyValueFactory<>("age"));
    }

    private void populateTellerTable(){ //add data in the table
        for(Teller tel: Bank.ldTeller){
            tellers.add(new Teller(tel.getTellerID(), tel.getFirstName(), tel.getMiddleName(),
                    tel.getLastname() + " " + tel.getExtensionName(), tel.getAddress(), tel.getAge()));
        }
        tableTeller.setItems(tellers);
    }

    private void refreshDataTeller(){ //will load again the table data
        this.removeListTeller(); //removes the teller table items
        tellers.clear(); //clears the observable list -> tellers
        this.populateTellerTable(); //will fill data in the table
        this.searchTeller(); //search through a specific predicate (eg. ID, name)
    }

    @FXML
    void refreshTableTeller(ActionEvent event) {
        this.initColsTeller();
        if(!Bank.ldTeller.isEmpty()){
            this.updateTeller(); //add update button
            this.removeTeller(); //add remove button
            this.peekTeller(); //peek button
        }
        this.refreshDataTeller();
        //this.searchTeller();
    }

    private void updateTeller(){
        Callback<TableColumn<Teller, Void>, TableCell<Teller, Void>> cellFactory = ( TableColumn<Teller, Void> param) -> {
             TableCell<Teller, Void> cell = new TableCell<Teller, Void>() {

                private final Button btnUpdate = new Button("Update");
                {
                    btnUpdate.setOnAction((ActionEvent event) -> {
                        Teller tel = getTableView().getItems().get(getIndex());
                        //System.out.println(tel.getTellerID());
                        Bank.setSessionBackId(String.valueOf(tel.getTellerID()));
                        try {
                            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("UpdateWindow.fxml"));
                            Parent root1 = (Parent) fxmlLoader.load();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(root1));
                            stage.setResizable(false);
                            stage.show();
                        } catch(Exception e) {
                            e.printStackTrace();
                        }
                    });
                }

                @Override
                public void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(btnUpdate);
                    }
                }
            };
            return cell;
        };

        col_Update.setCellFactory(cellFactory);
    }

    private void removeTeller(){

        Callback<TableColumn<Teller, Void>, TableCell<Teller, Void>> cellFactory = ( TableColumn<Teller, Void> param) -> {
            TableCell<Teller, Void> cell = new TableCell<Teller, Void>() {

                private final Button btnRemove = new Button("Remove");
                {
                    btnRemove.setOnAction((ActionEvent event) -> {
                        Teller tel = getTableView().getItems().get(getIndex());
                        System.out.println(tel.getTellerID());
                        ManagerWindowController.super.removeTellerACcount(String.valueOf(tel.getTellerID())); //remove the teller account
                        JOptionPane.showMessageDialog(null, tel.getFirstName() + " " + tel.getLastname() +  " was removed, please refresh the table", "Success",
                                JOptionPane.INFORMATION_MESSAGE);
                    });
                }

                @Override
                public void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(btnRemove);
                    }
                }
            };
            return cell;
        };

        col_remove.setCellFactory(cellFactory);
    }

    private void peekTeller(){

        Callback<TableColumn<Teller, Void>, TableCell<Teller, Void>> cellFactory = ( TableColumn<Teller, Void> param) -> {
            TableCell<Teller, Void> cell = new TableCell<Teller, Void>() {

                private final Button btnPeek = new Button("Peek");
                {
                    btnPeek.setOnAction((ActionEvent event) -> {
                        Teller tel = getTableView().getItems().get(getIndex());
                        System.out.println(tel.getTellerID());
                        Bank.setSessionBackId(String.valueOf(tel.getTellerID()));
                        try {
                            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PeekAccount.fxml"));
                            Parent root1 = (Parent) fxmlLoader.load();
                            Stage peek = new Stage();
                            peek.setScene(new Scene(root1, 335, 600));
                            peek.setResizable(false);
                            peek.show();
                        } catch(Exception e) {
                            e.printStackTrace();
                        }
                    });
                }

                @Override
                public void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(btnPeek);
                    }
                }
            };
            return cell;
        };

        col_Peek.setCellFactory(cellFactory);
    }

    /* look for client account */
    private void initColsClient(){
        col_clientID.setCellValueFactory(new PropertyValueFactory<>("clientID"));
        col_clientFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        col_clientMiddleName.setCellValueFactory(new PropertyValueFactory<>("middleName"));
        col_clientLastName.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        col_clientSavings.setCellValueFactory(new PropertyValueFactory<>("savingsAmount"));
        col_clientDebt.setCellValueFactory(new PropertyValueFactory<>("debtCredit"));
}

    private void populateClientTable(){ //add data in the table
        for(Client client: Bank.ldClient){
            clients.add(new Client(client.getClientID(), client.getFirstName(),client.getMiddleName(),client.getLastname() + " " + client.getExtensionName(),
                client.getSavingsAmount(), client.getDebtCredit()));
        }
        tableClient.setItems(clients);
    }

    private void refreshDataClient(){ //will load again the table data
        this.removeListClient(); //removes the client table items
        clients.clear(); //clears the observable list -> tellers
        this.populateClientTable(); //will fill data in the table
        this.searchClient(); //search through a specific predicate (eg. ID, name)
    }

    @FXML
    void refreshClientTable(ActionEvent event) {
        this.initColsClient();
        if(!Bank.ldClient.isEmpty()){
            this.removeClient(); //add remove button
            this.peekClient(); //peek button
        }
        this.refreshDataClient();
    }

    private void removeClient(){

        Callback<TableColumn<Client, Void>, TableCell<Client, Void>> cellFactory = ( TableColumn<Client, Void> param) -> {
            TableCell<Client, Void> cell = new TableCell<Client, Void>() {

                private final Button btnRemove = new Button("Remove");
                {
                    btnRemove.setOnAction((ActionEvent event) -> {
                        Client client = getTableView().getItems().get(getIndex());
                        System.out.println(client.getClientID());
                        ManagerWindowController.super.removeTellerACcount(String.valueOf(client.getClientID())); //remove the teller account
                        JOptionPane.showMessageDialog(null, client.getFirstName() + " " + client.getLastname() +  " was removed, please refresh the table",
                                "Success", JOptionPane.INFORMATION_MESSAGE);
                    });
                }

                @Override
                public void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(btnRemove);
                    }
                }
            };
            return cell;
        };

        col_remove1.setCellFactory(cellFactory);
    }

    private void peekClient(){

        Callback<TableColumn<Client, Void>, TableCell<Client, Void>> cellFactory = ( TableColumn<Client, Void> param) -> {
            TableCell<Client, Void> cell = new TableCell<Client, Void>() {

                private final Button btnPeek = new Button("Peek");
                {
                    btnPeek.setOnAction((ActionEvent event) -> {
                        Client client = getTableView().getItems().get(getIndex());
                        System.out.println(client.getClientID());
                        Bank.setSessionBackId(String.valueOf(client.getClientID()));
                        try {
                            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PeekAccount.fxml"));
                            Parent root1 = (Parent) fxmlLoader.load();
                            Stage peek = new Stage();
                            peek.setScene(new Scene(root1, 650, 600));
                            peek.setResizable(false);
                            peek.show();
                        } catch(Exception e) {
                            e.printStackTrace();
                        }
                    });
                }

                @Override
                public void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(btnPeek);
                    }
                }
            };
            return cell;
        };

        col_Peek1.setCellFactory(cellFactory);
    }

    /* searching algol */
    //for tellers
    private FilteredList<Teller> filteredListTeller = new FilteredList<>(tellers, p -> true);
    private SortedList<Teller> sortedListTeller = new SortedList<>(filteredListTeller);
    //for clients
    private FilteredList<Client> filteredListClient= new FilteredList<>(clients, p -> true);
    private SortedList<Client> sortedListClient = new SortedList<>(filteredListClient);

    private void removeListTeller(){
        int selectedItem = tableTeller.getSelectionModel().getSelectedIndex();
        if (selectedItem >= 0) {
            tableTeller.getItems().remove(selectedItem);
        }
    }

    private void searchTeller() {

        omniBox.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredListTeller.setPredicate(teller -> {
                if ((newValue == null || newValue.isEmpty())) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (teller.getFirstName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (teller.getLastname().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (String.valueOf(teller.getTellerID()).contains(lowerCaseFilter)) {
                    return true;
                }

                return false;
            });
        });

        sortedListTeller.comparatorProperty().bind(tableTeller.comparatorProperty());
        tableTeller.setItems(sortedListTeller);
    }

    private void removeListClient(){
        int selectedItem = tableClient.getSelectionModel().getSelectedIndex();
        if (selectedItem >= 0) {
            tableClient.getItems().remove(selectedItem);
        }
    }

    private void searchClient() {

        omniBox1.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredListClient.setPredicate(client -> {
                if ((newValue == null || newValue.isEmpty())) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (client.getFirstName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (client.getLastname().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (String.valueOf(client.getClientID()).contains(lowerCaseFilter)) {
                    return true;
                }

                return false;
            });
        });

        sortedListClient.comparatorProperty().bind(tableClient.comparatorProperty());
        tableClient.setItems(sortedListClient);
    }


    //logout button
    @FXML
    void logout(ActionEvent event) {
        try
        {
            Parent root = FXMLLoader.load(getClass().getResource("LoginForm.fxml"));
            Scene scene = new Scene(root,600,396);
            Stage mainStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            mainStage.setScene(scene);
            mainStage.setTitle("Banking System v.2");
            mainStage.setResizable(false);
            mainStage.show();
        }
        catch(IOException e){}
    }

}
