CREATE DATABASE  IF NOT EXISTS `facultate`;
USE `facultate`;

CREATE TABLE `catalog` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_student` int DEFAULT NULL,
  `id_materie` int DEFAULT NULL,
  `notaCurs` int DEFAULT NULL,
  `notaSeminar` int DEFAULT NULL,
  `notaLaborator` int DEFAULT NULL,
  `notaFinala` float(3,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_catalog_materie_idx` (`id_materie`),
  KEY `fk_catlog_student_idx` (`id_student`),
  CONSTRAINT `fk_catalog_materie` FOREIGN KEY (`id_materie`) REFERENCES `materie` (`id`),
  CONSTRAINT `fk_catlog_student` FOREIGN KEY (`id_student`) REFERENCES `student` (`id`)
);

INSERT INTO `catalog` (`id_student`, `id_materie`, `notaCurs`, `notaSeminar`, `notaLaborator`, `notaFinala`)
VALUES

-- Fizica
(1, 1, 8, 9, 10, 9.0),
(2, 1, 7, 8, 9, 8.33),
(3, 1, 9, 10, 8, 8.67),
(4, 1, 8, 9, 10, 9.0),
(5, 1, 7, 8, 9, 8.33),



-- Matematici Speciale

(5, 2, 9, 8, 10, 9.0),
(6, 2, 8, 7, 9, 8.0),
(7, 2, 10, 9, 8, 9.0),
(8, 2, 9, 8, 10, 9.0),
(9, 2, 8, 7, 9, 8.0),


-- Baza de Date

(10, 3, 8, 9, 10, 9.0),
(11, 3, 7, 8, 9, 8.33),
(12, 3, 9, 10, 8, 8.67),
(13, 3, 8, 9, 10, 9.0),
(14, 3, 7, 8, 9, 8.33),

-- Proiectare Orientata pe Obiect

(15, 4, 8, 9, 10, 9.0),
(16, 4, 7, 8, 9, 8.33),
(17, 4, 9, 10, 8, 8.67),
(18, 4, 8, 9, 10, 9.0),
(19, 4, 7, 8, 9, 8.33),


-- Proiectare in Limbaj de Asamblare

(20, 5, 8, 9, 10, 9.0),
(21, 5, 7, 8, 9, 8.33),
(22, 5, 9, 10, 8, 8.67),
(23, 5, 8, 9, 10, 9.0),
(24, 5, 7, 8, 9, 8.33),


-- Analiza Matematica

(25, 6, 8, 9, 10, 9.0),
(26, 6, 7, 8, 9, 8.33),
(27, 6, 9, 10, 8, 8.67),
(28, 6, 8, 9, 10, 9.0),
(29, 6, 7, 8, 9, 8.33),


-- Sisteme de Operare

(30, 7, 8, 9, 10, 9.0),
(31, 7, 7, 8, 9, 8.33),
(32, 7, 9, 10, 8, 8.67),
(33, 7, 8, 9, 10, 9.0),
(34, 7, 7, 8, 9, 8.33),-- ... repeat for other students

-- Electrotehnica

(35, 8, 8, 9, 10, 9.0),
(36, 8, 7, 8, 9, 8.33),
(37, 8, 9, 10, 8, 8.67),
(38, 8, 8, 9, 10, 9.0),
(39, 8, 7, 8, 9, 8.33),


-- Algoritmi Fundamentali

(40, 9, 8, 9, 10, 9.0),
(5, 9, 7, 8, 9, 8.33),
(32, 9, 9, 10, 8, 8.67),
(2, 9, 8, 9, 10, 9.0),
(15, 9, 7, 8, 9, 8.33),


-- Proiectare Logica

(13, 10, 8, 9, 10, 9.0),
(21, 10, 7, 8, 9, 8.33),
(35, 10, 9, 10, 8, 8.67),
(38, 10, 8, 9, 10, 9.0),
(14, 10, 7, 8, 9, 8.33),


-- Proiectarea Sistemelor Numerice si Date Reale

(12, 11, 8, 9, 10, 9.0),
(26, 11, 7, 8, 9, 8.33),
(19, 11, 9, 10, 8, 8.67),
(21, 11, 8, 9, 10, 9.0),
(22, 11, 7, 8, 9, 8.33);


