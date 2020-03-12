package bankingsystemv2;

import javafx.beans.binding.DoubleExpression;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.net.URL;
import java.util.InputMismatchException;
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
                i = super.deposit(getFund());
                if(i == 1)
                    message(TellerWindow.getTransactionType());
                else//if client is not found
                    notFound();
                break;
            case "Withdraw":
                i = super.withdraw(getFund());
                if(i == 1)
                    message(TellerWindow.getTransactionType());
                else//if client is not found
                    notFound();
                break;
            case "SendFund":
                i = super.sendFund(String.valueOf(tbeceiverID.getText()),getFund());
                if(i == 1)
                    message(TellerWindow.getTransactionType());
                else//if client is not found
                    notFound();
                break;
            case "PayCredit":
                i = super.payCredit(getFund());
                if(i == 1)
                    message(TellerWindow.getTransactionType());
                else//if client is not found
                    notFound();
                break;
            case "Transaction":
                break;
        }
    }

    private double getFund(){
        try{
            Double value = new Double(Double.parseDouble(Fund.getText()));
            if(value instanceof  Double){
                return Double.parseDouble((Fund.getText()));
            }else
                throw new InputMismatchException();
        }

        catch (InputMismatchException e){
            JOptionPane.showMessageDialog(null, "Invalid amount",
                    "Invalid", JOptionPane.INFORMATION_MESSAGE);
        }

        catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Invalid amount",
                    "Invalid", JOptionPane.INFORMATION_MESSAGE);
        }

        return 0;
    }

    private void message(String type){
        JOptionPane.showMessageDialog(null, type + " successful",
                "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    private void notFound(){
        JOptionPane.showMessageDialog(null, "Client not found",
                "Not found", JOptionPane.INFORMATION_MESSAGE);
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
