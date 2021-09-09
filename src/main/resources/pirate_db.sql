DROP DATABASE  IF EXISTS pirate_db;
CREATE DATABASE IF NOT EXISTS pirate_db;

USE pirate_db;

DROP TABLE  IF EXISTS pirate;
CREATE TABLE pirate(
id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT NOT NULL UNIQUE,
name VARCHAR(50),
strength  INT UNSIGNED  NOT NULL,
health  INT UNSIGNED  NOT NULL, 
/* is_alive BOOL; ( = health =? 0) */
drunk_level ENUM('sober','tipsy','brave','drunk','wasted'),
ship_id  INT UNSIGNED NOT NULL
);

INSERT INTO pirate (name, strength, health, drunk_level, ship_id) VALUES  (
	"John Sinx", 8, 6, 'sober', 1),
    ("Cucu Coppermaker", 7, 5, 'drunk', 1)
    ;

DROP TABLE  IF EXISTS captain;
CREATE TABLE captain(
id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT NOT NULL UNIQUE,
pirate_id INT UNSIGNED NOT NULL UNIQUE,
rum_owned INT UNSIGNED NOT NULL,
FOREIGN KEY (pirate_id) REFERENCES pirate(id)
);

INSERT INTO captain (rum_owned) VALUES  (
	15);

DROP TABLE  IF EXISTS ship;
CREATE TABLE ship(
id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT NOT NULL UNIQUE,
name VARCHAR(50),
graphic_id INT,
number_of_cannons INT UNSIGNED  NOT NULL,
ship_condition  INT UNSIGNED  NOT NULL
);


INSERT INTO Ship (name, graphic_id, number_of_cannons, ship_condition) VALUES  (
	"Ark of Joy", 1, 8, 100);

