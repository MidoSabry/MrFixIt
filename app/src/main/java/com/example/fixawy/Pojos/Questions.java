package com.example.fixawy.Pojos;

public class Questions {

    public String phone,question;


    public Questions(String phone, String question) {
        this.phone = phone;
        this.question = question;
    }

    public Questions() {
    }

    public Questions(String question) {
        this.question = question;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
