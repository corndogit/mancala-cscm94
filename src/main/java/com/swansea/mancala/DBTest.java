package com.swansea.mancala;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class DBTest {
    public static void main(String[] args) throws SQLException {
        User user;
        DatabaseConnector database = DatabaseConnector.create();
        while(true){
            System.out.println("Choose '1' for CREATE A NEW USER and '2' for SEE THE EXISTING USER and 3 for LEADERBOARD");
            Scanner sc = new Scanner(System.in);
            int value = sc.nextInt();
            if(value == 1){
                String firstName = "test";
                String lastName = "testington";
                int noOfGamesPlayed = 2;
                int noOfGamesWon = 1;
                String userName = "test";
                String password = "test";
                user = new User(firstName,lastName,noOfGamesPlayed,noOfGamesWon,userName,password);
                database.createUser(user);
            }
            else if (value == 2) {
                ArrayList<User> allUser = database.displayAllUsers();
                for(User u : allUser){
                    System.out.println(u.toString());
                }
            } else if (value == 3) {
                database.createLeaderBoard();
                database.displayLeaderBoard();
            } else {
                break;
            }
        }
    }
}