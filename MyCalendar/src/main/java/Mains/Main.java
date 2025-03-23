package Mains;

import Management.CalendarManager;
import Management.GestionEvenement;
import Utilisateurs.AuthentificationService;
import Utilisateurs.Utilisateur;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CalendarManager calendar = new CalendarManager();
        try (Scanner scanner = new Scanner(System.in)) {
            AuthentificationService authentificationService = new AuthentificationService(scanner);

            while (true) {
                Utilisateur utilisateur;

                do {
                    utilisateur = authentificationService.demanderConnexion();
                } while (utilisateur == null);

                GestionEvenement gestionEvenement = new GestionEvenement(calendar, scanner, utilisateur);

                while (gestionEvenement.afficherMenu());
                
            }
        }
    }
}
