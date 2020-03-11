/*
 * @Title:
 * @Description:
 * 
 * @Author:
 * @Date Created:
 */
package bankingsystemv2;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.regex.Pattern;

/**
 *
 * @author Ivan N. Roncesvalles
 */
public class CreditTransaction {
    private String transactionDate;
    private Double amount;
    private Double interestRate;
    private Double total;

    public CreditTransaction(String date, Double amount, Double interestRate) {
        this.transactionDate = date;
        this.amount = amount;
        this.interestRate = interestRate;
    }

    public String getTransactionDate() {
        return this.transactionDate;
    }

    public Double getAmount() {
        return this.amount;
    }

    public Double getInterestRate() {
        return this.interestRate;
    }
    
    public Double getTotal()
    {
        String[] MMDDYY = this.transactionDate.split(Pattern.quote("/"));
        String[] YEAR_AND_TIME = MMDDYY[2].split(" ");
        LocalDate startDate = LocalDate.of(Integer.valueOf(YEAR_AND_TIME[0]), Integer.valueOf(MMDDYY[0]), Integer.valueOf(MMDDYY[1]));
        LocalDate now = LocalDate.now();
        Period period = Period.between(startDate, now);
        Double interest = this.amount * (this.interestRate / 100);
        total = interest * period.getDays() + this.amount;
        return total;
    }
}
