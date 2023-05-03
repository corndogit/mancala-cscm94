package com.swansea.mancala;

import java.sql.*;
import java.util.ArrayList;
import java.time.LocalDate;

public class DatabaseConnector {
    private final Connection connection;

    /**
     * Creates a DatabaseConnector and opens a connection with the provided credentials.
     * @param URL URL to the database
     * @param username Database username
     * @param password Database password
     */
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

    /**
     * Persists a User object in database.
     * @param user the user to store in the database 
     */
    public void createUser(User user) throws SQLException{
        String query = """
                INSERT INTO User (firstName, lastName, gamesPlayed, gamesWon, userName, password, loginDate, winPc)
                VALUES(?, ?, ?, ?, ?, ?, ?, ?)
                """;
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
        String query = """
                INSERT IGNORE INTO Leaderboard (PlayerRank, userName, winPc)
                SELECT ROW_NUMBER() OVER (ORDER BY winPc DESC), userName, winPc
                FROM User
                WHERE (userName, winPc) NOT IN (SELECT userName, winPc FROM leaderBoard)
                ORDER BY winPc DESC;
                """;
        PreparedStatement pst = connection.prepareStatement(query);
        pst.executeUpdate();
    }

    /**
     * Validate provided credentials against records stored in the database.
     * @param username username to validate
     * @param password password to validate
     * @return True if the username and password are valid for a given user, else false
     */
    public boolean validateLogin(String username, String password) throws SQLException {
        String query = "SELECT userName, password FROM User WHERE userName = ? AND password = ?";
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

    /**
     * Update the user's profile picture
     * @param filename filename of the picture
     * @param user user to update
     */
    public void updateProfilePicture(String filename, User user) throws SQLException{
        String query = "UPDATE User SET profilePicture = ? WHERE userName = ?";
        PreparedStatement pst = connection.prepareStatement(query);
        pst.setString(1, filename);
        pst.setString(2, user.getUserName());
        pst.executeUpdate();
    }

    /**
     * Increments the provided user's games played and games won.
     * @param username username of the user to update
     */
    public void updateUserWins(String username) throws SQLException{
        String query = """
                        UPDATE User
                        SET gamesPlayed = gamesPlayed + 1, gamesWon = gamesWon + 1
                        WHERE userName = ?;
                        """;
        PreparedStatement pst = connection.prepareStatement(query);
        pst.setString(1, username);
        pst.executeUpdate();

    }
    
    /**
     * Increments the provided user's games played only.
     * @param username username of the user to update
     */
    public void updateUserGames(String username) throws SQLException{
        String query = """
                        UPDATE User
                        SET gamesPlayed = gamesPlayed + 1, gamesWon = gamesWon + 1
                        WHERE userName = ?;
                        """;
        PreparedStatement pst = connection.prepareStatement(query);
        pst.setString(1, username);
        pst.executeUpdate();

    }

    /**
     * Check if a username is already taken
     * @param username username to check
     * @return True if the name exists in the database, else false
     */
    public boolean userExistsInDatabase(String username) throws SQLException {
        String query = "SELECT userName FROM User WHERE userName = ?";
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

    @Deprecated
    public void removeDuplicate() throws SQLException{
        String query = """
                DELETE FROM Leaderboard\s
                WHERE PlayerRank NOT IN\s
                   (SELECT MIN(PlayerRank)\s
                    FROM leaderBoard\s
                    GROUP BY userName, winPc)
                ORDER BY winPc DESC;
                """;
        PreparedStatement pst = connection.prepareStatement(query);
        pst.executeUpdate();
    }

    /**
     * Retrieves a user from the database.
     * @param username username of the user
     * @return User instance
     */
    public User getUserByUsername(String username) throws SQLException {
        String query = """
                SELECT id, firstName, lastName, gamesPlayed, gamesWon, userName, loginDate, profilePicture
                FROM User
                WHERE username = ?;
                """;
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
        String query = "SELECT id, firstName, lastName, gamesPlayed, gamesWon, userName, loginDate, winPc FROM user";
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
        String query = "SELECT PlayerRank, userName, winPc FROM leaderBoard ORDER BY winPc DESC";
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
