CREATE DATABASE  IF NOT EXISTS `facultate`;
USE `facultate`;

CREATE TABLE `student` (
  `id` int NOT NULL AUTO_INCREMENT,
  `CNP` varchar(45) DEFAULT NULL,
  `nume` varchar(45) DEFAULT NULL,
  `prenume` varchar(45) DEFAULT NULL,
  `adresa` varchar(45) DEFAULT NULL,
  `nrDeTelefon` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `IBAN` varchar(45) DEFAULT NULL,
  `nrContract` int DEFAULT NULL,
  `anDeStudiu` int DEFAULT NULL,
  `nrDeOre` int DEFAULT NULL,
  PRIMARY KEY (`id`)
);

-- Inserting additional records
INSERT INTO `student` (`CNP`, `nume`, `prenume`, `adresa`, `nrDeTelefon`, `email`, `IBAN`, `nrContract`, `anDeStudiu`, `nrDeOre`) VALUES
('4567890123456', 'Georgescu', 'Maria', 'Strada Dorobantilor, nr. 5', '0745678901', 'maria.georgescu@example.com', 'RO78MNOP4567890123456789', 104, 2, 22),
('5678901234567', 'Constantin', 'Alexandru', 'Bulevardul Carol I, nr. 15', '0756789012', 'alexandru.constantin@example.com', 'RO90QRST5678901234567890', 105, 3, 28),
('6789012345678', 'Stancu', 'Elena', 'Aleea Magnoliei, nr. 8', '0767890123', 'elena.stancu@example.com', 'RO12UVWX6789012345678901', 106, 1, 18),
('7890123456789', 'Popa', 'Vasile', 'Strada Ion Creanga, nr. 12', '0778901234', 'vasile.popa@example.com', 'RO34YZAB7890123456789012', 107, 2, 23),
('8901234567890', 'Florescu', 'Andreea', 'Bulevardul Timisoara, nr. 20', '0789012345', 'andreea.florescu@example.com', 'RO56CDEF8901234567890123', 108, 3, 26),
('9012345678901', 'Gheorghe', 'Radu', 'Aleea Trandafirilor, nr. 7', '0790123456', 'radu.gheorghe@example.com', 'RO78GHIJ9012345678901234', 109, 1, 19),
('0123456789012', 'Stoica', 'Irina', 'Strada Mihai Eminescu, nr. 18', '0801234567', 'irina.stoica@example.com', 'RO90KLMN0123456789012345', 110, 2, 24),
('1234567890123', 'Barbu', 'George', 'Bulevardul Magheru, nr. 22', '0812345678', 'george.barbu@example.com', 'RO12OPQR1234567890123456', 111, 3, 31),
('2345678901234', 'Dobre', 'Camelia', 'Aleea Frunzelor, nr. 9', '0823456789', 'camelia.dobre@example.com', 'RO34STUV2345678901234567', 112, 1, 21),
('3456789012345', 'Neagu', 'Daniel', 'Strada Bucuresti, nr. 17', '0834567890', 'daniel.neagu@example.com', 'RO56WXYZ3456789012345678', 113, 2, 26),
('4567890123456', 'Bogdan', 'Raluca', 'Bulevardul Decebal, nr. 14', '0845678901', 'raluca.bogdan@example.com', 'RO78ABCD4567890123456789', 114, 3, 29),
('5678901234567', 'Munteanu', 'Vlad', 'Strada Plevnei, nr. 27', '0856789012', 'vlad.munteanu@example.com', 'RO90EFGH5678901234567890', 115, 1, 20),
('6789012345678', 'Serban', 'Ana-Maria', 'Aleea Salcamilor, nr. 11', '0867890123', 'ana-maria.serban@example.com', 'RO12IJKL6789012345678901', 116, 2, 25),
('7890123456789', 'Iancu', 'Silviu', 'Bulevardul Marasti, nr. 33', '0878901234', 'silviu.iancu@example.com', 'RO34MNOP7890123456789012', 117, 3, 30),
('8901234567890', 'Radulescu', 'Andrei', 'Strada Romana, nr. 16', '0889012345', 'andrei.radulescu@example.com', 'RO56QRST8901234567890123', 118, 1, 18),
('9012345678901', 'Nistor', 'Diana', 'Aleea Crinilor, nr. 19', '0890123456', 'diana.nistor@example.com', 'RO78UVWX9012345678901234', 119, 2, 22),
('0123456789012', 'Moldovan', 'Adrian', 'Bulevardul Basarabia, nr. 28', '0901234567', 'adrian.moldovan@example.com', 'RO90YZAB0123456789012345', 120, 3, 27),
('1234567860128', 'Ionescu', 'Ana', 'Bulevardul Unirii, nr. 30', '0912345678', 'ana.ionescu@example.com', 'RO12BCDE1234567890123456', 121, 1, 20),
('2345678901234', 'Dumitrescu', 'Marian', 'Strada Sibiu, nr. 14', '0923456789', 'marian.dumitrescu@example.com', 'RO34EFGH2345678901234567', 122, 2, 25),
('3456789012345', 'Georgescu', 'Ionut', 'Aleea Crizantemelor, nr. 8', '0934567890', 'ionut.georgescu@example.com', 'RO56IJKL3456789012345678', 123, 3, 28),
('2345678901234', 'Popescu', 'Ana', 'Bulevardul Victoriei, nr. 10', '0945678901', 'ana.popescu@example.com', 'RO78ABCD5678901234567890', 124, 1, 22),
('3456789012345', 'Ivanescu', 'Mihai', 'Strada Cluj, nr. 25', '0956789012', 'mihai.ivanescu@example.com', 'RO90EFGH6789012345678901', 125, 2, 26),
('4567890123456', 'Dinu', 'Elena', 'Aleea Brasov, nr. 18', '0967890123', 'elena.dinu@example.com', 'RO12IJKL7890123456789012', 126, 3, 30),
('5678901234567', 'Stanciu', 'Cristian', 'Bulevardul Iancu de Hunedoara, nr. 22', '0978901234', 'cristian.stanciu@example.com', 'RO34MNOP8901234567890123', 127, 1, 18),
('6789012345678', 'Vasilescu', 'Andreea', 'Strada Brasov, nr. 15', '0989012345', 'andreea.vasilescu@example.com', 'RO56QRST9012345678901234', 128, 2, 23),
('7890123456789', 'Ionescu', 'Gabriel', 'Aleea Constanta, nr. 8', '0990123456', 'gabriel.ionescu@example.com', 'RO78UVWX0123456789012345', 129, 3, 28),
('8901234567890', 'Barbulescu', 'Roxana', 'Bulevardul Aviatorilor, nr. 12', '1001234567', 'roxana.barbulescu@example.com', 'RO90YZAB1234567890123456', 130, 1, 19),
('9012345678901', 'Chirita', 'Doru', 'Strada Suceava, nr. 20', '1012345678', 'doru.chirita@example.com', 'RO12BCDE2345678901234567', 131, 2, 24),
('0123456789012', 'Iliescu', 'Oana', 'Bulevardul Mihai Viteazu, nr. 22', '1023456789', 'oana.iliescu@example.com', 'RO34EFGH3456789012345678', 132, 3, 31),
('1234567890123', 'Stanescu', 'Ion', 'Aleea Bucuresti, nr. 9', '1034567890', 'ion.stanescu@example.com', 'RO56IJKL4567890123456789', 133, 1, 21),
('2345678901234', 'Dumitru', 'Gabriela', 'Strada Arad, nr. 17', '1045678901', 'gabriela.dumitru@example.com', 'RO78ABCD5678901234567890', 134, 2, 26),
('3456789012345', 'Stoian', 'George', 'Bulevardul Unirii, nr. 14', '1056789012', 'george.stoian@example.com', 'RO90EFGH6789012345678901', 135, 3, 29),
('4567890123456', 'Radu', 'Elena', 'Aleea Sibiu, nr. 27', '1067890123', 'elena.radu@example.com', 'RO12IJKL7890123456789012', 136, 1, 20),
('5678901234567', 'Mazilu', 'Catalin', 'Strada Ploiesti, nr. 11', '1078901234', 'catalin.mazilu@example.com', 'RO34MNOP8901234567890123', 137, 2, 25),
('6789012345678', 'Constantinescu', 'Larisa', 'Bulevardul Decebal, nr. 33', '1089012345', 'larisa.constantinescu@example.com', 'RO56QRST9012345678901234', 138, 3, 30),
('7890123456789', 'Dinu', 'Marian', 'Strada Dorobanti, nr. 16', '1090123456', 'marian.dinu@example.com', 'RO78UVWX0123456789012345', 139, 1, 18),
('8901234567890', 'Stanculescu', 'Roxana', 'Aleea Buzau, nr. 19', '1101234567', 'roxana.stanculescu@example.com', 'RO90YZAB1234567890123456', 140, 2, 22),
('9012345678901', 'Cristea', 'Gabriel', 'Bulevardul Brasov, nr. 28', '1112345678', 'gabriel.cristea@example.com', 'RO12BCDE2345678901234567', 141, 3, 27),
('0123456789012', 'Voicu', 'Mihaela', 'Strada Pitesti, nr. 30', '1123456789', 'mihaela.voicu@example.com', 'RO34EFGH3456789012345678', 142, 1, 20),
('1234567890123', 'Georgescu', 'Catalin', 'Aleea Cluj, nr. 14', '1134567890', 'catalin.georgescu@example.com', 'RO56IJKL4567890123456789', 143, 2, 25);



