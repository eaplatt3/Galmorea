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

//Child Class of Person
//Geared to Verify Users Identity

public class Account extends Person {

    //Variables
    private String login;
    private String password;

    //Constructor
    Account(){

        login = getEmail();
        password = getPassword();
    }


    //Setters
    public void setLogin(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    //Getters
    @Override
    public void setPassword(String password) {
        super.setPassword(password);
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }


}
