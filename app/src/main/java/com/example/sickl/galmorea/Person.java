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
    private String childAcc = "Child";
    private String parentAcc = "Parent";


    public Person(){

    }

   public Person(String strFirstName, String strLastName, String strDob, String strEmail, String strPassword) {
            this.firstname = strFirstName;
            this.lastname = strLastName;
            this.dob = strDob;
            this.email = strEmail;
            this.password = strPassword;
    }


    //Setters
    public void setFirstName(String strFirstName) {

        this.firstname = strFirstName;

    }

    public void setLastName(String strLastName) {

        this.lastname = strLastName;
    }

    public void setBirthDate(String strDob) {
        this.dob = strDob;
    }

    public void setEmail(String strEmail) {

        this.email = strEmail;
    }

    public void setPassword(String strPassword) {

        this.password = strPassword;
    }

    public void setChildAcc(String strChildAcc){
        this.childAcc = strChildAcc;
    }

   /* public void setParentAcc(String strParentAcc){
        this.childAcc = strParentAcc;
    }*/

    //Getters
    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getBirthDate() {
        return dob;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getChildAcc(){
        return childAcc;
    }

    public String getParentAcc(){
        return parentAcc;
    }

}