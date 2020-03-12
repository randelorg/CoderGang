/*
 * @Title:
 * @Description:
 * 
 * @Author:
 * @Date Created:
 */
package bankingsystemv2;

import javax.xml.bind.SchemaOutputResolver;
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

    public double getAmount() {
        return this.amount;
    }

    public double getInterestRate() {
        return this.interestRate;
    }
    
    public double getTotal()
    {
        String[] MMDDYY = this.transactionDate.split(Pattern.quote("-"));
        System.out.println(this.transactionDate);
        System.out.println("1 " + MMDDYY[0]);
        System.out.println("2 " + MMDDYY[1]);
        System.out.println("3 " + MMDDYY[2]);
        String[] YEAR_AND_TIME = MMDDYY[2].split("T");
        LocalDate startDate = LocalDate.of(Integer.valueOf(YEAR_AND_TIME[1]), Integer.valueOf(MMDDYY[0]), Integer.valueOf(MMDDYY[1]));
        LocalDate now = LocalDate.now();
        Period period = Period.between(startDate, now);
        double interest = this.amount * (Bank.getINTEREST() / 100);
        total = interest * period.getDays() + this.amount;
        return total;
    }
}
