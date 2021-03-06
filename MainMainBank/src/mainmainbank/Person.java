
package mainmainbank;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Randel and Michael
 * @usage for the common person identity fields
 */
public class Person {

    private String firstName;
    private String middleName;
    private String lastname;
    private String extensionName;
    private int age;
    private String birthDay;
    private String gender;
    private String address;
    private String maritaStatus;
    private String citizenship;

    public Person(){

    }

    public Person(String firstName, String middleName, String lastName) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastname = lastName;
    }

    public Person(String firstName, String middleName, String lastName, String address, int age) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastname = lastName;
        this.address = address;
        this.age = age;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return this.middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastname() {
        return this.lastname;
    }

    public void setLastname(String LastName) {
        this.lastname = LastName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge(){return this.age;}

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMaritaStatus() {
        return this.maritaStatus;
    }

    public void setMaritaStatus(String maritaStatus) {
        this.maritaStatus = maritaStatus;
    }

    public String getCitizenship() {
        return this.citizenship;
    }

    public void setCitizenship(String citizenship) {
        this.citizenship = citizenship;
    }

    public String getBirthDay() {
        return this.birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getExtensionName() {
        return this.extensionName;
    }

    public void setExtensionName(String extensionName) {
        this.extensionName = extensionName;
    }
    
}
