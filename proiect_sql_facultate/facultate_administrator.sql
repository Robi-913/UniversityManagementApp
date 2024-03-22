CREATE DATABASE  IF NOT EXISTS `facultate`;
USE `facultate`;


CREATE TABLE `administrator` (
  `id` int NOT NULL AUTO_INCREMENT,
  `CNP` varchar(45) DEFAULT NULL,
  `nume` varchar(45) DEFAULT NULL,
  `prenume` varchar(45) DEFAULT NULL,
  `adresa` varchar(45) DEFAULT NULL,
  `numarDeTelefon` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `IBAN` varchar(45) DEFAULT NULL,
  `superadministrator_bool` bit(1) DEFAULT NULL,
  `nrContract` int DEFAULT NULL,
  PRIMARY KEY (`id`)
);

INSERT INTO `administrator` (`CNP`, `nume`, `prenume`, `adresa`, `numarDeTelefon`, `email`, `IBAN`, `superadministrator_bool`, `nrContract`)
VALUES
 ('1234567890123', 'Popescu', 'Ion', 'Str. Primaverii, nr. 10', '0712345678', 'popescu.ion@example.com', 'RO012345678901234567890', 1, 101),
  ('2345678901234', 'Ionescu', 'Ana', 'Str. Libertatii, nr. 25', '0723456789', 'ionescu.ana@example.com', 'RO123456789012345678901', 0, 102),
  ('3456789012345', 'Vasilescu', 'Mihai', 'Str. Reconstructiei, nr. 5', '0734567890', 'vasilescu.mihai@example.com', 'RO234567890123456789012', 0, 103),
  ('4567890123456', 'Dumitrescu', 'Elena', 'Str. Universitatii, nr. 15', '0745678901', 'dumitrescu.elena@example.com', 'RO345678901234567890123', 0, 104),
  ('5678901234567', 'Constantinescu', 'George', 'Str. Independerii, nr. 7', '0756789012', 'constantinescu.george@example.com', 'RO456789012345678901234', 0, 105)

