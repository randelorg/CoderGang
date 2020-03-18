/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankingsystemv2;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Randel Reyes
 */
public class LoginFormController extends Bank implements Initializable {
    
    /**
     * Initializes the controller class.
     */
    
    public static int instanceCounter = 0;
    
    private void clearFields(){
         username.clear();
         password.clear();
    }
    
    @FXML private PasswordField password;
    @FXML private TextField username;
    
    @FXML
    private void login(ActionEvent event) throws IOException {
        try{
            int userLevel = super.Login(username.getText(), password.getText());

            switch(userLevel)
            {
                case 0: //manager
                        Parent root = FXMLLoader.load(getClass().getResource("ManagerWindow.fxml"));
                        Scene scene = new Scene(root,1159,717);
                        Stage managerWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
                        managerWindow.setScene(scene);
                        managerWindow.setTitle("Manager window");
                        managerWindow.setResizable(false);
                        managerWindow.show();
                    break;
                case 1: //teller
                        Parent root1 = FXMLLoader.load(getClass().getResource("TellerWindow.fxml"));
                        Scene scene1 = new Scene(root1,1020,530);
                        Stage tellerWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
                        tellerWindow.setScene(scene1);
                        tellerWindow.setTitle("Teller window");
                        tellerWindow.setResizable(false);
                        tellerWindow.show();
                    break;
                case -1: //doesnt match
                        this.clearFields();//clears the fields
                        JOptionPane.showMessageDialog(null, "An incorrect password or username", "Account not found", JOptionPane.OK_OPTION);
                    break;
                case -2: //empty fields
                        JOptionPane.showMessageDialog(null, "Fill in the credentials", "Empty fields", JOptionPane.OK_OPTION);
            }
        }
        catch(InputMismatchException e){
            JOptionPane.showMessageDialog(null, "Invalid", "Invalid field(s)", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    } 
    
    
}
