package com.example.myapplication;

public class UserHelperClass {
    String name, email, phone, password;


    public UserHelperClass() {
    }

    public UserHelperClass(String name, String email, String phone,  String password) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }

}