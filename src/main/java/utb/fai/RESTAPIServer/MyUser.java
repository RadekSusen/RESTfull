package utb.fai.RESTAPIServer;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.jar.Attributes.Name;
import java.util.regex.Pattern;

@Entity
public class MyUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;

    public MyUser() {
    }

    public MyUser(String name, String email, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public boolean isUserDataValid() {
        // Add your validation logic here (e.g., email and phone number format
        // validation)
        return validMail(this.email) && validPhone(this.phoneNumber) && validName(this.name);
    }

    private boolean validMail(String email) {
        String emailReg = "^[A-Za-z0-9\\\\+_\\\\.-]+@(.+)$";
        Pattern pattern = Pattern.compile(emailReg);
        return pattern.matcher(email).matches();
    }

    private boolean validPhone(String phoneNumber) {
        String phoneReg = "^\\+[0-9]*$";
        Pattern pattern = Pattern.compile(phoneReg);
        return pattern.matcher(phoneNumber).matches();
    }

    private boolean validName(String name) {
        return name.length() > 3;
    }

    // TODO: Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
