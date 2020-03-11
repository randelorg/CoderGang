package bankingsystemv2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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
        session.setText("Updating ID " + Bank.getSessionBackId());
    }

    private void setFields(){
        fields[0] = fname.getText();
        fields[1] = mname.getText();
        fields[2] = lname.getText();
        fields[3] = tbExtension.getText();
        fields[4] = addres.getText();
    }

    @FXML
    void Update(ActionEvent event) {

        try {

            this.setFields();

            for (String f : fields) {
                if (f.isEmpty()) {
                    throw new InputMismatchException();
                }
            }

            String status = super.updateTellerProfile(fields);

            if(status.equals("Updated")){
                JOptionPane.showMessageDialog(null, "Teller " + Bank.getSessionBackId() + " is updated"
                        , "Updated", JOptionPane.INFORMATION_MESSAGE);
            }else if(status.equals("Failed")){
                JOptionPane.showMessageDialog(null, "Teller " + Bank.getSessionBackId() + " failed to update"
                        , "Failed", JOptionPane.ERROR_MESSAGE);
            }
            //(node) e.getScene()
        }

        catch (InputMismatchException e) {
            JOptionPane.showMessageDialog(null, "Supply all fields"
                    , "Empty fields", JOptionPane.INFORMATION_MESSAGE);
            e.getMessage();
        }

    }

}
