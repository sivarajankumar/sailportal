DROP DATABASE IF EXISTS sail_database;
CREATE DATABASE IF NOT EXISTS sail_database;

USE sail_database;

DELIMITER |
CREATE PROCEDURE identity()
BEGIN
SELECT @@identity;
END |
DELIMITER ;