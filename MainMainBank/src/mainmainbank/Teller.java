package mainmainbank;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Randel P. Reyes
 */
public class Teller extends Person{
    
    private int tellerID;
    private String username;
    private String password;

    public Teller(){}
    
    public Teller(int tellerID, String  firstName, String  middleName, String lastName, String address, int age) {
        super(firstName, middleName, lastName, address, age);
        this.tellerID = tellerID;
    }

    public int getTellerID() {
        return this.tellerID;
    }

    public void setTellerID(int tellerID) {
        this.tellerID = tellerID;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
