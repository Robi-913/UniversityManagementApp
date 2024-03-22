CREATE DATABASE  IF NOT EXISTS `facultate`;
USE `facultate`;

CREATE TABLE `activitate_grup` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nume` varchar(45) DEFAULT NULL,
  `nrMin` int DEFAULT NULL,
  `nrMax` int DEFAULT NULL,
  `nrPersInscrise` int DEFAULT NULL,
  `id_grup` int DEFAULT NULL,
  `inceputActivitate` datetime DEFAULT NULL,
  `sfarsitActivitate` datetime DEFAULT NULL,
  `dataCreare` datetime DEFAULT NULL,
  `dataExpirare` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_activitate_grup_idx` (`id_grup`),
  CONSTRAINT `fk_activitate_grup` FOREIGN KEY (`id_grup`) REFERENCES `grup_studenti` (`id`)
);


USE `facultate`;

INSERT INTO `activitate_grup` (`nume`, `nrMin`, `nrMax`, `nrPersInscrise`, `id_grup`, `inceputActivitate`, `sfarsitActivitate`, `dataCreare`, `dataExpirare`)
VALUES
('Lab extra 1', 5, 10, 8, 1, '2024-01-07 10:00:00', '2024-01-07 12:00:00', '2024-01-06 08:00:00', '2024-01-06 20:00:00'),
('Seminar extra 1', 3, 8, 6, 2, '2024-01-08 14:00:00', '2024-01-08 16:00:00', '2024-01-07 12:00:00', '2024-01-08 10:00:00'),
('Course extra 1', 4, 12, 10, 3, '2024-01-09 09:00:00', '2024-01-09 11:00:00', '2024-01-08 15:00:00', '2024-01-09 08:00:00'),
('Tutoring 1', 2, 5, 4, 4, '2024-01-10 15:00:00', '2024-01-10 17:00:00', '2024-01-09 16:00:00', '2024-01-10 12:00:00'),
('Lab extra 2', 6, 15, 12, 5, '2024-01-11 10:00:00', '2024-01-11 12:00:00', '2024-01-10 08:00:00', '2024-01-10 20:00:00'),
('Seminar extra 2', 4, 10, 8, 6, '2024-01-12 14:00:00', '2024-01-12 16:00:00', '2024-01-11 12:00:00', '2024-01-12 10:00:00'),
('Course extra 2', 5, 14, 11, 7, '2024-01-13 09:00:00', '2024-01-13 11:00:00', '2024-01-12 15:00:00', '2024-01-13 08:00:00'),
('Tutoring 2', 3, 7, 5, 8, '2024-01-14 15:00:00', '2024-01-14 17:00:00', '2024-01-13 16:00:00', '2024-01-14 12:00:00'),
('Lab extra 3', 7, 18, 15, 9, '2024-01-15 10:00:00', '2024-01-15 12:00:00', '2024-01-14 08:00:00', '2024-01-14 20:00:00'),
('Seminar extra 3', 5, 12, 10, 10, '2024-01-16 14:00:00', '2024-01-16 16:00:00', '2024-01-15 12:00:00', '2024-01-15 10:00:00'),
('Course extra 3', 6, 15, 12, 1, '2024-01-17 09:30:00', '2024-01-17 11:30:00', '2024-01-16 08:30:00', '2024-01-16 20:30:00'),
('Lab extra 4', 4, 10, 8, 2, '2024-01-18 13:00:00', '2024-01-18 15:00:00', '2024-01-17 12:00:00', '2024-01-17 10:00:00'),
('Seminar extra 4', 5, 12, 10, 3, '2024-01-19 14:30:00', '2024-01-19 16:30:00', '2024-01-18 12:30:00', '2024-01-18 10:30:00'),
('Tutoring 3', 3, 8, 6, 4, '2024-01-20 16:00:00', '2024-01-20 18:00:00', '2024-01-19 15:00:00', '2024-01-20 14:00:00'),
('Course extra 4', 7, 18, 15, 5, '2024-01-21 11:30:00', '2024-01-21 13:30:00', '2024-01-20 09:30:00', '2024-01-20 21:30:00'),
('Lab extra 5', 5, 12, 10, 6, '2024-01-22 10:30:00', '2024-01-22 12:30:00', '2024-01-21 09:30:00', '2024-01-21 21:30:00'),
('Seminar extra 5', 8, 20, 18, 7, '2024-01-23 14:00:00', '2024-01-23 16:00:00', '2024-01-22 12:00:00', '2024-01-22 10:00:00'),
('Tutoring 5', 3, 8, 6, 4, '2024-01-20 16:00:00', '2024-01-20 18:00:00', '2024-01-19 15:00:00', '2024-01-20 14:00:00'),
('Course extra 5', 7, 18, 15, 5, '2024-01-21 11:30:00', '2024-01-21 13:30:00', '2024-01-20 09:30:00', '2024-01-20 21:30:00');
