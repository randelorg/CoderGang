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
public class Name {
    private String firstName;
    private String middleName;
    private String surname;
    private String extensionName;

    public Name(String firstName, String middleName, String surname, String extensionName) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.surname = surname;
        this.extensionName = extensionName;
    }
    
    public Name()
    {
        this.firstName = "";
        this.middleName = "";
        this.surname = "";
        this.extensionName = "";
    }

    public String getExtensionName() {
        return extensionName;
    }

    public void setExtensionName(String extensionName) {
        this.extensionName = extensionName;
    }
    
    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getSurname() {
        return surname;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
    
    
}
