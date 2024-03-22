package org.proiect.proiectjavafxbd;

public class AdministratorSuggestionH {

    private static String CNP;


    public  AdministratorSuggestionH(String CNP) {
        this.CNP = CNP;
    }

    public static String getCNP() {
        return CNP;
    }

    public  void setCNP(String CNP) {
        this.CNP = CNP;
    }

    public static void setCNPGLOBAL(String Cnp) {
        AdministratorSuggestionH.CNP = Cnp;
    }
}
