package Models;

public class AddUser {

    String name,email,phone_number,bill,remark;

    public AddUser(){}

    public AddUser(String fullName, String email, String phone_number,String bill,String remark) {
        this.name = fullName;
        this.email = email;
        this.phone_number = phone_number;
        this.bill=bill;
        this.remark=remark;
    }

    public String getBill() {
        return bill;
    }

    public void setBill(String bill) {
        this.bill = bill;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }
}
