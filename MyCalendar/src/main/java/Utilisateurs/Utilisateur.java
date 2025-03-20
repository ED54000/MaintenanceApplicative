package Utilisateurs;

import java.util.Scanner;

public class Utilisateur {

    String nom;
    String motDePasse;

    public Utilisateur() {
        nom = null;
        motDePasse = null;
    }

    public Utilisateur(String nom) {
        this.nom = nom;
    }

    public Utilisateur(String nom, String motDePasse) {
        this.nom = nom;
        this.motDePasse = motDePasse;
    }


    public String getMotDePasse() {
        return motDePasse;
    }

    public String getNom() {
        return nom;
    }

    @Override
    public String toString() {
        return nom;
    }

    public void setNom(String s) {
        nom = s;
    }
}
