package mainmainbank;

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

import java.net.URL;
import java.util.ResourceBundle;

public class TellerWindow extends BankTransactions implements Initializable {

    //text fields and button
    @FXML private Label lbUsername;
    @FXML private Button btnDeposit;
    @FXML private Button btnWithdraw;
    @FXML private Button btnPayCredit;
    @FXML private Button btnViewSavings;
    @FXML private Button btnViewTransaction;
    @FXML private Button btnViewCreditDebt;
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

    //collections classes used to search function
    private FilteredList<Client> filteredListClient = new FilteredList<>(clients, p -> true);
    private SortedList<Client> sortedListClient = new SortedList<>(filteredListClient);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        displayUsername();
    }

    private void displayUsername(){
        for(Teller tel: Bank.ldTeller){
            if (Bank.getSESSION_ID().equals(String.valueOf(tel.getTellerID()))){
                lbUsername.setText(tel.getFirstName() + " " + tel.getLastname());
            }
        }
    }

    private void initCols(){
        col_ID.setCellValueFactory(new PropertyValueFactory<>("clientID"));
        col_Firstname.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        col_Middlename.setCellValueFactory(new PropertyValueFactory<>("middleName"));
        col_Lastname.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        col_Extensionname.setCellValueFactory(new PropertyValueFactory<>("extensionName"));
    }

    @FXML
    void RefreshTableData(ActionEvent event) {
        this.initCols();
        if(!Bank.ldTeller.isEmpty()){
            this.peekTeller(); //add peek button
        }
        this.refreshTable();
    }

    private void populateTable(){ //add data in the table
        for(Client cl: Bank.ldClient){
            clients.add(new Client(cl.getClientID(), cl.getFirstName(), cl.getMiddleName(),
                    cl.getLastname(), cl.getExtensionName()));
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
            TableCell<Client, Void> cell = new TableCell<Client, Void>() {

                private final Button btnPeek = new Button("Peek");
                {
                    btnPeek.setOnAction((ActionEvent event) -> {
                        Client cl = getTableView().getItems().get(getIndex());
                        System.out.println(cl.getClientID());
                        Bank.setSessionBackId(String.valueOf(cl.getClientID()));
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

        col_Peek.setCellFactory(cellFactory);
    }

    private void searchTeller() {

        omnibox.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredListClient.setPredicate(client -> {
                if ((newValue == null || newValue.isEmpty())) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (client.getFirstName().toLowerCase().contains(lowerCaseFilter)) {
                    System.out.println("Teller window -> client ID" + client.getClientID());
                    //add method here to support client ID retrieval
                    return true;
                } else if (client.getLastname().toLowerCase().contains(lowerCaseFilter)) {
                    //add method here to support client ID retrieval
                    return true;
                } else if (String.valueOf(client.getClientID()).contains(lowerCaseFilter)) {
                    //add method here to support client ID retrieval
                    return true;
                }

                return false;
            });
        });

        sortedListClient.comparatorProperty().bind(tableClient.comparatorProperty());
        tableClient.setItems(sortedListClient);
    }

//        super.deposit();
//        super.withdraw();
//        super.payCredit();
//        super.checkSavings();
//        //view transcation method here
//        super.viewCreditDebt();

    @FXML
    void Deposit(ActionEvent event) {

    }

    @FXML
    void ViewTranscation(ActionEvent event) {

    }

    @FXML
    void Widthdraw(ActionEvent event) {

    }

    @FXML
    void payCredit(ActionEvent event) {

    }



}
