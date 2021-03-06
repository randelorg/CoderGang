package bankingsystemv2;


import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.Random;
import java.util.regex.Pattern;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Randel P. Reyes
 */
public class Bank implements IBank{

    //linked list implementation for clients and tellers
    protected static LinkedList<Client> ldClient = new LinkedList(); //stores information of every client
    protected static LinkedList<Teller> ldTeller = new LinkedList(); //stores information of every teller

    private static Long BANK_NET_AMOUNT = Long.parseUnsignedLong("18446744073709");
    private static double INTEREST; //bank interest
    private static double MAX_WITHDRAWAL; //max withdrawal
    private static double MIN_WITHDRWAl; //minimum withdrawal

    public static Long getBankNetAmount() {
        return BANK_NET_AMOUNT;
    }

    public static void setBankNetAmount(Long bankNetAmount) {
        BANK_NET_AMOUNT = bankNetAmount;
    }

    public static double getINTEREST() {
        return INTEREST;
    }

    public static void setINTEREST(double INTEREST) {
        Bank.INTEREST = INTEREST;
    }

    public static double getMaxWithdrawal() {
        return MAX_WITHDRAWAL;
    }

    public static void setMaxWithdrawal(double maxWithdrawal) {
        MAX_WITHDRAWAL = maxWithdrawal;
    }

    public static double getMIN_WITHDRWAl() {
        return MIN_WITHDRWAl;
    }

    public static void setMIN_WITHDRWAl(double MIN_WITHDRWAl) {
        Bank.MIN_WITHDRWAl = MIN_WITHDRWAl;
    }

    public static double getInitialDeposit() {
        return INITIAL_DEPOSIT;
    }

    public static void setInitialDeposit(double initialDeposit) {
        INITIAL_DEPOSIT = initialDeposit;
    }

    public static double getMaintainingBalance() {
        return MAINTAINING_BALANCE;
    }

    public static void setMaintainingBalance(double maintainingBalance) {
        MAINTAINING_BALANCE = maintainingBalance;
    }

    public static double getInitialCredit() {
        return INITIAL_CREDIT;
    }

    public static void setInitialCredit(double initialCredit) {
        INITIAL_CREDIT = initialCredit;
    }

    private static double INITIAL_DEPOSIT; //initial deposit
    private static double MAINTAINING_BALANCE;
    private static double INITIAL_CREDIT;

    //legal age
    private final int clientLegalAge = 18;
    private final int tellerLegalAge = 20;

    //session variables USERNAME and PASSWORD
    private static String SESSION_USERNAME;
    private static String SESSION_PASSWORD;
    private static String SESSION_ID;

    //for clients
    private static String SESSION_BACK_ID;

    //stores manager credentials
    private Manager manager = new Manager();

    public static Long getBANK_NET_AMOUNT() {
        return Bank.BANK_NET_AMOUNT;
    }
    //clients pay add to the bank net amount
    public static void AddBankNetAmount(double loan){
        Bank.BANK_NET_AMOUNT += (long)loan;
    }
    //clients get a loan deduct to the bank net amount
    public static void DeductBankNetAmount(double loan){
        Bank.BANK_NET_AMOUNT -= (long)loan;
    }

    //stores the current username and password
    private void Session(int ID, String username, String password){
        Bank.SESSION_ID = String.valueOf(ID);
        Bank.SESSION_USERNAME = username;
        Bank.SESSION_PASSWORD = password;
    }

    public static String getSESSION_ID() {
        return SESSION_ID;
    }

    public static String getSESSION_USERNAME() {
        return SESSION_USERNAME;
    }

    public static String getSessionBackId() { return SESSION_BACK_ID; }

    public static void setSessionBackId(String sessionBackId) { SESSION_BACK_ID = sessionBackId; }
    
    //implemented methods from IBank interface
    @Override
    public int generateClientID() {
        int generatedID = new Random().nextInt(89999999) + 10000000;
        
        for(Client cl: ldClient)
        {
            if(generatedID == Integer.parseInt(cl.getClientID()))
                generatedID = new Random().nextInt(89999999) + 10000000;
        }
        
        return generatedID;
    }

    @Override
    public int generateTellerID() {
        int generatedID = new Random().nextInt(89999999) + 10000000;
        
        for(Teller tel: ldTeller)
        {
            if(generatedID == tel.getTellerID())
                generatedID = new Random().nextInt(89999999) + 10000000;
        }
        
        return generatedID;
    }

    @Override
    public int Login(String username, String password) throws InputMismatchException{
        
        if(!username.isEmpty() && !password.isEmpty()){
            //for the manager
            if(username.equals(manager.getUsername()) && password.equals(manager.getPassword())){
                Session(manager.getID(), username, password);//stores the username and password of the manager
                return 0; //if success
            }else{
                //for teller
                for(Teller tel: ldTeller){
                    if(username.equals(tel.getUsername()) && password.equals(tel.getPassword())){
                        Session(tel.getTellerID(),username, password);//stores the username and password of the teller
                        return 1;//if success
                    }
                }
            }
        }
        else
            return -2;//if fields are empty
        
        return -1;//if not success
    }
    
    @Override //for the teller and manager
    public String createAccountClient(String[] clientFields) {
        Person p = new Client(); //typecast
        Client client = (Client) p; //typecast
        
        client.setClientID(Integer.parseInt(clientFields[0]));
        client.setFirstName(clientFields[1]);
        client.setMiddleName(clientFields[2]);
        client.setSurname(clientFields[3]);
        client.setExtensionName(clientFields[4]);
        client.setBirthdate(clientFields[5]);
        client.setGender(clientFields[6]);
        client.setAddress(clientFields[7]);
        client.setMaritalStatus(clientFields[8]);
        client.setCitizenship(clientFields[9]);
        
        if(client.getAge(clientFields[5]) < clientLegalAge)//when client age is < 18
            return "Declined";
        
        ldClient.add(client);
        
        return "Approve"; //when client age is >= 18
    }

    @Override //for manager
    public String createAccoutTeller(String[] tellerFields) {
        Person p = new Teller(); //typecast
        Teller teller = (Teller) p; //typecast
        
        teller.setTellerID(Integer.parseInt(tellerFields[0]));
        teller.setFirstName(tellerFields[1]);
        teller.setMiddleName(tellerFields[2]);
        teller.setSurname(tellerFields[3]);
        teller.setExtensionName(tellerFields[4]);
        teller.setBirthdate(tellerFields[5]);
        teller.setGender(tellerFields[6]);
        teller.setAddress(tellerFields[7]);
        teller.setMaritalStatus(tellerFields[8]);
        teller.setCitizenship(tellerFields[9]);
        teller.setUsername(tellerFields[10]);
        teller.setPassword(tellerFields[11]);
        
        if(teller.getAge(tellerFields[5]) < tellerLegalAge)//when teller age is < 20
            return "Declined";
        
        ldTeller.add(teller);
        
        return "Hired"; //when teller age is >= 20
    }

    private String setUpUsername(String fname, String lname){
        return fname.substring(0, 1).toUpperCase() + fname.substring(1).concat(lname.substring(0, 1).toUpperCase() + lname.substring(1));
    }

    @Override //for manager
    public String updateTellerProfile(String[] fields) {

        for (Teller tel: Bank.ldTeller){
            if(Bank.getSessionBackId().equals(String.valueOf(tel.getTellerID()))){
                tel.setFirstName(fields[0]);
                tel.setMiddleName(fields[1]);
                tel.setSurname(fields[2]);
                tel.setExtensionName(fields[3]);
                tel.setAddress(fields[4]);
                tel.setUsername(this.setUpUsername(tel.getFirstName(),tel.getSurname()));
                System.out.println(tel.getUsername());
                return "Updated";
            }
        }

        return "Failed";
    }

    @Override //for manager
    public void removeClientAccount() {
        //empty
    }

    @Override //for manager
    public void removeTellerACcount(String clientID) {
        Iterator iterator = ldTeller.iterator();

        int i = 0;
        boolean found = false;
        for(Teller tel:Bank.ldTeller){
            if(clientID.equals(String.valueOf(tel.getTellerID()))){
                found = true;
                break;
            }
            i++;
        }

        if(found){
            Bank.ldTeller.remove(i);
            System.out.println("Removed");
        }
    }

    @Override //for manager and teller
    public void viewClientAccountTranscations() {
        
    }
    
}
