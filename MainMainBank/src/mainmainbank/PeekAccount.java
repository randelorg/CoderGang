package mainmainbank;

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
    @FXML private TextField monthlyPayment;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
                lastName.setText(tel.getLastname());
                extensionName.setText(tel.getExtensionName());
                age.setText(String.valueOf(tel.getAge()));
                gender.setText(tel.getGender());
                address.setText(tel.getAddress());
                maritalStatus.setText(tel.getMaritaStatus());
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
                    lastName.setText(client.getLastname());
                    extensionName.setText(client.getExtensionName());
                    age.setText(String.valueOf(client.getAge()));
                    gender.setText(client.getGender());
                    address.setText(client.getAddress());
                    maritalStatus.setText(client.getMaritaStatus());
                    citizenship.setText(client.getCitizenship());
                    //assets
                    savings.setText(String.valueOf(client.getSavingsAmount()));
                    credit.setText(String.valueOf(client.getCreditAMount()));
                    debt.setText(String.valueOf(client.getDebtCredit()));
                    monthlyPayment.setText(String.valueOf(client.getMonthlyPayment()));
                    found = true;
                    break;
                }
            }
        }
    }

}
