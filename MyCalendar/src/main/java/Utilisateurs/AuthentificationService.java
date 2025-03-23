package Utilisateurs;

import java.util.*;

import java.util.function.Function;

public class AuthentificationService {
    private final Scanner scanner;
    private final Utilisateurs utilisateurs;

    public AuthentificationService(Scanner scanner) {
        this.scanner = scanner;
        this.utilisateurs = new Utilisateurs();
    }

    public Utilisateur demanderConnexion() {
        System.out.println("            ___              _                        _                            __  __                            __ _                  ");
        System.out.println("    o O O  / __|   __ _     | |     ___    _ _     __| |   __ _      _ _     o O O|  \\/  |  __ _    _ _     __ _    / _` |   ___      _ _    ");
        System.out.println("   o      | (__   / _` |    | |    / -_)  | ' \\   / _` |  / _` |    | '_|   o     | |\\/| | / _` |  | ' \\   / _` |   \\__, |  / -_)    | '_| ");
        System.out.println("  TS__[O]  \\___|  \\__,_|   _|_|_   \\___|  |_||_|  \\__,_|  \\__,_|   _|_|_   TS__[O]|_|__|_| \\__,_|  |_||_|  \\__,_|   |___/   \\___|   _|_|_  ");
        System.out.println(" {======|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"| {======|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"| ");
        System.out.println("./o--000'\"`-0-0-'\"`-0-0-'\"`-0-0-'\"`-0-0-'\"`-0-0-'\"`-0-0-'\"`-0-0-'\"`-0-0-'./o--000'\"`-0-0-'\"`-0-0-'\"`-0-0-'\"`-0-0-'\"`-0-0-'\"`-0-0-'\"`-0-0-'");
        System.out.println("1 - Se connecter");
        System.out.println("2 - Créer un compte");
        System.out.print("Choix : ");

        Map<String, Function<Scanner, Utilisateur>> actions = Map.of(
                "1", this::connecterUtilisateur,
                "2", this::inscrireUtilisateur
        );

        return actions.getOrDefault(scanner.nextLine(), s -> {
            System.out.println("Choix invalide.");
            return null;
        }).apply(scanner);
    }

    private Utilisateur connecterUtilisateur(Scanner scanner) {
        System.out.print("Nom d'utilisateur : ");
        String nom = scanner.nextLine();
        System.out.print("Mot de passe : ");
        String motDePasse = scanner.nextLine();

        return utilisateurs.authentifier(nom, motDePasse);
    }

    private Utilisateur inscrireUtilisateur(Scanner scanner) {
        System.out.print("Nom d'utilisateur : ");
        String nom = scanner.nextLine();
        System.out.print("Mot de passe : ");
        String motDePasse = scanner.nextLine();
        System.out.print("Répéter mot de passe : ");

        return Optional.of(scanner.nextLine())
                .filter(confirm -> confirm.equals(motDePasse))
                .map(confirm -> utilisateurs.ajouterUtilisateur(nom, motDePasse))
                .orElseGet(() -> {
                    System.out.println("Les mots de passe ne correspondent pas...");
                    return null;
                });
    }


}