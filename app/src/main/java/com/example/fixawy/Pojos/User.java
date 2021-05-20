package com.example.fixawy.Pojos;

public class User {

    public String userName,email,phone,address,type,password,jobTitle;

    public User(){}

    public User(String userName, String email, String phone, String address, String type, String password) {
        this.userName = userName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.type = type;
        this.password=password;
    }

    public User(String userName, String email, String phone, String address, String type, String password, String jobTitle) {
        this.userName = userName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.type = type;
        this.password = password;
        this.jobTitle = jobTitle;
    }

    public User(String userName, String phone, String address) {
        this.userName = userName;
        this.phone = phone;
        this.address = address;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

