create database User;
use User;
create table user(
    id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    firstName char(255),
    lastName char(255),
    gamesPlayed int,
    gamesWon int,
    userName varchar(255),
    password varchar(255),
    loginDate date,
    winPc float
);
create table leaderBoard(
    PlayerRank INT,
    userName varchar(255),
    winPc float
);
