package bankingsystemv2;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class PeekAccount extends Bank implements Initializable {

    @FXML private TextField IdentityNumber;
    @FXML private TextField firstName;
    @FXML private TextField middleName;
    @FXML private TextField lastName;
    @FXML private TextField extensionName;
    @FXML private TextField age;
    @FXML private TextField gender;
    @FXML private TextField address;
    @FXML private TextField maritalStatus;
    @FXML private TextField citizenship;

    @FXML private TextField savings;
    @FXML private TextField credit;
    @FXML private TextField debt;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //sets all the initial credit amount of clients
        for (Client cl: Bank.ldClient){
            if(cl.getCreditLimit() == 0) {
                cl.setCreditLimit(Bank.getInitialCredit());
            }
        }
        this.displayAccount();
    }

    private void displayAccount(){
        boolean found  = false;

        for (Teller tel: Bank.ldTeller){
            if(Bank.getSessionBackId().equals(String.valueOf(tel.getTellerID()))){
                //profile
                IdentityNumber.setText(String.valueOf(tel.getTellerID()));
                firstName.setText(tel.getFirstName());
                middleName.setText(tel.getMiddleName());
                lastName.setText(tel.getSurname());
                extensionName.setText(tel.getExtensionName());
                age.setText(String.valueOf(tel.getAge(tel.getBirthdate())));
                gender.setText(tel.getGender());
                address.setText(tel.getAddress());
                maritalStatus.setText(tel.getMaritalStatus());
                citizenship.setText(tel.getCitizenship());
                found = true;
                break;
            }
        }

        if(!found){
            for (Client client: Bank.ldClient){
                if(Bank.getSessionBackId().equals(String.valueOf(client.getClientID()))){
                    //profile
                    IdentityNumber.setText(String.valueOf(client.getClientID()));
                    firstName.setText(client.getFirstName());
                    middleName.setText(client.getMiddleName());
                    lastName.setText(client.getSurname());
                    extensionName.setText(client.getExtensionName());
                    age.setText(String.valueOf(client.getAge(client.getBirthdate())));
                    gender.setText(client.getGender());
                    address.setText(client.getAddress());
                    maritalStatus.setText(client.getMaritalStatus());
                    citizenship.setText(client.getCitizenship());
                    //assets
                    savings.setText(String.valueOf(client.getSavingsBalance()));
                    credit.setText(String.valueOf(client.getCreditBalance()));
                    debt.setText(String.valueOf(client.getTOTAL_DEBIT()));
                    break;
                }
            }
        }
    }

}
