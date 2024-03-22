CREATE DATABASE  IF NOT EXISTS `facultate`;
USE `facultate`;

CREATE TABLE `chat` (
  `id` int NOT NULL AUTO_INCREMENT,
  `text` varchar(100) DEFAULT NULL,
  `idGrup` int DEFAULT NULL,
  `idStudent` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_chat_idx` (`idStudent`),
  KEY `fk_chat2_idx` (`idGrup`),
  CONSTRAINT `fk_chat` FOREIGN KEY (`idStudent`) REFERENCES `student` (`id`),
  CONSTRAINT `fk_chat2` FOREIGN KEY (`idGrup`) REFERENCES `grup_studenti` (`id`)
);

USE `facultate`;


INSERT INTO `chat` (`text`, `idStudent`, `idGrup`)
VALUES
  ('Salut! Cum merge?', 1, 1),
  ('Bine, mulțumesc! Tu?', 2, 1),
  ('Am nevoie de ajutor la problema 3, cineva poate să mă ajute?', 3, 2),
  ('Sigur, te pot ajuta eu. Care e problema?', 4, 2),
  ('Ați văzut tema nouă la Baze de Date?', 5, 3),
  ('Da, este destul de dificilă. Să ne ajutăm reciproc!', 6, 3),
  ('Cine participă la evenimentul de mâine?', 7, 4),
  ('Eu voi fi prezent!', 8, 4),
  ('Am trimis notițele pentru Analiza Matematica în grupul nostru.', 9, 5),
  ('Mulțumesc! Le voi verifica imediat.', 10, 5),
   ('Cine este interesat să lucreze la proiectul de Java împreună?', 13, 7),
  ('Eu sunt interesat! Când ne întâlnim?', 14, 7),
  ('Cum a fost examenul de Sisteme de Operare?', 15, 8),
  ('A fost destul de dificil, dar sper să trecem cu bine cu toții.', 16, 8),
  ('Am creat un grup pentru studiul algoritmilor. Cine dorește să se alăture?', 17, 9),
  ('Count me in! Mulțumesc pentru inițiativă.', 18, 9),
  ('Ați văzut anunțul privind laboratorul de Proiectare Orientată pe Obiect?', 19, 10),
  ('Da, trebuie să ne pregătim bine pentru el!', 20, 10);

