package mainmainbank;

public class BankTransactions extends Bank implements IBankTransactions {

    @Override
    public int sendFund(String receiverID, double fund) {
        for (Client cl: Bank.ldClient){
            if(receiverID.equals(String.valueOf(cl.getClientID()))){
                cl.addToSavings(fund);
                return 1;
            }
        }
        return  0;
    }

    @Override
    public int withdraw(double fund) {
        for (Client cl: Bank.ldClient){
            if(Bank.getSessionBackId().equals(String.valueOf(cl.getClientID()))){
               cl.deductToSavings(fund);
                return 1;
            }
        }
        return 0;
    }

    @Override
    public int deposit(double fund) {
        for (Client cl: Bank.ldClient){
            if(Bank.getSessionBackId().equals(String.valueOf(cl.getClientID()))){
                cl.addToSavings(fund);
                return 1;
            }
        }
        return 0;
    }

    @Override
    public void payCredit(double fund) {

    }

    @Override
    public void computeMonthlyPayment() {

    }

    @Override
    public void computeAddFundToBank() {

    }
}
