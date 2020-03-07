package mainmainbank;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author asus
 */
public interface IBank {

    int generateClientID(); //generates the client ID number - check
    int generateTellerID(); //generates the teller ID number - check
    int Login(String username, String password); //login  - check
    String createAccountClient(String[] clientFields); //creates account for the client - check
    String createAccoutTeller(String[] tellerFields); //creates account for the teller
    void searchClientAccount(); //search for the client account
    void searchTellerAccount(); //search for the teller account
    String updateTellerProfile(String[] fields); //updates teller profile
    void displayClientAccount(); //display all the client profiles
    void displayTellerAccount(); //display all the teller profiles
    void removeClientAccount(); //remove/delete the client account
    void removeTellerACcount(String clientID); //remove/delete the teller account
    void viewClientAccountTranscations(); //view the client account transactions
    
}
