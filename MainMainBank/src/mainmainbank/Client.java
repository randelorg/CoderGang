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
public class Client extends Person {
    private int clientID;
    private double savingsAmount;

    private double creditAMount;
    private double debtCredit;
    private double monthlyPayment;
    private int age;

    public Client(){}

    public Client(int clientID, String firstName, String middleName, String lastname, double savingsAmount, double debtCredit) {
        super(firstName,middleName,lastname);
        this.clientID = clientID;
        this.savingsAmount = savingsAmount;
        this.debtCredit = debtCredit;
    }

    public Client(int clientID, String firstName, String middleName, String lastname, String extensionName) {
        super(firstName,middleName,lastname);
        this.clientID = clientID;
    }


    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public double getSavingsAmount() {
        return savingsAmount;
    }

    public void setSavingsAmount(double savingsAmount) {
        this.savingsAmount = savingsAmount;
    }

    public double getCreditAMount() {
        return creditAMount;
    }

    public void setCreditAMount(double creditAMount) {
        this.creditAMount = creditAMount;
    }

    public double getDebtCredit() {
        return debtCredit;
    }

    public void setDebtCredit(double debtCredit) {
        this.debtCredit = debtCredit;
    }

    public double getMonthlyPayment() {
        return monthlyPayment;
    }

    public void setMonthlyPayment(double monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }
}
