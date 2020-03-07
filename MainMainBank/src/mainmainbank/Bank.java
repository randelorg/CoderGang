package mainmainbank;


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
    private static double INTEREST = 0; //bank interest
    private static double MAX_WITHDRAWAL = 0; //max withdrawal
    private static double MIN_WITHDRWAl = 0; //minimum withdrawal
    private static double INITIAL_DEPOSIT = 0; //initial deposit
    private static double MAINTAINING_BALANCE = 0;
    private static double INITIAL_CREDIT = 0;
    
    //legal age
    private final int clientLegalAge = 18;
    private final int tellerLegalAge = 20;
    
    //session variables USERNAME and PASSWORD
    private static String SESSION_USERNAME;
    private static String SESSION_PASSWORD;
    private static String SESSION_ID;

    //for clients
    private static String SESSION_ID_CLIENT;
    private static String SESSION_ID_TELLER;
    
    //stores manager credentials
    private Manager manager = new Manager(); 
     
    public static double getINTEREST() {
        return INTEREST;
    }

    public static void setINTEREST(double aINTEREST) {
        INTEREST = aINTEREST;
    }

    public static double getMAX_WITHDRAWAL() {
        return MAX_WITHDRAWAL;
    }

    public static void setMAX_WITHDRAWAL(double aMAX_WITHDRAWAL) {
        MAX_WITHDRAWAL = aMAX_WITHDRAWAL;
    }

    public static double getMIN_WITHDRWAl() {
        return MIN_WITHDRWAl;
    }

    public static void setMIN_WITHDRWAl(double aMIN_WITHDRWAl) {
        MIN_WITHDRWAl = aMIN_WITHDRWAl;
    }

    public static double getINITIAL_DEPOSIT() {
        return INITIAL_DEPOSIT;
    }

    public static void setINITIAL_DEPOSIT(double aINITIAL_DEPOSIT) {
        INITIAL_DEPOSIT = aINITIAL_DEPOSIT;
    }

    public static double getMAINTAINING_BALANCE() {
        return MAINTAINING_BALANCE;
    }

    public static void setMAINTAINING_BALANCE(double aMAINTAINING_BALANCE) {
        MAINTAINING_BALANCE = aMAINTAINING_BALANCE;
    }

    public static double getINITIAL_CREDIT() {
        return INITIAL_CREDIT;
    }

    public static void setINITIAL_CREDIT(double aFIRST_CREDIT) {
        INITIAL_CREDIT = aFIRST_CREDIT;
    }
    
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
    private void Session(String username, String password){
        Bank.SESSION_USERNAME = username;
        Bank.SESSION_PASSWORD = password;
    }
    
    public static String getSESSION_ID() {
        return SESSION_ID;
    }

    public static void setSESSION_ID(String aSESSION_ID) {
        SESSION_ID = aSESSION_ID;
    }
    
    public static String getSESSION_USERNAME() {
        return SESSION_USERNAME;
    }
    
    public static String getSESSION_PASSWORD() {
        return SESSION_PASSWORD;
    }

    public static String getSessionIdClient() { return SESSION_ID_CLIENT; }

    public static void setSessionIdClient(String sessionIdClient) { SESSION_ID_CLIENT = sessionIdClient; }

    public static String getSessionIdTeller() { return SESSION_ID_TELLER; }

    public static void setSessionIdTeller(String sessionIdTeller) { SESSION_ID_TELLER = sessionIdTeller; }

    //get the age according to bday
    private int getAge(String bday){
        String[] dates = bday.split(Pattern.quote("-"));
        LocalDate localdate = LocalDate.of(Integer.valueOf(dates[0]), Integer.valueOf(dates[1]),Integer.valueOf(dates[2]));
        LocalDate now = LocalDate.now();
        Period period = Period.between(localdate, now);
        return period.getYears();
    }
    
    //implemented methods from IBank interface
    @Override
    public int generateClientID() {
        int generatedID = new Random().nextInt(89999999) + 10000000;
        
        for(Client cl: ldClient)
        {
            if(generatedID == cl.getClientID())
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
                Session(username, password);//stores the username and password of the manager
                return 0; //if success
            }else{
                //for teller
                for(Teller tel: ldTeller){
                    if(username.equals(tel.getUsername()) && password.equals(tel.getPassword())){
                        Session(username, password);//stores the username and password of the teller
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
        client.setLastname(clientFields[3]);
        client.setExtensionName(clientFields[4]);
        client.setBirthDay(clientFields[5]);
        client.setAge(this.getAge(clientFields[5]));
        client.setGender(clientFields[6]);
        client.setAddress(clientFields[7]);
        client.setMaritaStatus(clientFields[8]);
        client.setCitizenship(clientFields[9]);
        
        if(client.getAge() < clientLegalAge)//when client age is < 18
            return "Declined";
        
        ldClient.add(client);
        
        this.Test();
        
        return "Approve"; //when client age is >= 18
    }

    @Override //for manager
    public String createAccoutTeller(String[] tellerFields) {
        Person p = new Teller(); //typecast
        Teller teller = (Teller) p; //typecast
        
        teller.setTellerID(Integer.parseInt(tellerFields[0]));
        teller.setFirstName(tellerFields[1]);
        teller.setMiddleName(tellerFields[2]);
        teller.setLastname(tellerFields[3]);
        teller.setExtensionName(tellerFields[4]);
        teller.setBirthDay(tellerFields[5]);
        teller.setAge(this.getAge(tellerFields[5]));
        teller.setGender(tellerFields[6]);
        teller.setAddress(tellerFields[7]);
        teller.setMaritaStatus(tellerFields[8]);
        teller.setCitizenship(tellerFields[9]);
        teller.setUsername(tellerFields[10]);
        teller.setPassword(tellerFields[11]);
        
        if(teller.getAge() < tellerLegalAge)//when teller age is < 20
            return "Declined";
        
        ldTeller.add(teller);
        
        this.Test2();
        
        return "Hired"; //when teller age is >= 20
    }

    @Override //for the teller and manager
    public void searchClientAccount() {
        
    }

    @Override //for manager
    public void searchTellerAccount() {
        
    }

    private String setUpUsername(String fname, String lname){
        return fname.substring(0, 1).toUpperCase() + fname.substring(1).concat(lname.substring(0, 1).toUpperCase() + lname.substring(1));
    }

    @Override //for manager
    public String updateTellerProfile(String[] fields) {

        for (Teller tel: Bank.ldTeller){
            if(Bank.getSessionIdTeller().equals(String.valueOf(tel.getTellerID()))){
                tel.setFirstName(fields[0]);
                tel.setMiddleName(fields[1]);
                tel.setLastname(fields[2]);
                tel.setExtensionName(fields[3]);
                tel.setAddress(fields[4]);
                tel.setUsername(this.setUpUsername(tel.getFirstName(),tel.getLastname()));
                System.out.println(tel.getUsername());
                return "Updated";
            }
        }

        return "Failed";
    }

    @Override //for teller and manager
    public void displayClientAccount() {
        
    }

    @Override //for manager
    public void displayTellerAccount() {
        
    }

    @Override //for manager
    public void removeClientAccount() {
        
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
    
    //testing purposes methods
    private void Test(){
        
        for(Client client: ldClient){
            System.out.println(client.getClientID());
            System.out.println(client.getFirstName());
            System.out.println(client.getMiddleName());
            System.out.println(client.getLastname());
            System.out.println(client.getExtensionName());
            System.out.println(client.getBirthDay());
            System.out.println(client.getAge());
            System.out.println(client.getGender());
            System.out.println(client.getAddress());
            System.out.println(client.getMaritaStatus());
            System.out.println(client.getCitizenship());
        }
    }
    
    private void Test2(){
        
        for(Teller teller: ldTeller){
            System.out.println(teller.getTellerID());
            System.out.println(teller.getFirstName());
            System.out.println(teller.getMiddleName());
            System.out.println(teller.getLastname());
            System.out.println(teller.getExtensionName());
            System.out.println(teller.getBirthDay());
            System.out.println(teller.getAge());
            System.out.println(teller.getGender());
            System.out.println(teller.getAddress());
            System.out.println(teller.getMaritaStatus());
            System.out.println(teller.getCitizenship());
        }
    }
}
