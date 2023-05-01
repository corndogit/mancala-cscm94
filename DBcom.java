import java.sql.*;
import java.util.ArrayList;
import java.time.LocalDate;
public class DBcom {
    public static void createUser(User user) throws SQLException{
        Connection connection = Database.dbConnection();
        String query = Query.insert;
        PreparedStatement pst = connection.prepareStatement(query);
        pst.setString(1,user.getFirstName());
        pst.setString(2,user.getLastName());
        pst.setInt(3,user.getNoOfGamesPlayed());
        pst.setInt(4,user.getNoOfGamesWon());
        pst.setString(5,user.getUserName());
        pst.setString(6,user.getPassword());
        LocalDate loginDate = user.getLoginDate();
        pst.setDate(7,java.sql.Date.valueOf(loginDate));
        pst.setFloat(8,user.getWinPc());

        System.out.println("\n ps = "+pst+"\n"+user);
        pst.executeUpdate();
        pst.close();
    }
    public static void createLeaderBoard() throws SQLException{
        Connection connection = Database.dbConnection();
        String query = Query.leaderBoard1;
        PreparedStatement pst = connection.prepareStatement(query);
        pst.executeUpdate();
    }
    public static void removeDuplicate() throws SQLException{
        Connection connection = Database.dbConnection();
        String query = Query.removeDuplicate;
        PreparedStatement pst = connection.prepareStatement(query);
        pst.executeUpdate();
    }
    public static ArrayList<User> displayUser() throws SQLException {
        ArrayList<User> list = new ArrayList<User>();
        Connection connection = Database.dbConnection();
        String query = Query.read;
        Statement st = connection.createStatement();
        System.out.println("\n st = "+st);
        ResultSet resultSet = st.executeQuery(query);
        while(resultSet.next()){
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            int gamesPlayed = resultSet.getInt("gamesPlayed");
            int gamesWon = resultSet.getInt("gamesWon");
            String username = resultSet.getString("userName");
            User user1 = new User();
            LocalDate loginDate = user1.getLoginDate();
            loginDate = Date.valueOf(loginDate).toLocalDate();
            User user = new User(firstName,lastName,gamesPlayed,gamesWon,username,loginDate);
            list.add(user);
        }
        st.close();
        return list;
    }
    public static void displayLeaderBoard() throws SQLException {
        Connection connection = Database.dbConnection();
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
