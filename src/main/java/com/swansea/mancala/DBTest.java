package com.swansea.mancala;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class DBTest {
    public static void main(String[] args) throws SQLException {
        User user;
        DatabaseConnector database = new DatabaseConnector("jdbc:mysql://localhost:3306/User", "root", "pass");
        while(true){
            System.out.println("Choose '1' for CREATE A NEW USER and '2' for SEE THE EXISTING USER and 3 for LEADERBOARD");
            Scanner sc = new Scanner(System.in);
            int value = sc.nextInt();
            if(value == 1){
                System.out.println("Enter the first name");
                String firstName = sc.next();
                System.out.println("Enter the Last name");
                String lastName = sc.next();
                System.out.println("Enter the no of Games you played");
                int noOfGamesPlayed = sc.nextInt();
                System.out.println("Enter the no of Games you won");
                int noOfGamesWon = sc.nextInt();
                System.out.println("Create your userName");
                String userName = sc.next();
                System.out.println("Create your password");
                String password = sc.next();
                user = new User(firstName,lastName,noOfGamesPlayed,noOfGamesWon,userName,password);
                database.createUser(user);
            }
            else if (value == 2) {
                ArrayList<User> allUser = database.displayUser();
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