package org.proiect.proiectjavafxbd;

public class colegStudent {

    private String nume;
    private String prenume;
    private String anDeStudiu;
    private String grupa;

    public colegStudent(String nume, String prenume, String anDeStudiu, String grupa) {
        this.nume = nume;
        this.prenume = prenume;
        this.anDeStudiu = anDeStudiu;
        this.grupa= grupa;
    }

    public String getNume() {
        return nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public String getAnDeStudiu() {
        return anDeStudiu;
    }
    public String getGrupa() {
        return grupa;
    }
}