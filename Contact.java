import java.util.*;

public class Contact {
    private String id;
    private String name;
    private String contactNumber;
    private String company;
    private double salary;
    private String birthday;

    public Contact() {}

    public Contact(String id, String name, String contactNumber, String company, double salary, String birthday) {
        this.id = id;
        this.name = name;
        this.contactNumber = contactNumber;
        this.company = company;
        this.salary = salary;
        this.birthday = birthday;
    }

    
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getCompany() {
        return company;
    }

    public double getSalary() {
        return salary;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}
