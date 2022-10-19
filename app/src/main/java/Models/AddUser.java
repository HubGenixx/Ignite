package Models;

public class AddUser {

    String fullName,email,phone_number;

    public AddUser(String fullName, String email, String phone_number) {
        this.fullName = fullName;
        this.email = email;
        this.phone_number = phone_number;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }
}
