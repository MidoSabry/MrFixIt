package com.example.fixawy.Pojos;

public class MakeOrder {
    String time;
    String date;
    int    paymentMethod;
    int    typeOfOrder;
    String location;
    String phone;
    String details;

    public MakeOrder(String time, String date, int paymentMethod, int typeOfOrder, String location, String phone, String details) {
        this.time = time;
        this.date = date;
        this.paymentMethod = paymentMethod;
        this.typeOfOrder = typeOfOrder;
        this.location = location;
        this.phone = phone;
        this.details = details;
    }

    public MakeOrder() {
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(int paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public int getTypeOfOrder() {
        return typeOfOrder;
    }

    public void setTypeOfOrder(int typeOfOrder) {
        this.typeOfOrder = typeOfOrder;
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

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
