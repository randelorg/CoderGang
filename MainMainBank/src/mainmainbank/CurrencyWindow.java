package mainmainbank;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ResourceBundle;

public class CurrencyWindow extends TellerWindow implements Initializable {

    @FXML private TextField Fund;
    @FXML private Label lbAccountID;
    @FXML private Button btnSend;
    @FXML private Label lbTypeOfTransaction;
    @FXML private TextField tbeceiverID;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.displayID();
        lbTypeOfTransaction.setText(TellerWindow.getTransactionType());

        if(TellerWindow.getTransactionType().equals("SendFund")) {
            btnSend.setText("Send");
            tbeceiverID.setVisible(true);
        }else {
            btnSend.setText("Process");
            tbeceiverID.setVisible(false);
        }
    }

    private void transactionsName(){
        int i = 0;
        switch (TellerWindow.getTransactionType()){
            case "Deposit":
                i = super.deposit(Double.parseDouble(Fund.getText()));
                if(i == 1)
                    message(TellerWindow.getTransactionType());
                break;
            case "Withdraw":
                i = super.withdraw(Double.parseDouble(Fund.getText()));
                if(i == 1)
                    message(TellerWindow.getTransactionType());
                break;
            case "SendFund":
                i = super.sendFund(String.valueOf(tbeceiverID.getText()),Double.parseDouble(Fund.getText()));
                if(i == 1)
                    message(TellerWindow.getTransactionType());
                break;
            case "PayCredit":
                super.payCredit(Double.parseDouble(Fund.getText()));
                break;
            case "Transaction":
                break;
        }
    }

    private void message(String type){
        JOptionPane.showMessageDialog(null, type + " successful",
                "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    private void displayID(){
        for(Client client: Bank.ldClient){
            if (Bank.getSessionBackId().equals(String.valueOf(client.getClientID()))){
                lbAccountID.setText("Account ID " + String.valueOf(client.getClientID()));
                break;
            }
        }
    }

    @FXML
    private void ProcessCurrency(ActionEvent event) {
        this.transactionsName();
    }


}
