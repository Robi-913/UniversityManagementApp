CREATE DATABASE  IF NOT EXISTS `facultate`;
USE `facultate`;

CREATE TABLE `grup_studenti` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nume` varchar(45) DEFAULT NULL,
  `id_materie` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_grup_materie_idx` (`id_materie`),
  CONSTRAINT `fk_grup_materie` FOREIGN KEY (`id_materie`) REFERENCES `materie` (`id`)
);

INSERT INTO `grup_studenti` (`nume`, `id_materie`) VALUES
('Grupa 1', 1),
('Grupa 2', 2),
('Grupa 3', 3),
('Grupa 4', 4),
('Grupa 5', 5),
('Grupa 6', 6),
('Grupa 7', 7),
('Grupa 8', 8),
('Grupa 9', 9),
('Grupa 10', 10),
('Grupa 11', 11);