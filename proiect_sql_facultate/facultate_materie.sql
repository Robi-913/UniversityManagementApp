CREATE DATABASE  IF NOT EXISTS `facultate`;
USE `facultate`;

CREATE TABLE `materie` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nume` varchar(45) DEFAULT NULL,
  `oreCurs` int DEFAULT NULL,
  `oreSeminar` int DEFAULT NULL,
  `oreLaborator` int DEFAULT NULL,
  `procentCurs` int DEFAULT NULL,
  `procentSeminar` int DEFAULT NULL,
  `procentLaborator` int DEFAULT NULL,
  `descriere` varchar(200) DEFAULT NULL,
  `nrMaxStudenti` int DEFAULT NULL,
  PRIMARY KEY (`id`)
);


INSERT INTO `materie` (`nume`, `oreCurs`, `oreSeminar`, `oreLaborator`, `procentCurs`, `procentSeminar`, `procentLaborator`, `descriere`, `nrMaxStudenti`)
VALUES
  ('Fizica', 30, 15, 15, 40, 30, 30, 'Curs de fizica generala', 50),
  ('Matematici Speciale', 40, 20, 20, 50, 30, 20, 'Curs de matematici speciale', 60),
  ('Baza de Date', 45, 20, 25, 50, 30, 20, 'Introducere in baze de date', 40),
  ('Proiectare Orientata pe Obiect', 35, 25, 20, 40, 30, 30, 'Principii OOP', 45),
  ('Proiectare in Limbaj de Asamblare', 30, 20, 25, 40, 30, 30, 'Asamblare si programare la nivel de hardware', 40),
  ('Analiza Matematica', 40, 30, 15, 50, 30, 20, 'Curs de analiza matematica', 55),
  ('Sisteme de Operare', 35, 25, 20, 40, 30, 30, 'Principii de functionare ale sistemelor de operare', 50),
  ('Electrotehnica', 30, 20, 25, 40, 30, 30, 'Curs de electrotehnica', 40),
  ('Algoritmi Fundamentali', 40, 30, 15, 50, 30, 20, 'Studiul algoritmilor de baza', 55),
  ('Proiectare Logica', 35, 25, 20, 40, 30, 30, 'Principii de proiectare logica', 50),
  ('Proiectarea Sistemelor Numerice si Date Reale', 40, 20, 25, 50, 30, 20, 'Proiectarea sistemelor numerice si lucrul cu date reale', 45);