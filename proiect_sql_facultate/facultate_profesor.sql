CREATE DATABASE  IF NOT EXISTS facultate;
USE facultate;

CREATE TABLE profesor (
  id int NOT NULL AUTO_INCREMENT,
  CNP varchar(100) DEFAULT NULL,
  nume varchar(100) DEFAULT NULL,
  prenume varchar(100) DEFAULT NULL,
  adresa varchar(100) DEFAULT NULL,
  nrDeTelefon varchar(100) DEFAULT NULL,
  email varchar(100) DEFAULT NULL,
  IBAN varchar(100) DEFAULT NULL,
  nrContract int DEFAULT NULL,
  OreMin int DEFAULT NULL,
  OreMax int DEFAULT NULL,
  Departament varchar(100) DEFAULT NULL,
  PRIMARY KEY (id)
);

-- Inserting additional records
INSERT INTO profesor (CNP, nume, prenume, adresa, nrDeTelefon, email, IBAN, nrContract, OreMin, OreMax, Departament) VALUES
-- Matematici Speciale
('5678901234567', 'Gheorghe', 'Elena', 'Bulevardul Carol I, nr. 12', '0756789012', 'elena.gheorghe@example.com', 'RO90QRST5678901234567890', 205, 18, 28, 'Matematici Speciale'),
('9012345678901', 'Iancu', 'Roxana', 'Aleea Trandafirilor, nr. 7', '0790123456', 'roxana.iancu@example.com', 'RO78GHIJ9012345678901234', 209, 12, 22, 'Matematici Speciale'),
('2345678901234', 'Dumitrache', 'Mariana', 'Aleea Frunzelor, nr. 9', '0823456789', 'mariana.dumitrache@example.com', 'RO34STUV2345678901234567', 212, 15, 25, 'Matematici Speciale'),

-- Baza de Date
('6789012345678', 'Stanciu', 'Gabriel', 'Aleea Magnoliei, nr. 8', '0767890123', 'gabriel.stanciu@example.com', 'RO12UVWX6789012345678901', 206, 10, 20, 'Baza de Date'),
('0123456789012', 'Stoian', 'Silviu', 'Strada Mihai Eminescu, nr. 10', '0801234567', 'silviu.stoian@example.com', 'RO90KLMN0123456789012345', 210, 18, 28, 'Baza de Date'),
('5678901234567', 'Munteanu', 'Vlad', 'Strada Plevnei, nr. 27', '0856789012', 'vlad.munteanu@example.com', 'RO90EFGH5678901234567890', 215, 18, 28, 'Baza de Date'),

-- Proiectare Orientata pe Obiect
('7890123456789', 'Popescu', 'Iulia', 'Strada Ion Creanga, nr. 18', '0778901234', 'iulia.popescu@example.com', 'RO34YZAB7890123456789012', 207, 15, 25, 'Proiectare Orientata pe Obiect'),
('4567890123456', 'Bogdan', 'Raluca', 'Bulevardul Decebal, nr. 14', '0845678901', 'raluca.bogdan@example.com', 'RO78ABCD4567890123456789', 214, 12, 22, 'Proiectare Orientata pe Obiect'),
('9012345678901', 'Iancu', 'Roxana', 'Aleea Trandafirilor, nr. 7', '0790123456', 'roxana.iancu@example.com', 'RO78GHIJ9012345678901234', 209, 12, 22, 'Proiectare Orientata pe Obiect'),

-- Proiectare in Limbaj de Asamblare
('6789012345678', 'Serban', 'Ana-Maria', 'Aleea Salcamilor, nr. 11', '0867890123', 'ana-maria.serban@example.com', 'RO12IJKL6789012345678901', 216, 10, 20, 'Proiectare in Limbaj de Asamblare'),
('0123456789012', 'Stoian', 'Silviu', 'Strada Mihai Eminescu, nr. 10', '0801234567', 'silviu.stoian@example.com', 'RO90KLMN0123456789012345', 210, 18, 28, 'Proiectare in Limbaj de Asamblare'),
('7890123456789', 'Popescu', 'Iulia', 'Strada Ion Creanga, nr. 18', '0778901234', 'iulia.popescu@example.com', 'RO34YZAB7890123456789012', 207, 15, 25, 'Proiectare in Limbaj de Asamblare'),

-- Analiza Matematica
('0123456789012', 'Stoian', 'Silviu', 'Strada Mihai Eminescu, nr. 10', '0801234567', 'silviu.stoian@example.com', 'RO90KLMN0123456789012345', 210, 18, 28, 'Analiza Matematica'),
('1234567890123', 'Barbulescu', 'Gabriela', 'Bulevardul Magheru, nr. 22', '0812345678', 'gabriela.barbulescu@example.com', 'RO12OPQR1234567890123456', 211, 10, 20, 'Analiza Matematica'),
('5678901234567', 'Munteanu', 'Vlad', 'Strada Plevnei, nr. 27', '0856789012', 'vlad.munteanu@example.com', 'RO90EFGH5678901234567890', 215, 18, 28, 'Analiza Matematica'),

-- Sisteme de Operare
('3456789012345', 'Neacsu', 'Andrei', 'Strada Bucuresti, nr. 17', '0834567890', 'andrei.neacsu@example.com', 'RO56WXYZ3456789012345678', 213, 20, 30, 'Sisteme de Operare'),
('2345678901234', 'Dumitrache', 'Mariana', 'Aleea Frunzelor, nr. 9', '0823456789', 'mariana.dumitrache@example.com', 'RO34STUV2345678901234567', 212, 15, 25, 'Sisteme de Operare'),
('7890123456789', 'Popescu', 'Iulia', 'Strada Ion Creanga, nr. 18', '0778901234', 'iulia.popescu@example.com', 'RO34YZAB7890123456789012', 207, 15, 25, 'Sisteme de Operare'),

-- Electrotehnica
('4567890123456', 'Bogdan', 'Raluca', 'Bulevardul Decebal, nr. 14', '0845678901', 'raluca.bogdan@example.com', 'RO78ABCD4567890123456789', 214, 12, 22, 'Electrotehnica'),
('5678901234567', 'Munteanu', 'Vlad', 'Strada Plevnei, nr. 27', '0856789012', 'vlad.munteanu@example.com', 'RO90EFGH5678901234567890', 215, 18, 28, 'Electrotehnica'),
('8901234567890', 'Radulescu', 'Andrei', 'Strada Romana, nr. 16', '0889012345', 'andrei.radulescu@example.com', 'RO56QRST8901234567890123', 218, 20, 30, 'Electrotehnica'),

-- Algoritmi Fundamentali
('5678901234567', 'Munteanu', 'Vlad', 'Strada Plevnei, nr. 27', '0856789012', 'vlad.munteanu@example.com', 'RO90EFGH5678901234567890', 215, 18, 28, 'Algoritmi Fundamentali'),
('6789012345678', 'Serban', 'Ana-Maria', 'Aleea Salcamilor, nr. 11', '0867890123', 'ana-maria.serban@example.com', 'RO12IJKL6789012345678901', 216, 10, 20, 'Algoritmi Fundamentali'),
('4567890123456', 'Bogdan', 'Raluca', 'Bulevardul Decebal, nr. 14', '0845678901', 'raluca.bogdan@example.com', 'RO78ABCD4567890123456789', 214, 12, 22, 'Algoritmi Fundamentali'),

-- Proiectare Logica
('6789012345678', 'Serban', 'Ana-Maria', 'Aleea Salcamilor, nr. 11', '0867890123', 'ana-maria.serban@example.com', 'RO12IJKL6789012345678901', 216, 10, 20, 'Proiectare Logica'),
('8901234567890', 'Radulescu', 'Andrei', 'Strada Romana, nr. 16', '0889012345', 'andrei.radulescu@example.com', 'RO56QRST8901234567890123', 218, 20, 30, 'Proiectare Logica'),
('7890123456789', 'Popescu', 'Iulia', 'Strada Ion Creanga, nr. 18', '0778901234', 'iulia.popescu@example.com', 'RO34YZAB7890123456789012', 207, 15, 25, 'Proiectare Logica'),

-- Fizica
('8901234567890', 'Florea', 'Daniel', 'Bulevardul Timisoara, nr. 20', '0789012345', 'daniel.florea@example.com', 'RO56CDEF8901234567890123', '208', '20', '30', 'Fizica'),
('3456789012345', 'Neacsu', 'Andrei', 'Strada Bucuresti, nr. 17', '0834567890', 'andrei.neacsu@example.com', 'RO56WXYZ3456789012345678', '213', '20', '30', 'Fizica'),
('8901234567890', 'Radulescu', 'Andrei', 'Strada Romana, nr. 16', '0889012345', 'andrei.radulescu@example.com', 'RO56QRST8901234567890123', '218', '20', '30', 'Fizica'),

-- Proiectarea Sistemelor Numerice
('8901234567890', 'Radulescu', 'Andrei', 'Strada Romana, nr. 16', '0889012345', 'andrei.radulescu@example.com', 'RO56QRST8901234567890123', 218, 20, 30, 'Proiectarea Sistemelor Numerice'),
('0123456789012', 'Moldovan', 'Adrian', 'Bulevardul Basarabia, nr. 28', '0901234567', 'adrian.moldovan@example.com', 'RO90YZAB0123456789012345', 220, 18, 28, 'Proiectarea Sistemelor Numerice'),
('8901234567890', 'Radulescu', 'Andrei', 'Strada Romana, nr. 16', '0889012345', 'andrei.radulescu@example.com', 'RO56QRST8901234567890123', 218, 20, 30, 'Proiectarea Sistemelor Numerice');