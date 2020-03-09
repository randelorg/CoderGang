package mainmainbank;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class TellerWindow implements Initializable {

    @FXML private Label lbUsername;

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

}
