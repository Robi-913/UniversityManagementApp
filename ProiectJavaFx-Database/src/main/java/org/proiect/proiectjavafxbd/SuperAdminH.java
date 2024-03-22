package org.proiect.proiectjavafxbd;

public class SuperAdminH {

    private String nume;
    private String prenume;
    private String adresa;


    private String email;
    private String IBAN;
    private String CNP;

    public SuperAdminH(String nume, String prenume,String CNP,String IBAN, String adresa,String email) {
        this.nume = nume;
        this.prenume = prenume;
        this.CNP=CNP;
        this.IBAN = IBAN;
        this.adresa = adresa;
        this.email = email;


    }

    public String getNume() {
        return nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public String getAdresa() {
        return adresa;
    }

    public String getCNP() {
        return CNP;
    }

    public String getEmail() {
        return email;
    }

    public String getIBAN() {
        return IBAN;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
    }

    public void setCNP(String CNP) {
        this.CNP = CNP;
    }

}
