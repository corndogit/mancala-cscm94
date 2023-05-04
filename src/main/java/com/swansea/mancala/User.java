package com.swansea.mancala;

import java.sql.Date;
import java.time.LocalDate;

/**
 * The User class represents a user of Mancala game, with their personal details and game statistics.
 * @author Swapnil Sarmah
 */
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

    /**
     * Returns the first name of the user.
     * @return String containing the first name
     */
    public String getFirstName(){
        return firstName;
    }

    /**
     * Returns the last name of the user.
     * @return String containing the last name of the user
     */
    public String getLastName(){
        return lastName;
    }

    /**
     * Returns the number of games played by the user.
     * @return String containing the number of games played by the user
     */
    public int getNoOfGamesPlayed(){
        return noOfGamesPlayed;
    }

    /**
     * Returns the number of games won by the user.
     * @return String containing the number of games won by the user
     */
    public int getNoOfGamesWon(){
        return noOfGamesWon;
    }

    /**
     * Gets the username of the user.
     * @return String containing the username of the user.
     */
    public String getUserName(){
        return userName;
    }

    /**
     * Gets the password hash of the user. Passwords are hashed with SHA-256.
     * @return String containing the password of the user.
     */
    public String getPassword(){
        return password;
    }

    /**
     * Gets the login date of the user
     * @return LocalDate object of the user's login date
     */
    public LocalDate getLoginDate(){
        return loginDate.toLocalDate();
    }
    public float getWinPc(){
        if (noOfGamesPlayed < 1) {
            return 0;
        }
        return ((float) noOfGamesWon / (float) noOfGamesPlayed) * 100;
    }

    /**
     * Overridden string representation of the user object, including their personal details and game statistics.
     * @return String representation of the user object
     */
    public String toString(){
        String template = "User, first name : %s, last name: %s, games played: %d, games Won %d, date logged in: %s";
        return String.format(template, firstName, lastName, noOfGamesPlayed, noOfGamesWon, loginDate);
    }

    /**
     * Gets the profile picture filename of the user.
     * @return String containing the filename of the user's profile picture.
     */
    public String getProfilePicture() {
        return profilePicture;
    }

    /**
     * Sets the profile picture filename of the user.
     * @param filename filename of the profile picture.
     */
    public void setProfilePicture(String filename) {
        this.profilePicture = filename;
    }

    /**
     * Gets the rank of the user for the leaderboard.
     * @return Integer of the rank
     */
    public Integer getRank() {
        return rank;
    }
}
