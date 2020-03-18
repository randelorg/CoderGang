/*
 * @Title:
 * @Description:
 * 
 * @Author:
 * @Date Created:
 */
package bankingsystemv2;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;


public class Client extends Person
{  
    private String clientID ;
    private double creditLimit = 0;
    private double savingsBalance = 0;
    private double creditBalance = 0;
    private double TOTAL_DEBIT = 0;


    private ArrayList<TransactionHistory> transacAL = new ArrayList<>();
    private ArrayList<CreditTransaction> creditAL = new ArrayList<>();
    
    public Client(){ }

    public Client(Double creditLimit, Double savingsBalance)
    {
        super();
        this.creditLimit = creditLimit;
        this.savingsBalance = savingsBalance;
    }
    
    public Client(Person p,Double creditLimit, Double savingsBalance)
    {
        super(p); 
        this.creditLimit = creditLimit;
        this.savingsBalance = savingsBalance;
    }
    
     public Client(Person p,Double creditLimit, Double savingsBalance, String id)
    {
        super(p); 
        this.clientID = id;
        this.creditLimit = creditLimit;
        this.savingsBalance = savingsBalance;
    }

    public double getTOTAL_DEBIT() {
        TOTAL_DEBIT = 0;
        for(CreditTransaction ct: this.creditAL)
            TOTAL_DEBIT += ct.getTotal();
        return TOTAL_DEBIT;
    }
    public void setTOTAL_DEBIT(double TOTAL_DEBIT) {
        this.TOTAL_DEBIT = TOTAL_DEBIT;
    }

    public void setClientID(Integer id)
    {
        this.clientID = id.toString();
    }
    public void deductToSavings(double fund){
        this.savingsBalance -= fund;
    }
    public void addToSavings(double fund){
        this.savingsBalance += fund;
    }
    public void addTransaction(TransactionHistory th)
    {
        transacAL.add(th);
    }
    public void addCreditTransaction(CreditTransaction ct)
    {
        creditAL.add(ct);
    }
    public String getClientID() {
        return this.clientID;
    }
    public Double getSavingsBalance() {
        return this.savingsBalance;
    }
    public ArrayList<TransactionHistory> getTransacAL() {
        return this.transacAL;
    }
    public ArrayList<CreditTransaction> getCreditAL() {
        return this.creditAL;
    }
    public void setCreditLimit(Double creditLimit) {
        this.creditLimit = creditLimit;
    }
    public Double getCreditLimit(){return this.creditLimit;}
    public void setSavingsBalance(Double savingsBalance) {
        this.savingsBalance = savingsBalance;
    }
    public void setCreditBalance(Double creditBalance) {
        this.creditBalance = creditBalance;
    }
    public Double getCreditBalance(){return this.creditBalance;}
    public void addToCreditBalance(Double amount){this.creditBalance += amount;}
    
    public void settleCreditTransaction(String date, Double amount)
    {
        for(int i = 0; i < creditAL.size(); i++)
        {
            if(creditAL.get(i).getTransactionDate().equals(date) && creditAL.get(i).getTotal() == amount)
            {
                creditAL.remove(i);
            }
        }
    }

    public void computeCreditBalance() {
        for(CreditTransaction a: creditAL)
        {
            TOTAL_DEBIT += a.getTotal();
        }
        if(this.creditBalance - TOTAL_DEBIT <= 0)
            this.creditBalance = 0;
        else
            this.creditBalance -= TOTAL_DEBIT;

    }

    public void computeTotalDebit() {
        for(CreditTransaction a: creditAL)
        {
            TOTAL_DEBIT += a.getTotal();
        }
    }

    public void deductToCreditBalance(double fund){this.creditBalance-= fund;}
    public void deductToDebt(double fund){
        this.TOTAL_DEBIT -= fund;
    }
    
}
