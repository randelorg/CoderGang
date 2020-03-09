package mainmainbank;

public interface IBankTransactions {
    int sendFund(String receiverID,double fund);
    int withdraw(double fund);
    int deposit(double fund);
    void payCredit(double fund);
    void computeMonthlyPayment();
    void computeAddFundToBank();
}
