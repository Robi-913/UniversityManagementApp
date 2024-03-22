CREATE DATABASE  IF NOT EXISTS `facultate`;
USE `facultate`;

CREATE TABLE `inscris_activitate_didactica` (
  `id` int NOT NULL AUTO_INCREMENT,
  `idActivitateDidactica` int DEFAULT NULL,
  `idStudent` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_inscris_activitate_didactica_idx` (`idActivitateDidactica`),
  KEY `fk_inscris_activitate_didactica2_idx` (`idStudent`),
  CONSTRAINT `fk_inscris_activitate_didactica` FOREIGN KEY (`idActivitateDidactica`) REFERENCES `activitate_didactica` (`id`),
  CONSTRAINT `fk_inscris_activitate_didactica2` FOREIGN KEY (`idStudent`) REFERENCES `student` (`id`)
);


INSERT INTO `inscris_activitate_didactica` (`idActivitateDidactica`, `idStudent`)
VALUES
  (1, 1),
  (2, 2),
  (3, 3),
  (4, 4),
  (5, 5),
  (6, 6),
  (7, 7),
  (8, 8),
  (9, 9),
  (10, 10),
  (11, 11),
  (12, 12),
  (13, 13),
  (14, 14),
  (15, 15),
  (16, 16),
  (17, 17),
  (18, 18),
  (19, 19),
  (20, 20),
  (21, 21),
  (22, 22),
  (23, 23),
  (24, 24),
  (25, 25),
  (26, 26),
  (27, 27),
  (28, 28),
  (29, 29),
  (30, 30),
  (31, 31),
  (32, 32),
  (33, 33),
  (1, 34),
  (2, 35),
  (3, 36),
  (4, 37),
  (5, 38),
  (6, 39),
  (7, 40);
