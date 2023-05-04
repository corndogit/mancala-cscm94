create database if not exists Mancala;
use Mancala;

create table User(
    id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    firstName char(255) NOT NULL,
    lastName char(255) NOT NULL,
    userName varchar(255) NOT NULL,
    password varchar(255) NOT NULL,
    profilePicture varchar(255) DEFAULT ('default.png'),
    loginDate date DEFAULT (CURRENT_DATE),
    gamesPlayed int DEFAULT 0,
    gamesWon int DEFAULT 0
);
