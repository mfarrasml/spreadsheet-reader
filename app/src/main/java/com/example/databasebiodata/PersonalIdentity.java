package com.example.databasebiodata;

import java.time.LocalDate;

public class PersonalIdentity {
    public String fullName;
    public String dateOfBirth;
    public String address;
    public String phoneNumber;

    public PersonalIdentity(String name, String dob, String addr, String phone){
        fullName = name;
        dateOfBirth = dob;
        address = addr;
        phoneNumber = phone;
    }

    // argument-less constructor
    public PersonalIdentity(){

    }

    /*
    public int age(){
        LocalDate currentDate = new LocalDate.Now();

        String[] dob = dateOfBirth.split("/");

        currentDate.

    }
    */

    // set/update personal identity data
    public void setPersonalIdentity(String name, String dob, String addr, String phone){
        fullName = name;
        dateOfBirth = dob;
        address = addr;
        phoneNumber = phone;
    }

}
