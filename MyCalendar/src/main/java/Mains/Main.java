import Utilisateurs.AuthentificationService;
import Utilisateurs.Utilisateur;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CalendarManager calendar = new CalendarManager();
        try (Scanner scanner = new Scanner(System.in)) {
            AuthentificationService authentificationService = new AuthentificationService(scanner);
            Utilisateur utilisateur;

            do {
                utilisateur = authentificationService.demanderConnexion();
            } while (utilisateur == null);

            while (utilisateur.getNom() != null && !utilisateur.getNom().isEmpty()) {
                System.out.println(utilisateur.getNom());
                System.out.println(utilisateur);
                new GestionEvenement(calendar, scanner, utilisateur).afficherMenu();
            }
        }
    }
}

