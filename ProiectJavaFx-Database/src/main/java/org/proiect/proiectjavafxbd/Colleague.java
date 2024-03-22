package org.proiect.proiectjavafxbd;

public class Colleague {
    private String nume;
    private String prenume;
    private String Departament;
    private String CNP;
    private String IBAN;



    private String adresa;
    private String email;


    public Colleague(String nume, String prenume, String departament) {
        this.nume = nume;
        this.prenume = prenume;
        this.Departament = departament;
    }

    public Colleague(String nume, String prenume, String CNP, String IBAN,String departament,  String adresa, String email) {
        this.nume = nume;
        this.prenume = prenume;
        this.CNP = CNP;
        this.IBAN = IBAN;
        this.Departament = departament;
        this.adresa = adresa;
        this.email = email;
    }


    public String getNume() {
        return nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public String getDepartament() {
        return Departament;
    }

    public String getCNP() {
        return CNP;
    }

    public String getIBAN() {
        return IBAN;
    }

    public String getAdresa() {
        return adresa;
    }

    public String getEmail() {
        return email;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public void setDepartament(String departament) {
        Departament = departament;
    }

    public void setCNP(String CNP) {
        this.CNP = CNP;
    }

    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}