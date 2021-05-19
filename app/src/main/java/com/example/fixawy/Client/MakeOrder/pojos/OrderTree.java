package com.example.fixawy.Client.MakeOrder.pojos;

public class OrderTree {
    private int typeOfOrder;
    private String details;
    private String location;
    private String phone;
    private String date;
    private String time;
    private int paymentMethod;

    public int getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(int paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public OrderTree(String location, String phone, String date, String time) {
        this.location = location;
        this.phone = phone;
        this.date = date;
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public OrderTree() {
    }

    public OrderTree(int typeOfOrder, String details) {
        this.typeOfOrder = typeOfOrder;
        this.details = details;
    }

    public int getTypeOfOrder() {
        return typeOfOrder;
    }

    public void setTypeOfOrder(int typeOfOrder) {
        this.typeOfOrder = typeOfOrder;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
