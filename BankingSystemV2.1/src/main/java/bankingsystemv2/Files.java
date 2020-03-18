/*
 * @Title:
 * @Description:
 * 
 * @Author:
 * @Date Created:
 */
package bankingsystemv2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 *
 * @author Ivan N. Roncesvalles
 */
public class Files 
{
    public static ArrayList<Client> clientAL = new ArrayList<>();
    public static ArrayList<Teller> tellerAL = new ArrayList<>();
    
    public void readFiles()
    {
        readClients();
        readTransactions();
        readCreditTransactions();
        readTellers();
    }
    
    public void saveFiles()
    {
        saveClients();
        saveTransactions();
        saveCreditTransactions();
        saveTellers();
    }
    
    public void readClients()
    {
        File f = new File("Clients.txt");
        if(f.exists())
        {
            try
            {
                FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);
                String line = br.readLine();
                while(line != null)
                {
                    String[] details = line.split(Pattern.quote("|"));
                    Name name = new Name(details[0], details[1], details[2], details[3]);
                    Person p = new Person(name, details[4], details[5], details[6], details[7], details[8], details[12]);
                    Client acc = new Client(p, Double.parseDouble(details[9]), Double.parseDouble(details[10]), details[11]);
                    if(clientAL.contains(acc) == false)
                        clientAL.add(acc);
                    
                    line = br.readLine();
                }
                br.close();
            }
            catch(IOException e)
            {
                System.out.println("Exception in Clients.txt");
            }
        }
        else
        {
            System.out.println("File not Found");
        }
    }
    
    public void readTransactions()
    {
        File fa = new File("Transactions.txt");
        if(fa.exists())
        {
            try
            {
                FileReader fr = new FileReader(fa);
                BufferedReader br = new BufferedReader(fr);
                String line = br.readLine();
                while(line != null)
                {
                    String[] details = line.split(Pattern.quote("|"));
                    for(int i = 0; i < clientAL.size(); i++)
                    {
                        if(clientAL.get(i).getClientID().equalsIgnoreCase(details[0]))
                        {
                            for(int j = 1; j < details.length; j+=4)
                            {
                                TransactionHistory t = new TransactionHistory(details[j],details[j+1], Double.parseDouble(details[j+2]), details[j+3]);
                                clientAL.get(i).addTransaction(t);
                            }
                        }
                    }
                    line = br.readLine();
                }
                br.close();
            }
            catch(IOException e)
            {
                System.out.println("Exception in Transactions.txt");
            }
        }
        else
        {
            System.out.println("File not Found");
        }
    }
    
    public void readCreditTransactions()
    {
        File fa = new File("Credits.txt");
        if(fa.exists())
        {
            try
            {
                FileReader fr = new FileReader(fa);
                BufferedReader br = new BufferedReader(fr);
                String line = br.readLine();
                while(line != null)
                {
                    String[] details = line.split(Pattern.quote("|"));
                    for(int i = 0; i < clientAL.size(); i++)
                    {
                        if(clientAL.get(i).getClientID().equalsIgnoreCase(details[0]))
                        {
                            for(int j = 1; j < details.length; j+=3)
                            {
                                CreditTransaction ct = new CreditTransaction(details[j],Double.parseDouble(details[j+1]), Double.parseDouble(details[j+2]));
                                clientAL.get(i).addCreditTransaction(ct);
                            }
                        }
                    }
                    line = br.readLine();
                }
                br.close();
            }
            catch(IOException e)
            {
                System.out.println("Exception in Transactions.txt");
            }
        }
        else
        {
            System.out.println("File not Found");
        }
    }
    
    public void readTellers()
    {
        try
        {
            File f = new File("Tellers.txt");
            
            if(f.exists())
            {
                FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);
                String line = br.readLine();
                while(line != null)
                {
                    String[] details = line.split("\\|");
                    Name name = new Name(details[1], details[2], details[3], details[4]);
                    Person p = new Person(name, details[5], details[6], details[7], details[9], details[10], details[8]);
                    Teller tellerAcc = new Teller(p,details[0],details[11], details[12]);
                    
                    if(tellerAL.contains(tellerAcc) == false)
                        tellerAL.add(tellerAcc);
                    
                    line = br.readLine();
                }
                br.close();
            }
        }
        catch(IOException e)
        {
            
        }
    }
    
    public void saveTellers()
    {
        File f = new File("Tellers.txt");
        if(f.exists())
        {
            try
            {
                FileWriter fw = new FileWriter(f);
                PrintWriter pw = new PrintWriter(fw);
                for(int i = 0; i < tellerAL.size(); i++) 
                {
                    String line = String.format("%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s",
                            String.valueOf(tellerAL.get(i).getTellerID()), tellerAL.get(i).getFirstName(),
                            tellerAL.get(i).getMiddleName(), tellerAL.get(i).getSurname(),
                            tellerAL.get(i).getExtensionName(), tellerAL.get(i).getAddress(),
                            tellerAL.get(i).getContactNo(), tellerAL.get(i).getCitizenship(),
                            tellerAL.get(i).getMaritalStatus(), tellerAL.get(i).getGender(),
                            tellerAL.get(i).getBirthdate(), tellerAL.get(i).getUsername(),
                            tellerAL.get(i).getPassword());
                            
                    pw.println(line);
                }
                pw.close();
            }
            catch(IOException e)
            {
                System.out.println("IOException in Tellers.txt");
            }
            
        }
        else
        {
            System.out.println("Tellers.txt not found");
        }
    }
    
    public void saveClients()
    {
        File f = new File("Clients.txt");
        if(f.exists())
        {
            try
            {
                FileWriter fw = new FileWriter(f);
                PrintWriter pw = new PrintWriter(fw);
                for(int i = 0; i < clientAL.size(); i++) 
                {
                    String line = String.format("%s|%s|%s|%s|%s|%s|%s|%s|%s|%.2f|%.2f|%s|%s",
                            clientAL.get(i).getFirstName(), clientAL.get(i).getMiddleName(),
                            clientAL.get(i).getSurname(), clientAL.get(i).getExtensionName(),
                            clientAL.get(i).getAddress(), clientAL.get(i).getContactNo(),
                            clientAL.get(i).getCitizenship(), clientAL.get(i).getGender(),
                            clientAL.get(i).getBirthdate(), clientAL.get(i).getCreditLimit(),
                            clientAL.get(i).getSavingsBalance(), clientAL.get(i).getClientID(),
                            clientAL.get(i).getMaritalStatus());
                    pw.println(line);
                }
                pw.close();
            }
            catch(IOException e)
            {
                System.out.println("IOException in Accounts.txt");
            }
            
        }
        else
        {
            System.out.println("Clients.txt not found");
        }
    }
    
    public void saveTransactions()
    {
        File fs = new File("Transactions.txt");
        if(fs.exists())
        {
            try
            {
                FileWriter fw = new FileWriter(fs);
                PrintWriter pw = new PrintWriter(fw);
                for(int i = 0; i < clientAL.size(); i++)
                {
                    pw.print(clientAL.get(i).getClientID());
                    for(int j = 0; j < clientAL.get(i).getTransacAL().size(); j++)
                    {
                        pw.print("|" + clientAL.get(i).getTransacAL().get(j).getTransaction() + 
                                "|" + clientAL.get(i).getTransacAL().get(j).getTransactionType() +
                                "|" + clientAL.get(i).getTransacAL().get(j).getAmount() + 
                                "|" + clientAL.get(i).getTransacAL().get(j).getDate());
                    }
                    pw.println();
                }
                pw.close();
            }
            catch(IOException e)
            {
                System.out.println("IOException in Transaction.txt");
            }
        }
        else
        {
            System.out.println("Transactions.txt not Found");
        }
    }
    
    public void saveCreditTransactions()
    {
        File fs = new File("Credits.txt");
        if(fs.exists())
        {
            try
            {
                FileWriter fw = new FileWriter(fs);
                PrintWriter pw = new PrintWriter(fw);
                for(int i = 0; i < clientAL.size(); i++)
                {
                    pw.print(clientAL.get(i).getClientID());
                    for(int j = 0; j < clientAL.get(i).getCreditAL().size(); j++)
                    {
                        pw.print("|" + clientAL.get(i).getCreditAL().get(j).getTransactionDate() +
                                "|" + clientAL.get(i).getCreditAL().get(j).getAmount() + 
                                "|" + clientAL.get(i).getCreditAL().get(j).getInterestRate());
                    }
                    pw.println();
                }
                pw.close();
            }
            catch(IOException e)
            {
                System.out.println("IOException in Credits.txt");
            }
        }
        else
        {
            System.out.println("Credits.txt not Found");
        }
    }
}
