package com.swansea.mancala;

import java.time.LocalDate;

public class User {
    private String firstName;
    private String lastName;
    private int noOfGamesPlayed;
    private int noOfGamesWon;
    private LocalDate loginDate;
    private String userName;
    private String password;
    private float winPc;
    public User(){

    }

    public User(String firstName, String lastName, int noOfGamesPlayed, int noOfGamesWon, String userName,String password){
        this.firstName = firstName;
        this.lastName = lastName;
        this.noOfGamesPlayed = noOfGamesPlayed;
        this.noOfGamesWon = noOfGamesWon;
        this.userName = userName;
        this.password = password;
    }
    public User(String firstName, String lastName, int noOfGamesPlayed, int noOfGamesWon, String userName,LocalDate loginDate){
        this.firstName = firstName;
        this.lastName = lastName;
        this.noOfGamesPlayed = noOfGamesPlayed;
        this.noOfGamesWon = noOfGamesWon;
        this.userName = userName;
        this.loginDate = loginDate;
    }
    public String getFirstName(){

        return firstName;
    }
    public String getLastName(){

        return lastName;
    }
    public int getNoOfGamesPlayed(){
        return noOfGamesPlayed;
    }
    public int getNoOfGamesWon(){

        return noOfGamesWon;
    }
    public String getUserName(){
        return userName;
    }
    public String getPassword(){
        return password;
    }

    public LocalDate getLoginDate(){
        LocalDate currentDate = LocalDate.now();
        this.loginDate = currentDate;
        return loginDate;
    }
    public float getWinPc(){
        winPc = ((float)noOfGamesWon/(float)noOfGamesPlayed)*100;
        return winPc;
    }
    public String toString(){
        return " first name : "+firstName+", last name: "+lastName+", Games played: "+noOfGamesPlayed+" Games Won "+noOfGamesWon+" Date of logged in: "+loginDate;
    }

}
