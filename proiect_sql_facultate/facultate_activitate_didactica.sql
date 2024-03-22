CREATE DATABASE IF NOT EXISTS `facultate`;
USE `facultate`;

CREATE TABLE `activitate_didactica` (
  `id` int NOT NULL AUTO_INCREMENT,
  `idProfesor` int DEFAULT NULL,
  `idMaterie` int DEFAULT NULL,
  `Curs` bit(1) DEFAULT NULL,
  `Seminar` bit(1) DEFAULT NULL,
  `Laborator` bit(1) DEFAULT NULL,
  `Examen` bit(1) DEFAULT NULL,
  `Colocviu` bit(1) DEFAULT NULL,
  `DataInceput` datetime DEFAULT NULL,
  `DataFinal` datetime DEFAULT NULL,
  `NrMaximParticipanti` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_activitate_didactica_idx` (`idMaterie`),
  KEY `fk_activitate_didactica2_idx` (`idProfesor`),
  CONSTRAINT `fk_activitate_didactica_materie` FOREIGN KEY (`idMaterie`) REFERENCES `materie` (`id`),
  CONSTRAINT `fk_activitate_didactica_profesor` FOREIGN KEY (`idProfesor`) REFERENCES `profesor` (`id`)
);

-- Populate activitate_didactica for Fizica
INSERT INTO `activitate_didactica` (`idProfesor`, `idMaterie`, `Curs`, `Seminar`, `Laborator`, `Examen`, `Colocviu`, `DataInceput`, `DataFinal`, `NrMaximParticipanti`)
VALUES

  (1, 1, 1, 1, 1, 1, 0, '2024-02-01 10:00:00', '2024-05-30 12:00:00', 50),
  (2, 1, 1, 1, 1, 1, 0, '2024-02-01 14:00:00', '2024-05-30 16:00:00', 50),
  (3, 1, 1, 1, 1, 1, 0, '2024-02-02 10:00:00', '2024-05-31 12:00:00', 50),

-- Populate activitate_didactica for Matematici Speciale
  (4, 2, 1, 1, 1, 1, 0, '2024-02-01 08:00:00', '2024-05-30 10:00:00', 60),
  (5, 2, 1, 1, 1, 1, 0, '2024-02-01 12:00:00', '2024-05-30 14:00:00', 60),
  (6, 2, 1, 1, 1, 1, 0, '2024-02-02 08:00:00', '2024-05-31 10:00:00', 60),

-- Populate activitate_didactica for Baza de Date
  (7, 3, 1, 1, 1, 1, 0, '2024-02-03 10:00:00', '2024-06-01 12:00:00', 40),
  (8, 3, 1, 1, 1, 1, 0, '2024-02-03 14:00:00', '2024-06-01 16:00:00', 40),
  (9, 3, 1, 1, 1, 1, 0, '2024-02-04 10:00:00', '2024-06-02 12:00:00', 40),

-- Populate activitate_didactica for Proiectare Orientata pe Obiect
  (10, 4, 1, 1, 1, 1, 0, '2024-02-05 08:00:00', '2024-06-03 10:00:00', 45),
  (11, 4, 1, 1, 1, 1, 0, '2024-02-05 12:00:00', '2024-06-03 14:00:00', 45),
  (12, 4, 1, 1, 1, 1, 0, '2024-02-06 08:00:00', '2024-06-04 10:00:00', 45),

-- Populate activitate_didactica for Proiectare in Limbaj de Asamblare
  (13, 5, 1, 1, 1, 1, 0, '2024-02-07 10:00:00', '2024-06-05 12:00:00', 40),
  (14, 5, 1, 1, 1, 1, 0, '2024-02-07 14:00:00', '2024-06-05 16:00:00', 40),
  (15, 5, 1, 1, 1, 1, 0, '2024-02-08 10:00:00', '2024-06-06 12:00:00', 40),

-- Populate activitate_didactica for Analiza Matematica
  (16, 6, 1, 1, 1, 1, 0, '2024-02-09 08:00:00', '2024-06-07 10:00:00', 55),
  (17, 6, 1, 1, 1, 1, 0, '2024-02-09 12:00:00', '2024-06-07 14:00:00', 55),
  (18, 6, 1, 1, 1, 1, 0, '2024-02-10 08:00:00', '2024-06-08 10:00:00', 55),

-- Populate activitate_didactica for Sisteme de Operare
  (19, 7, 1, 1, 1, 1, 0, '2024-02-11 10:00:00', '2024-06-09 12:00:00', 50),
  (20, 7, 1, 1, 1, 1, 0, '2024-02-11 14:00:00', '2024-06-09 16:00:00', 50),
  (21, 7, 1, 1, 1, 1, 0, '2024-02-12 10:00:00', '2024-06-10 12:00:00', 50),

-- Populate activitate_didactica for Electrotehnica
  (22, 8, 1, 1, 1, 1, 0, '2024-02-13 08:00:00', '2024-06-11 10:00:00', 40),
  (23, 8, 1, 1, 1, 1, 0, '2024-02-13 12:00:00', '2024-06-11 14:00:00', 40),
  (24, 8, 1, 1, 1, 1, 0, '2024-02-14 08:00:00', '2024-06-12 10:00:00', 40),

-- Populate activitate_didactica for Algoritmi Fundamentali
  (25, 9, 1, 1, 1, 1, 0, '2024-02-15 10:00:00', '2024-06-13 12:00:00', 55),
  (26, 9, 1, 1, 1, 1, 0, '2024-02-15 14:00:00', '2024-06-13 16:00:00', 55),
  (27, 9, 1, 1, 1, 1, 0, '2024-02-16 10:00:00', '2024-06-14 12:00:00', 55),

-- Populate activitate_didactica for Proiectare Logica
  (28, 10, 1, 1, 1, 1, 0, '2024-02-17 08:00:00', '2024-06-15 10:00:00', 50),
  (29, 10, 1, 1, 1, 1, 0, '2024-02-17 12:00:00', '2024-06-15 14:00:00', 50),
  (30, 10, 1, 1, 1, 1, 0, '2024-02-18 08:00:00', '2024-06-16 10:00:00', 50),

-- Populate activitate_didactica for Proiectarea Sistemelor Numerice si Date Reale
  (31, 11, 1, 1, 1, 1, 0, '2024-02-19 10:00:00', '2024-06-17 12:00:00', 45),
  (32, 11, 1, 1, 1, 1, 0, '2024-02-19 14:00:00', '2024-06-17 16:00:00', 45),
  (33, 11, 1, 1, 1, 1, 0, '2024-02-20 10:00:00', '2024-06-18 12:00:00', 45);

