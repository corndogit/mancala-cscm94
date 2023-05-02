package com.swansea.mancala;

import java.sql.*;
import java.util.ArrayList;
import java.time.LocalDate;

public class DatabaseConnector {
    private final Connection connection;

    public DatabaseConnector(String URL, String username, String password) throws SQLException {
        connection = DriverManager.getConnection(URL,username,password);
    }

    /**
     * Factory method for creating a new database using the values stored in the DBLogin enum.
     * @return DatabaseConnection associated with the database
     * @throws SQLException if database is not reachable
     */
    public static DatabaseConnector create() throws SQLException {
        return new DatabaseConnector(
                DBLogin.URL.toString(),
                DBLogin.USERNAME.toString(),
                DBLogin.PASSWORD.toString()
        );
    }

    public void createUser(User user) throws SQLException{
        String query = Query.insert;
        PreparedStatement pst = connection.prepareStatement(query);
        pst.setString(1, user.getFirstName());
        pst.setString(2, user.getLastName());
        pst.setInt(3, 0);
        pst.setInt(4, 0);
        pst.setString(5, user.getUserName());
        pst.setString(6, user.getPassword());
        pst.setDate(7, java.sql.Date.valueOf(LocalDate.now()));
        pst.setFloat(8, 0);

        System.out.println("\nps = "+pst);
        System.out.println(user);
        pst.executeUpdate();
        pst.close();
    }
    public void createLeaderBoard() throws SQLException{
        String query = Query.leaderBoard1;
        PreparedStatement pst = connection.prepareStatement(query);
        pst.executeUpdate();
    }

    public boolean validateLogin(String username, String password) throws SQLException {
        String query = Query.validateLogin;
        PreparedStatement pst = connection.prepareStatement(query);
        pst.setString(1, username);
        pst.setString(2, password);
        ResultSet resultSet = pst.executeQuery();

        String queryUsername;
        String queryPassword;
        while (resultSet.next()) {
            queryUsername = resultSet.getString("userName");
            queryPassword = resultSet.getString("password");
            if (username.equals(queryUsername) && password.equals(queryPassword)) {
                return true;
            }
        }
        return false;
    }

    public void updateProfilePicture(String filename, User user) throws SQLException{
        String query = Query.updateProfilePicture;
        PreparedStatement pst = connection.prepareStatement(query);
        pst.setString(1, filename);
        pst.setString(2, user.getUserName());
        pst.executeUpdate();
    }

    public boolean userExistsInDatabase(String username) throws SQLException {
        String query = Query.usernameExists;
        PreparedStatement pst = connection.prepareStatement(query);
        pst.setString(1, username);
        ResultSet resultSet = pst.executeQuery();

        String queryUsername;
        while (resultSet.next()) {
            queryUsername = resultSet.getString("userName");
            if (username.equals(queryUsername)) {
                return true;
            }
        }
        return false;
    }

    public void removeDuplicate() throws SQLException{
        String query = Query.removeDuplicate;
        PreparedStatement pst = connection.prepareStatement(query);
        pst.executeUpdate();
    }

    public User getUserByUsername(String username) throws SQLException {
        String query = Query.getUser;
        PreparedStatement pst = connection.prepareStatement(query);
        pst.setString(1, username);
        ResultSet resultSet = pst.executeQuery();
        if (resultSet.next()) {
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            int gamesPlayed = resultSet.getInt("gamesPlayed");
            int gamesWon = resultSet.getInt("gamesWon");
            String userName = resultSet.getString("userName");
            String profilePicture = resultSet.getString("profilePicture");
            LocalDate loginDate = resultSet.getDate("loginDate").toLocalDate();

            return new User(firstName, lastName, gamesPlayed, gamesWon, userName, profilePicture, loginDate);
        }
        throw new SQLException("No user found with username '%s'", username);
    }

    public ArrayList<User> displayAllUsers() throws SQLException {
        ArrayList<User> list = new ArrayList<>();
        String query = Query.getAllUsers;
        Statement st = connection.createStatement();
        System.out.println("\nst = "+st);
        ResultSet resultSet = st.executeQuery(query);
        while(resultSet.next()){
            User user = new User(
                    resultSet.getString("firstName"),
                    resultSet.getString("lastName"),
                    resultSet.getInt("gamesPlayed"),
                    resultSet.getInt("gamesWon"),
                    resultSet.getString("userName"),
                    resultSet.getString("profilePicture"),
                    resultSet.getDate("loginDate").toLocalDate());
            list.add(user);
        }
        st.close();
        return list;
    }

    public void displayLeaderBoard() throws SQLException {
        String query = Query.displayLeaderboard;
        PreparedStatement stmt = connection.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();
        System.out.println("LeaderBoard:");
        System.out.println("Rank\tUser Name\tWin Percentage");
        while (rs.next()) {
            int rank = rs.getInt("PlayerRank");
            String userName = rs.getString("userName");
            float winPc = rs.getFloat("winPc");
            System.out.println(rank + "\t" + userName + "\t\t" + winPc + "%");
        }
        rs.close();
        stmt.close();
    }
}
