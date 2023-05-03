package com.swansea.mancala;

import java.sql.Date;
import java.time.LocalDate;

public class User {
    private Integer rank = null;
    private String firstName;
    private String lastName;
    private int noOfGamesPlayed;
    private int noOfGamesWon;
    private String profilePicture;
    private Date loginDate;
    private String userName;
    private String password;

    @Deprecated
    public User(String firstName, String lastName, int noOfGamesPlayed, int noOfGamesWon, String userName,String password){
        this.firstName = firstName;
        this.lastName = lastName;
        this.noOfGamesPlayed = noOfGamesPlayed;
        this.noOfGamesWon = noOfGamesWon;
        this.userName = userName;
        this.password = password;
    }


    /**
     * A User instance used for displaying the user's profile.
     * @param firstName User's first name
     * @param lastName User's last name
     * @param noOfGamesPlayed Number of games played
     * @param noOfGamesWon Number of games won
     * @param userName User's username
     * @param profilePicture User's profile picture path
     * @param loginDate User's latest login date
     */
    public User(String firstName, String lastName, int noOfGamesPlayed, int noOfGamesWon,
                String userName, String profilePicture, LocalDate loginDate){
        this.firstName = firstName;
        this.lastName = lastName;
        this.noOfGamesPlayed = noOfGamesPlayed;
        this.noOfGamesWon = noOfGamesWon;
        this.userName = userName;
        this.profilePicture = profilePicture;
        this.loginDate = Date.valueOf(loginDate);
    }

    /**
     * A basic user instance containing only a username. Used for the guest player in-game.
     * @param username Name of the player
     */
    public User(String username) {
        this.userName = username;
    }


    /**
     * A basic user instance for loading leaderboard scores
     * @param username User's username
     * @param noOfGamesPlayed Number of games played
     * @param noOfGamesWon Number of games won
     */
    public User(int rank, String username, int noOfGamesPlayed, int noOfGamesWon) {
        this.rank = rank;
        this.userName = username;
        this.noOfGamesPlayed = noOfGamesPlayed;
        this.noOfGamesWon = noOfGamesWon;
    }

    /**
     * A new user to register in the database
     * @param firstName the user's first name
     * @param lastName the user's last name
     * @param userName the user's username
     * @param password the user's password
     */
    public User(String firstName, String lastName, String userName, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.noOfGamesPlayed = 0;
        this.noOfGamesWon = 0;
        this.loginDate = Date.valueOf(LocalDate.now());
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
        return loginDate.toLocalDate();
    }
    public float getWinPc(){
        if (noOfGamesPlayed < 1) {
            return 0;
        }
        return ((float) noOfGamesWon / (float) noOfGamesPlayed) * 100;
    }
    public String toString(){
        return " first name : "+firstName+", last name: "+lastName+", Games played: "+noOfGamesPlayed+" Games Won "+noOfGamesWon+" Date of logged in: "+loginDate;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String filename) {
        this.profilePicture = filename;
    }

    public Integer getRank() {
        return rank;
    }
}
