import Utilisateurs.AuthentificationService;
import Utilisateurs.Utilisateur;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CalendarManager calendar = new CalendarManager();
        try (Scanner scanner = new Scanner(System.in)) {
            Utilisateur utilisateur = new Utilisateur();

            while (utilisateur.getNom() == null) {
                utilisateur = AuthentificationService.demanderConnexion(scanner, utilisateur);
            }
            while (utilisateur.getNom() != null) {
                new GestionEvenement(calendar, scanner, utilisateur).afficherMenu();
            }
        }
    }
}

