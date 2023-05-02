create database Mancala;
use Mancala;
create table user(
    id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    firstName char(255) NOT NULL,
    lastName char(255) NOT NULL,
    gamesPlayed int DEFAULT 0,
    gamesWon int DEFAULT 0,
    userName varchar(255) NOT NULL,
    password varchar(255) NOT NULL,
    loginDate date DEFAULT (CURRENT_DATE),
    winPc float DEFAULT 0.0
);
create table leaderBoard(
    PlayerRank INT,
    userName varchar(255),
    winPc float
);
