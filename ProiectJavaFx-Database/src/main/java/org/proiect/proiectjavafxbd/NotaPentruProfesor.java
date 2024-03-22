package org.proiect.proiectjavafxbd;

public class NotaPentruProfesor {

    private String Materie;
    private String numeStudent;
    private float notaCurs;
    private float notaSeminar;
    private float notaLaborator;
    private float notaFinala;
    private float procentNotaCurs;
    private float procentNotaSeminar;
    private float ProcentNotaLaborator;

    public NotaPentruProfesor(String materie, String numeStudent, float notaCurs, float notaSeminar, float notaLaborator, float notaFinala) {
        Materie = materie;
        this.numeStudent = numeStudent;
        this.notaCurs = notaCurs;
        this.notaSeminar = notaSeminar;
        this.notaLaborator = notaLaborator;
        this.notaFinala = notaFinala;
    }

    public String getMaterie() {
        return Materie;
    }

    public String getNumeStudent() {
        return numeStudent;
    }

    public float getNotaCurs() {
        return notaCurs;
    }

    public float getNotaSeminar() {
        return notaSeminar;
    }

    public float getNotaLaborator() {
        return notaLaborator;
    }

    public float getNotaFinala() {
        return notaFinala;
    }

    public float getProcentNotaCurs() {
        return procentNotaCurs;
    }

    public float getProcentNotaSeminar() {
        return procentNotaSeminar;
    }

    public float getProcentNotaLaborator() {
        return ProcentNotaLaborator;
    }

    public void setMaterie(String materie) {
        Materie = materie;
    }

    public void setNumeStudent(String numeStudent) {
        this.numeStudent = numeStudent;
    }

    public void setNotaCurs(float notaCurs) {
        this.notaCurs = notaCurs;
    }

    public void setNotaSeminar(float notaSeminar) {
        this.notaSeminar = notaSeminar;
    }

    public void setNotaLaborator(float notaLaborator) {
        this.notaLaborator = notaLaborator;
    }

    public void setNotaFinala(float notaFinala) {
        this.notaFinala = notaFinala;
    }

    public void setProcentNotaCurs(float procentNotaCurs) {
        this.procentNotaCurs = procentNotaCurs;
    }

    public void setProcentNotaSeminar(float procentNotaSeminar) {
        this.procentNotaSeminar = procentNotaSeminar;
    }

    public void setProcentNotaLaborator(float ProcentNotaLaborator) {
        this.ProcentNotaLaborator = ProcentNotaLaborator;
    }
}
