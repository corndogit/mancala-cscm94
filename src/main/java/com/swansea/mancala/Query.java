package com.swansea.mancala;

public class Query {
    static String insert = "INSERT INTO user (firstName, lastName, gamesPlayed, gamesWon, userName,password, loginDate,winPc) VALUES(?,?,?,?,?,?,?,?)";
    static String read = "SELECT id, firstName, lastName, gamesPlayed, gamesWon, userName, loginDate, winPc FROM user;";
    static String displayLeaderboard = "SELECT PlayerRank, userName, winPc FROM leaderBoard ORDER BY winPc DESC";
    static String userTable = "create table user(id int NOT NULL AUTO_INCREMENT PRIMARY KEY,firstName char(255),lastName char(255),gamesPlayed int,gamesWon int,userName varchar(255), password varchar(255), loginDate date,winPc float);";
    static String leaderBoardTable = "create table leaderBoard(PlayerRank INT,userName varchar(255),winPc float);";
    static String leaderBoard1 = "INSERT IGNORE INTO leaderBoard (PlayerRank, userName, winPc)\n" +
            "SELECT ROW_NUMBER() OVER (ORDER BY winPc DESC), userName, winPc\n" +
            "FROM user\n" +
            "WHERE (userName, winPc) NOT IN (SELECT userName, winPc FROM leaderBoard)\n" +
            "ORDER BY winPc DESC;";
    static String removeDuplicate = "DELETE FROM leaderBoard \n" +
            "WHERE PlayerRank NOT IN \n" +
            "   (SELECT MIN(PlayerRank) \n" +
            "    FROM leaderBoard \n" +
            "    GROUP BY userName, winPc)\n" +
            "ORDER BY winPc DESC;\n";
}
