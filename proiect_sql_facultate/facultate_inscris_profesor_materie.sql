CREATE DATABASE  IF NOT EXISTS `facultate`;
USE `facultate`;

CREATE TABLE `inscris_profesor_materie` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_profesor` int DEFAULT NULL,
  `id_materie` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_inscris_profesor_materie_idx` (`id_profesor`),
  KEY `fk_inscris_profesor_materie2_idx` (`id_materie`),
  CONSTRAINT `fk_inscris_profesor_materie` FOREIGN KEY (`id_profesor`) REFERENCES `profesor` (`id`),
  CONSTRAINT `fk_inscris_profesor_materie2` FOREIGN KEY (`id_materie`) REFERENCES `materie` (`id`)
);
