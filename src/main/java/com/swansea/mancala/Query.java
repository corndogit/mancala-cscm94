package com.swansea.mancala;

public class Query {
    static String insert = """
            INSERT INTO user (firstName, lastName, gamesPlayed, gamesWon, userName,password, loginDate,winPc)
            VALUES(?,?,?,?,?,?,?,?)
            """;
    static String getUser = """
            SELECT id, firstName, lastName, gamesPlayed, gamesWon, userName, loginDate, winPc
            FROM user
            WHERE username = ?;
            """;
    static String displayLeaderboard = "SELECT PlayerRank, userName, winPc FROM leaderBoard ORDER BY winPc DESC";
    static String leaderBoard1 = """
            INSERT IGNORE INTO leaderBoard (PlayerRank, userName, winPc)
            SELECT ROW_NUMBER() OVER (ORDER BY winPc DESC), userName, winPc
            FROM user
            WHERE (userName, winPc) NOT IN (SELECT userName, winPc FROM leaderBoard)
            ORDER BY winPc DESC;
            """;
    static String removeDuplicate = """
            DELETE FROM leaderBoard\s
            WHERE PlayerRank NOT IN\s
               (SELECT MIN(PlayerRank)\s
                FROM leaderBoard\s
                GROUP BY userName, winPc)
            ORDER BY winPc DESC;
            """;
    static String validateLogin = "SELECT userName, password FROM User WHERE userName = ? AND password = ?";
    static String usernameExists = "SELECT userName FROM User WHERE userName = ?";
}
