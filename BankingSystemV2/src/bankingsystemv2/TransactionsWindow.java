package bankingsystemv2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class TransactionsWindow extends TellerWindow implements Initializable {

    private final ObservableList<TransactionHistory> transac = FXCollections.observableArrayList();
    private final ObservableList<CreditTransaction> debit = FXCollections.observableArrayList();
    //for transaction history
    @FXML private Label lbID;
    @FXML private TableView<TransactionHistory> tableTransaction;
    @FXML private TableColumn<TransactionHistory, String> col_TranName;
    @FXML private TableColumn<TransactionHistory, Double> col_Amount;
    @FXML private TableColumn<TransactionHistory, String>  col_TranType;
    @FXML private TableColumn<TransactionHistory, String> col_DateTime;
    @FXML private Label lbTransaction;

    //for Credit Transaction
    @FXML private TableView<CreditTransaction> tableCreditLoan;
    @FXML private TableColumn<CreditTransaction, String> dateColumn;
    @FXML private TableColumn<CreditTransaction, Double> amountColumn;
    @FXML private TableColumn<CreditTransaction, Double>  interestColumn;
    @FXML private TableColumn<CreditTransaction, Double> totalColumn;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            lbTransaction.setText(TellerWindow.getTransactionType());
        }
        catch (NullPointerException e) {
            System.out.println("Empty SESSION ID");
        }

        lbID.setText(String.valueOf(Bank.getSessionBackId()));
        this.initColsClient();
        this.populateClientTable();
    }

    private void initColsClient(){
        col_TranName.setCellValueFactory(new PropertyValueFactory<>("transaction"));
        col_Amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        col_TranType.setCellValueFactory(new PropertyValueFactory<>("transactionType"));
        col_DateTime.setCellValueFactory(new PropertyValueFactory<>("date"));

        dateColumn.setCellValueFactory(new PropertyValueFactory<>("transactionDate"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        interestColumn.setCellValueFactory(new PropertyValueFactory<>("interestRate"));
        totalColumn.setCellValueFactory(new PropertyValueFactory<>("total"));
    }

    private void populateClientTable(){ //add data in the table
        for(Client c : Bank.ldClient)
        {
            if(c.getClientID().equals(Bank.getSessionBackId()))
            {
                for(TransactionHistory th: c.getTransacAL())
                {
                    transac.add(th);
                }
            }
        }
        tableTransaction.setItems(transac);

        //for credit table

        for(Client c : Bank.ldClient)
        {
            if(c.getClientID().equals(Bank.getSessionBackId()))
            {
                for(CreditTransaction ct: c.getCreditAL())
                {
                    debit.add(ct);
                }
            }
        }

        tableCreditLoan.setItems(debit);
    }
}
