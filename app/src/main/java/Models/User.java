package Models;

public class User {

    private String name,email,phn_number,password;



    public User(String name, String email, String phn_number, String password) {
        this.name = name;
        this.email = email;
        this.phn_number = phn_number;
        this.password = password;
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

    public String getPhn_number() {
        return phn_number;
    }

    public void setPhn_number(String phn_number) {
        this.phn_number = phn_number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
