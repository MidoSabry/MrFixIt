package com.example.fixawy.NotificationToWorker;

public class Data2 {
    private String Title;
    private String Message;

    public Data2(String title, String message) {
        Title = title;
        Message = message;
    }

    public Data2() {
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

}
