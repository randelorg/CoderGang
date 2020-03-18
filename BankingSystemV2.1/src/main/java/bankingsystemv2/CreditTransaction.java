/*
 * @Title:
 * @Description:
 * 
 * @Author:
 * @Date Created:
 */
package bankingsystemv2;

import com.sun.xml.internal.ws.addressing.WsaActionUtil;

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
        //this.interestRate = interestRate;
        this.interestRate = Bank.getINTEREST();
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
        try {
            String[] DATE = this.transactionDate.split(Pattern.quote("T"));
            String[] MMDDYY = DATE[0].split("-");
//       System.out.println(this.transactionDate);
            LocalDate startDate = LocalDate.of(Integer.valueOf(MMDDYY[0]), Integer.valueOf(MMDDYY[1]), Integer.valueOf(MMDDYY[2]));
            LocalDate now = LocalDate.now();
            Period period = Period.between(startDate, now);
            double interest = this.amount * (Bank.getINTEREST() / 100);
            total = interest * period.getDays() + this.amount;
            return total;
        }

        catch (NumberFormatException e){
            System.out.println("Invalid time and date");
        }

        catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Too much array size");
        }

        return 0;
    }
}
