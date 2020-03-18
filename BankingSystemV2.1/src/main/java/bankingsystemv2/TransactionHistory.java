/*
 * @Title:
 * @Description:
 * 
 * @Author:
 * @Date Created:
 */
package bankingsystemv2;

/**
 *
 * @author Ivan N. Roncesvalles
 */
public class TransactionHistory {
    private String transaction;
    private String transactionType;
    private Double amount;
    private String date;

    public TransactionHistory(String transaction,String transactionType, Double amount, String date) {
        this.transaction = transaction;
        this.transactionType = transactionType;
        this.amount = amount;
        this.date = date;
    }

    public TransactionHistory() {
        this.transactionType = "";
        this.amount = 0.0;
        this.date = "";
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
    public String getTransactionType() {
        return transactionType;
    }

    public Double getAmount() {
        return amount;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
    
     public void setTransaction(String transaction) {
        this.transaction = transaction;
    }

    public String getTransaction() {
        return this.transaction;
    }
}
