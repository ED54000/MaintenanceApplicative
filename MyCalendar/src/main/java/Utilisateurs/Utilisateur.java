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

    public void creerNom(Scanner scanner) {
        nom = scanner.nextLine();
    }

    public void creerMotDePasse(Scanner scanner) {
        motDePasse = scanner.nextLine();
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
}
