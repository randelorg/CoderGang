package mainmainbank;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.net.URL;
import java.util.InputMismatchException;
import java.util.ResourceBundle;

public class UpdateWindow extends Bank implements Initializable {

    @FXML private TextField fname;
    @FXML private TextField lname;
    @FXML private Label session;
    @FXML private TextField addres;
    @FXML private TextField mname;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        session.setText("Updating teller " + Bank.getSessionIdClient());
    }

    @FXML
    void Update(ActionEvent event) {
        for (Teller tel: Bank.ldTeller){
            if(tel.getTellerID() == Integer.parseInt(Bank.getSessionIdClient())){
                try {
                    tel.setFirstName(fname.getText());
                    tel.setMiddleName(mname.getText());
                    tel.setLastName(lname.getText());
                    tel.setAddress(addres.getText());
                    JOptionPane.showMessageDialog(null, "Updating complete", "Success", JOptionPane.INFORMATION_MESSAGE);
                }
                catch (InputMismatchException e){}
                break;
            }
        }
        Stage stage  = new Stage();
        stage.close();
        // do what you have to do
    }

}
