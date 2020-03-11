/*
 * @Title:
 * @Description:
 * 
 * @Author:
 * @Date Created:
 */
package bankingsystemv2;

import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Pattern;

/**
 *
 * @author Ivan N. Roncesvalles
 */
public class Person extends Name{

    public void setAge(int age) {
        this.age = age;
    }

    private int age;
    private String address;
    private String contactNo;
    private String citizenship;
    private String gender;
    private String birthdate;
    private String maritalStatus;

    public Person(Name n,String address, String contactNo, String citizenship, String gender, 
            String birthdate, String maritalStatus) 
    {
        super(n.getFirstName(), n.getMiddleName(), n.getSurname(),  n.getExtensionName());
        this.age = getAge(birthdate);
        this.address = address;
        this.contactNo = contactNo;
        this.citizenship = citizenship;
        this.gender = gender;
        this.birthdate = birthdate;
        this.maritalStatus = maritalStatus;
    }
    public Person(Person p)
    {
        super(p.getFirstName(), p.getMiddleName(), p.getSurname(),  p.getExtensionName());
        this.age = this.getAge(p.getBirthdate());
        this.address = p.getAddress();
        this.contactNo = p.getContactNo();
        this.citizenship = p.getCitizenship();
        this.gender = p.getGender();
        this.birthdate = p.getBirthdate();
        this.maritalStatus = p.getMaritalStatus();
    }
    
    public Person()
    {

    }
    
    public Person(String firstName, String middleName, String surname, String extensionName, String birthDate)
    {
        super(firstName, middleName, surname, extensionName);
        this.birthdate = birthDate;
    }
    
    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getMaritalStatus() {
        return this.maritalStatus;
    }
    
    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getGender() {
        return gender;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public void setCitizenship(String citizenship) {
        this.citizenship = citizenship;
    }

    public final int getAge(String bdate)
    {
        if(!bdate.isEmpty())
        {
            String[] dates = bdate.split(Pattern.quote("-"));
            LocalDate bday = LocalDate.of(Integer.valueOf(dates[0]), Integer.valueOf(dates[1]),Integer.valueOf(dates[2]));
            LocalDate now = LocalDate.now();
            Period period = Period.between(bday, now);
            this.age = period.getYears();
            return period.getYears();
        }
        else
        {
            return 0;
        }

    }

    public String getAddress() {
        return address;
    }

    public String getContactNo() {
        return contactNo;
    }

    public String getCitizenship() {
        return citizenship;
    }

    public int getAge() {
        return age;
    }
}
