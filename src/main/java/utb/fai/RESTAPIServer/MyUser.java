package utb.fai.RESTAPIServer;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class MyUser {
    @Id
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;

    public  Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public  String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail(){
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

    public boolean isUserDataValid() {
        return isEmailValid(this.email) && isPhoneNumberValid(this.phoneNumber) && isNameValid(this.name) && isIdValid(this.id);
    }

    private boolean isEmailValid(String email) {
        return email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");
    }

    private boolean isPhoneNumberValid(String phoneNumber) {
        return phoneNumber.matches("^(\\\\+\\\\d{1,3}( )?)?((\\\\(\\\\d{1,3}\\\\))|\\\\d{1,3})[- .]?\\\\d{3,4}[- .]?\\\\d{4}$");
    }

    private boolean isNameValid(String name) {
        return name.matches("[a-zA-Z]+");
    }

    private boolean isIdValid(Long id) {
        return id >= 1;
    }
    
}
