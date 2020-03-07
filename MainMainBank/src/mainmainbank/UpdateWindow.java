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

    @FXML private Label session;
    @FXML private TextField fname;
    @FXML private TextField lname;
    @FXML private TextField mname;
    @FXML private TextField addres;
    @FXML private TextField tbExtension;

    private final String[] fields = new String[5];

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        session.setText("Updating teller " + Bank.getSessionIdTeller());
    }

    @FXML
    void Update(ActionEvent event) {

        fields[0] = fname.getText();
        fields[1] = mname.getText();
        fields[2] = lname.getText();
        fields[3] = tbExtension.getText();
        fields[4] = addres.getText();

        super.updateTellerProfile(fields);

        JOptionPane.showMessageDialog(null, "Teller " + Bank.getSessionIdTeller() + " is updated"
                                        , "Updated", JOptionPane.INFORMATION_MESSAGE);

    }

}
