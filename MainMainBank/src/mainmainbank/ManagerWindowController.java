/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainmainbank;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.InputMismatchException;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
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

    //for look account tab
    private final ObservableList<Teller> tellers = FXCollections.observableArrayList();
    @FXML private TableView<Teller> tvsearchTeller;
    @FXML private TableColumn<Teller, Integer> tvAccountID;
    @FXML private TableColumn<Teller, String> tvFirstName;
    @FXML private TableColumn<Teller, String> tvMiddlename;
    @FXML private TableColumn<Teller, String> tbLastName;
    @FXML private TableColumn<Teller, String> tbAddress;
    @FXML private TableColumn<Teller, Integer> tvAge;
    @FXML private TableColumn<Teller, Void> col_Update;
    @FXML private TableColumn<Teller, Void> col_remove;

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
                            JOptionPane.OK_OPTION);
            
            accountID.setText(String.valueOf(super.generateClientID()));//creates new client ID
        }
        
        catch(NumberFormatException e){//bday error
            JOptionPane.showMessageDialog(null, "Birthdate is invalid", "Invalid",
                            JOptionPane.OK_OPTION);
        }
        catch(IllegalArgumentException e){//if age is < 18
            JOptionPane.showMessageDialog(null, "Client age must be over 18", "Declined",
                            JOptionPane.OK_OPTION);
        }
        catch(InputMismatchException e){//empty fields
            JOptionPane.showMessageDialog(null, "Please input all fields", "Empty Field(s)",
                            JOptionPane.OK_OPTION);
        }
        
    }

    //for creation of teller account
    @FXML
    void checkPassword(ActionEvent event) throws AWTException {
        Robot robot = new Robot();//simulates press of f3

        if(tbRetypePassword.getText().equals(tbPassword)){

        }else{
            robot.keyPress(114); //f3 key
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
                    JOptionPane.OK_OPTION);
        } catch (InputMismatchException e) {//empty fields
            JOptionPane.showMessageDialog(null, "Please input all fields", "Empty Field(s)",
                    JOptionPane.OK_OPTION);
        } catch (IllegalArgumentException e) {//if age is < 18
            JOptionPane.showMessageDialog(null, "Teller age must be over 20", "Declined",
                    JOptionPane.OK_OPTION);
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

    //look account tab
    private void initCols(){
        tvAccountID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        tvFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        tvMiddlename.setCellValueFactory(new PropertyValueFactory<>("middleName"));
        tbLastName.setCellValueFactory(new PropertyValueFactory<>("LastName"));
        tbAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        tvAge.setCellValueFactory(new PropertyValueFactory<>("age"));
    }

    private void populateTable(){ //add data in the table
        for(Teller tel: Bank.ldTeller){
            tellers.add(new Teller(tel.getTellerID(), tel.getFirstName(), tel.getMiddleName(),
                    tel.getLastName() + " " + tel.getExtensionName(), tel.getAddress(), tel.getAge()));
        }
        tvsearchTeller.setItems(tellers);
    }

    private void refreshData(){ //will load again the table data
        tvsearchTeller.getItems().clear();
        tellers.clear(); //clears the observable list -> tellers
        this.populateTable(); //will fill data in the table
    }

    @FXML
    void refreshTable(ActionEvent event) {
        this.initCols();

        if(!Bank.ldTeller.isEmpty()){
            this.updateButtonToTable(); //add update button
            this.removeButtonToTable(); //add remove button
            this.refreshData();
        }else{
            this.refreshData();
        }
    }

    private void updateButtonToTable(){
        //@FXML private TableColumn<Teller, Void> col_Update;
        //@FXML private TableColumn<Teller, Void> col_remove;

        Callback<TableColumn<Teller, Void>, TableCell<Teller, Void>> cellFactory = ( TableColumn<Teller, Void> param) -> {
             TableCell<Teller, Void> cell = new TableCell<Teller, Void>() {
                
                private final Button btnUpdate = new Button("Update");
                {
                    btnUpdate.setOnAction((ActionEvent event) -> {
                        Teller tel = getTableView().getItems().get(getIndex());
                        System.out.println(tel.getTellerID());
                        Bank.setSessionIdClient(String.valueOf(tel.getTellerID()));
                        try {
                            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("UpdateWindow.fxml"));
                            Parent root1 = (Parent) fxmlLoader.load();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(root1));
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

    private void removeButtonToTable(){

        Callback<TableColumn<Teller, Void>, TableCell<Teller, Void>> cellFactory = ( TableColumn<Teller, Void> param) -> {
            TableCell<Teller, Void> cell = new TableCell<Teller, Void>() {

                private final Button btnRemove = new Button("Remove");
                {
                    btnRemove.setOnAction((ActionEvent event) -> {
                        Teller tel = getTableView().getItems().get(getIndex());
                        System.out.println(tel.getTellerID());
                        ManagerWindowController.super.removeTellerACcount(String.valueOf(tel.getTellerID())); //remove the teller account
                        JOptionPane.showMessageDialog(null, tel.getFirstName() + " " + tel.getLastName() +  " is removed, please refresh the table", "Success",
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
            mainStage.show();
        }
        catch(IOException e){}
    }
    
}