package com.example.fixawy.Pojos;

public class Answer {
    public String replay,phone;

    public Answer(String replay) {

        this.replay = replay;
    }

    public Answer(String replay, String phone) {
        this.replay = replay;
        this.phone = phone;
    }

    public String getReplay() {
        return replay;
    }

    public void setReplay(String replay) {
        this.replay = replay;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Answer() {
    }

}

