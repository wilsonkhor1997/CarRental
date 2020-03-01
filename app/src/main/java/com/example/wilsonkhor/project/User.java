package com.example.wilsonkhor.project;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by Wilson Khor on 15/12/2018.
 */

public class User {
    private String nric;
    private String name;
    private String phoneNumber;
    private String date;
    private String time;
    private String rentHour;
    private String address;
    private String address1;
    private String address2;
    private String gender;
    private String selection;
    private int payment;
    private String key;
    final FirebaseUser user1 = FirebaseAuth.getInstance().getCurrentUser();

    public User() {
    }

    public User(String nric, String name, String phoneNumber, String email, String date, String time, String rentHour, String address, String address1, String address2) {
        this.nric = nric;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.date = date;
        this.time = time;
        this.rentHour = rentHour;
        this.address = address;
        this.address1 = address1;
        this.address2 = address2;
    }

    public User(String gender, String selection, int payment) {
        this.gender = gender;
        this.selection = selection;
        this.payment = payment;
    }

    public User(String nric, String name, String phoneNumber, String date, String time, String rentHour, String address, String address1, String address2, String gender, String selection, int payment) {
        this.nric = nric;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.date = date;
        this.time = time;
        this.rentHour = rentHour;
        this.address = address;
        this.address1 = address1;
        this.address2 = address2;
        this.gender = gender;
        this.selection = selection;
        this.payment = payment;
    }

    public String getNric() {
        return nric;
    }

    public void setNric(String nric) {
        this.nric = nric;
    }

    public String getName() {
        return name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public String getRentHour() {
        return rentHour;
    }

    public void setRentHour(String rentHour) {
        this.rentHour = rentHour;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSelection() {
        return selection;
    }

    public void setSelection(String selection) {
        this.selection = selection;
    }

    public int getPayment() {
        return payment;
    }

    public void setPayment(int payment) {
        this.payment = payment;
    }


    public String toString(){
        return this.key;
    }

}