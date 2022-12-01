package com.example.emmortalevents;

import android.app.Person;

public class Employees {

    private String PersonName;
    private String Event;
    private String Purpose;
    private String Address;
    private String Price;
    private String Email;
    private String Phone;


    public Employees() {
        //public no-arg constructor needed
    }

    public Employees(String personName, String event, String purpose, String address, String price, String email, String phone) {
        PersonName = personName;
        Event = event;
        Purpose = purpose;
        Address = address;
        Price = price;
        Email = email;
        Phone = phone;
    }

    public String getPersonName() {
        return PersonName;
    }

    public String getEvent() {
        return Event;
    }

    public String getPurpose() {
        return Purpose;
    }

    public String getAddress() {
        return Address;
    }

    public String getPrice() {
        return Price;
    }

    public String getEmail() {
        return Email;
    }

    public String getPhone() {
        return Phone;
    }
}
