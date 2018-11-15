package com.example.sickl.galmorea;


//Earl Platt III
//BCS421 - Android
//9/30/2018
//App That is A Login & Registration Page
/////////////////////////////////
//Input: User Enters
//       -Username & Password
//       -First Name, Last Name,
//        Date of Birth, Email,
//Output: Allows User to Register & Login


//Class to Hold Persons Information
public class Person {

    //Variables
    private String firstname;
    private String lastname;
    private String dob;
    private String email;
    private String password;

    //Constructor
    public Person() {

        firstname = "John";
        lastname = "Doe";
        dob = "01/01/1900";
        email = "JohnDoe@email.com";
        password = "ABC123";

    }

    //Setters
    public void setFirstname(String firstname) {

        this.firstname = firstname;

    }

    public void setLastname(String lastname) {

        this.lastname = lastname;
    }

    public void setBirthDate(String dob) {
        this.dob = dob;
    }

    public void setEmail(String email) {

        this.email = email;
    }

    public void setPassword(String password) {

        this.password = password;
    }

    //Getters
    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getBirthmonth() {
        return dob;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

}

