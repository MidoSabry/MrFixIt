package com.example.fixawy.Pojos;

public class EmployeeData {
    int emp_id;
    String emp_name;
    String emp_address;
    String emp_image;
    String emp_phone;
    int emp_rate;
    int num_of_jobs;

    public EmployeeData() {
    }

    public EmployeeData(int emp_id, String emp_name, String emp_address, String emp_image, String emp_phone, int emp_rate, int num_of_jobs) {
        this.emp_id = emp_id;
        this.emp_name = emp_name;
        this.emp_address = emp_address;
        this.emp_image = emp_image;
        this.emp_phone = emp_phone;
        this.emp_rate = emp_rate;
        this.num_of_jobs = num_of_jobs;
    }

    public EmployeeData(int emp_id, String emp_name, String emp_address, String emp_image, String emp_phone, int emp_rate) {
        this.emp_id = emp_id;
        this.emp_name = emp_name;
        this.emp_address = emp_address;
        this.emp_image = emp_image;
        this.emp_phone = emp_phone;
        this.emp_rate = emp_rate;
    }

    public int getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(int emp_id) {
        this.emp_id = emp_id;
    }

    public String getEmp_name() {
        return emp_name;
    }

    public void setEmp_name(String emp_name) {
        this.emp_name = emp_name;
    }

    public String getEmp_address() {
        return emp_address;
    }

    public void setEmp_address(String emp_address) {
        this.emp_address = emp_address;
    }

    public String getEmp_image() {
        return emp_image;
    }

    public void setEmp_image(String emp_image) {
        this.emp_image = emp_image;
    }

    public String getEmp_phone() {
        return emp_phone;
    }

    public void setEmp_phone(String emp_phone) {
        this.emp_phone = emp_phone;
    }

    public int getEmp_rate() {
        return emp_rate;
    }

    public void setEmp_rate(int emp_rate) {
        this.emp_rate = emp_rate;
    }

    public int getNum_of_jobs() {
        return num_of_jobs;
    }

    public void setNum_of_jobs(int num_of_jobs) {
        this.num_of_jobs = num_of_jobs;
    }


}
