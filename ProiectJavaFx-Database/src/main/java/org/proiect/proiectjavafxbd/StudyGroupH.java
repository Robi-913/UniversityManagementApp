package org.proiect.proiectjavafxbd;

public class StudyGroupH {

    private String nume;
    private String prenume;
    private String activitate;


    public StudyGroupH(String name, String prenume, String activitate){

        this.nume=name;
        this.prenume=prenume;
        this.activitate=activitate;

    }

    public StudyGroupH(String activitate) {
        this.activitate = activitate;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getActivitate() {
        return activitate;
    }

    public void setActivitate(String activitate) {
        this.activitate = activitate;
    }
}
