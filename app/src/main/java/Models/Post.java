package Models;

import android.content.Context;

import androidx.annotation.NonNull;

public class Post {
    private String Postid;
    private String name,email,phone_number, bill, remark;
    private String postedBy;





    public Post(String postid, String name, String email, String phone_number, String bill, String remark, String postedBy, long postedAt) {
        Postid = postid;
        this.name = name;
        this.email = email;
        this.phone_number = phone_number;
        this.bill = bill;
        this.remark = remark;
        this.postedBy = postedBy;
    }

    public Post() {
    }

    public String getPostid() {
        return Postid;
    }

    public void setPostid(String postid) {
        Postid = postid;
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

    public String getPostedBy() {
        return postedBy;
    }

    public void setPostedBy(String postedBy) {
        this.postedBy = postedBy;
    }
}
