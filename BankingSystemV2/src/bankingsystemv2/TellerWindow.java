package bankingsystemv2;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TellerWindow extends BankTransactions implements Initializable {

    private static String transactionType;
    public static String getTransactionType() {
        return transactionType;
    }

    public static void setTransactionType(String transactionType) {
        TellerWindow.transactionType = transactionType;
    }

    @FXML private Label lbUsername;
    @FXML private Button btnDeposit1;
    @FXML private Button btnWithdraw1;
    @FXML private Button btnPayCredit1;
    @FXML private Button btnViewTransaction;
    @FXML private TextField omnibox;
    @FXML private Button btnYourAccount;
    @FXML private Button btnRefresh;
    //table
    private final ObservableList<Client> clients = FXCollections.observableArrayList();
    @FXML private TableView<Client> tableClient;
    @FXML private TableColumn<Client, String> col_ID;
    @FXML private TableColumn<Client, String> col_Firstname;
    @FXML private TableColumn<Client, String> col_Middlename;
    @FXML private TableColumn<Client, String> col_Lastname;
    @FXML private TableColumn<Client, String> col_Extensionname;
    @FXML private TableColumn<Client, Void> col_Peek;

    //(collections) classes used to search function
    private FilteredList<Client> filteredListClient = new FilteredList<>(clients, p -> true);
    private SortedList<Client> sortedListClient = new SortedList<>(filteredListClient);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        displayUsername();
    }

    private void displayUsername(){
        for(Teller tel: Bank.ldTeller){
            if (Bank.getSESSION_ID().equals(String.valueOf(tel.getTellerID()))){
                lbUsername.setText(tel.getFirstName() + " " + tel.getSurname());
            }
        }
    }

    private void initCols(){
        col_ID.setCellValueFactory(new PropertyValueFactory<>("clientID"));
        col_Firstname.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        col_Middlename.setCellValueFactory(new PropertyValueFactory<>("middleName"));
        col_Lastname.setCellValueFactory(new PropertyValueFactory<>("surname"));
        col_Extensionname.setCellValueFactory(new PropertyValueFactory<>("extensionName"));
    }

    @FXML
    private void RefreshTableData(ActionEvent event) {
        this.initCols();
        if(!Bank.ldTeller.isEmpty()){
            this.peekTeller(); //add peek button
        }
        this.refreshTable();
    }

    private void populateTable(){ //add data in the table
        for(Client cl: Bank.ldClient){
            if(!clients.contains(cl))
                clients.add(cl);
        }
        tableClient.setItems(clients);
    }

    private void removeData(){
        int selectedItem = tableClient.getSelectionModel().getSelectedIndex();
        if (selectedItem >= 0) {
            tableClient.getItems().remove(selectedItem);
        }
    }

    private void refreshTable(){ //will load again the table data
        this.removeData(); //removes the teller table items
        clients.clear(); //clears the observable list -> tellers
        this.populateTable(); //will fill data in the table
        this.searchTeller(); //search through a specific predicate (eg. ID, name)
    }

    private void peekTeller(){

        Callback<TableColumn<Client, Void>, TableCell<Client, Void>> cellFactory = (TableColumn<Client, Void> param) -> {
            return new TableCell<Client, Void>() {

                private final Button btnPeek = new Button("Peek");
                {
                    btnPeek.setOnAction((ActionEvent event) -> {
                        Client cl = getTableView().getItems().get(getIndex());
                        System.out.println(cl.getClientID());
                        Bank.setSessionBackId(String.valueOf(cl.getClientID()));
                        try {
                            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PeekAccount.fxml"));
                            Parent root1 = fxmlLoader.load();
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
        };

        col_Peek.setCellFactory(cellFactory);
    }

    private void searchTeller() {

        omnibox.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            filteredListClient.setPredicate(client -> {
                if ((newValue == null || newValue.isEmpty())) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (client.getFirstName().toLowerCase().contains(lowerCaseFilter)) {
                    System.out.println("Teller window -> client ID: " + client.getClientID());
                    Bank.setSessionBackId(String.valueOf(client.getClientID()));
                    //add method here to support client ID retrieval
                    return true;
                } else //add method here to support client ID retrieval
                    if (client.getSurname().toLowerCase().contains(lowerCaseFilter)) {
                        Bank.setSessionBackId(String.valueOf(client.getClientID()));
                        //add method here to support client ID retrieval
                        return true;
                    } else if (String.valueOf(client.getClientID()).contains(lowerCaseFilter)){
                        Bank.setSessionBackId(String.valueOf(client.getClientID()));
                        return true;
                    }

                    return false;
            });
        });

        sortedListClient.comparatorProperty().bind(tableClient.comparatorProperty());
        tableClient.setItems(sortedListClient);
    }

    private void displayForm() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CurrencyWindow.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage currency = new Stage();
        currency.setScene(new Scene(root1, 540, 253));
        currency.setResizable(false);
        currency.show();
    }

    private void displayTableTransaction() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TransactionsWindow.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    private void Deposit(ActionEvent event) throws IOException {
        setTransactionType("Deposit");
        this.displayForm();
    }

    @FXML
    private void ViewTranscation(ActionEvent event) throws IOException {
        setTransactionType("Transaction");
        displayTableTransaction();
    }

    @FXML
    private void Widthdraw(ActionEvent event) throws IOException {
        setTransactionType("Withdraw");
        this.displayForm();
    }

    @FXML
    private void payCredit(ActionEvent event) throws IOException {
        setTransactionType("PayCredit");
        this.displayForm();
    }

    @FXML
    private void SendFund(ActionEvent event) throws IOException {
        setTransactionType("SendFund");
        this.displayForm();
    }

}
