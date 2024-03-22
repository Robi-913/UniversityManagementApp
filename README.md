# Sistem de Gestionare a Platformei de Studiu

## Despre Proiect
Acest proiect este conceput pentru a oferi o soluție eficientă pentru gestionarea unei platforme educaționale, folosind JavaFX pentru interfața grafică și MySQL pentru sistemul de baze de date. Este destinat să servească nevoile administratorilor, profesorilor și studenților, facilitând accesul și managementul informațiilor educaționale relevante.

## Funcționalități
- **Autentificare securizată** pentru diferite roluri de utilizatori: administratori, profesori și studenți.
- **Calendarul activităților**, cu opțiuni personalizabile de evenimente, disponibil pentru toți utilizatorii.
- **Gestionarea notelor** și catalogul, dedicate profesorilor.
- **Chat** în grupuri de studiu pentru o comunicare eficientă între studenți.
- **Gestionarea utilizatorilor** și atribuirea drepturilor specifice de către administratori, inclusiv funcționalități extinse pentru super administratori.

## Tehnologii Folosite
- **Java/JavaFX** pentru crearea interfeței grafice.
- **MySQL** pentru proiectarea și administrarea bazei de date.

## Instalare și Rulare
Pentru a rula acest sistem, trebuie să aveți Java și un server MySQL instalat pe sistemul dumneavoastră:
1. Clonați repository-ul pe sistemul local.
2. Importați proiectul în IDE-ul preferat (exemplu: IntelliJ IDEA, Eclipse).
3. Configurați conexiunea la baza de date MySQL în fișierul de configurare.
4. Rulați `Main.java` pentru a iniția aplicația.

## Manual de Utilizare
Utilizatorii se vor autentifica în aplicație folosind email-ul și CNP-ul. În funcție de rolul utilizatorului:
- **Studenții** pot accesa calendarul, catalogul, grupurile de studiu și funcția de chat.
- **Profesorii** au acces la gestionarea notelor, calendar și pot participa în grupuri de studiu.
- **Administratorii** pot administra informațiile despre utilizatori, având și funcționalități avansate pentru super administratori.

## Concluzii și Dezvoltări Viitoare
Platforma noastră educațională este proiectată să îmbunătățească interacțiunea și organizarea dintre participanții la procesul educațional. Viitoarele îmbunătățiri includ adăugarea de funcții de analiză și raportare, notificări în timp real și adaptarea pentru dispozitive mobile.
