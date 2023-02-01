DROP DATABASE IF EXISTS `warcaby`;
CREATE DATABASE `warcaby`;
USE `warcaby`;
CREATE USER 'warcaby_serwer'@'localhost' IDENTIFIED BY 'warcaby';
CREATE TABLE `game` (
    id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    gameVersion INT NOT NULL,
    startDate DATETIME,
    movesCount INT NOT NULL DEFAULT 0
);
CREATE TABLE `move` (
    game_id INT NOT NULL,
    ordinal INT NOT NULL,
    source VARCHAR(4) NOT NULL,
    destination VARCHAR(4) NOT NULL
);
DELIMITER $$
CREATE OR REPLACE PROCEDURE new_game()
BEGIN
    INSERT INTO game(startDate) VALUES (NOW());
END$$
DELIMITER ;