package bankingsystemv2;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class BankTransactions extends Bank implements IBankTransactions {

    @Override
    public int sendFund(String receiverID, double fund) {
        for (Client cl: Bank.ldClient){
            if(receiverID.equals(String.valueOf(cl.getClientID()))){
                cl.getTransacAL().add(new TransactionHistory("Savings","Transfer fund",fund,
                        String.valueOf(LocalDateTime.now())));
                cl.addToSavings(fund);
                return 1;
            }
        }
        
        return  0;
    }

    @Override
    public int withdraw(double fund) {
        for (Client cl: Bank.ldClient){
            if(Bank.getSessionBackId().equals(String.valueOf(cl.getClientID())))
            {
                if(fund <= cl.getSavingsBalance()) {
                    cl.getTransacAL().add(new TransactionHistory("Savings", "Withdraw", fund,
                            String.valueOf(LocalDateTime.now())));
                    cl.deductToSavings(fund);
                    return 1;
                }else
                    return 2; //if withdrawal fund is greater than the savings balance of the client
            }
        }
        return 0;//if not found
    }

    @Override
    public int deposit(double fund) {
        for (Client cl: Bank.ldClient){
            if(Bank.getSessionBackId().equals(String.valueOf(cl.getClientID()))){
                if(cl.getSavingsBalance() >= 0) {
                    cl.getTransacAL().add(new TransactionHistory("Savings", "Deposit", fund,
                            String.valueOf(LocalDateTime.now())));
                    cl.addToSavings(fund);
                }else{
                    if(fund >= Bank.getInitialDeposit()) {
                        cl.getTransacAL().add(new TransactionHistory("Savings", "Deposit", fund,
                                String.valueOf(LocalDateTime.now())));
                        return 1;
                    }else{
                        return 2;
                    }
                }
                return 1;
            }
        }
        return 0;
    }

    @Override
    public int payCredit(double fund) {
        for (Client cl: Bank.ldClient){
            if(Bank.getSessionBackId().equals(String.valueOf(cl.getClientID()))){
                cl.getCreditAL().add(new CreditTransaction(String.valueOf(LocalDateTime.now()), fund, Bank.getINTEREST()));
                cl.computeTotalDebit(); // compute the total debt of the client
                cl.deductToDebt(fund); // deducts the paid amount to the total debt
                Bank.AddBankNetAmount(fund); //add the paid amount to the bank net fund
                cl.computeCreditBalance(); // computes the credit available credit balance
                return 1;
            }
        }
        return 0;
    }

    @Override
    public int withdrawCredit(double fund) {
        int i =0;
        for (Client cl: Bank.ldClient){
            if(Bank.getSessionBackId().equals(String.valueOf(cl.getClientID())))
            {
                if(fund <= cl.getSavingsBalance()) {
                    cl.getCreditAL().add(new CreditTransaction(String.valueOf(LocalDateTime.now()), fund, Bank.getINTEREST()));
//                    cl.computeCreditBalance();
                    cl.deductToCreditBalance(fund); //deducts the fund to the total credit balance
                    return 1;
                }else
                    return 2; //if withdrawal fund is greater than the savings balance of the client
            }
        }
        return 0;//if not found
    }
}
