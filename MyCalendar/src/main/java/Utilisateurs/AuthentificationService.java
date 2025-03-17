package Utilisateurs;

import java.util.Scanner;

public class AuthentificationService {
    

    public static Utilisateur demanderConnexion(Scanner scanner,Utilisateur utilisateur) {
        Utilisateurs utilisateurs = new Utilisateurs();
      /*
        System.out.println("  _____         _                   _                __  __");
        System.out.println(" / ____|       | |                 | |              |  \\/  |");
        System.out.println(
                "| |       __ _ | |  ___  _ __    __| |  __ _  _ __  | \\  / |  __ _  _ __    __ _   __ _   ___  _ __");
        System.out.println(
                "| |      / _` || | / _ \\| '_ \\  / _` | / _` || '__| | |\\/| | / _` || '_ \\  / _` | / _` | / _ \\| '__|");
        System.out.println(
                "| |____ | (_| || ||  __/| | | || (_| || (_| || |    | |  | || (_| || | | || (_| || (_| ||  __/| |");
        System.out.println(
                " \\_____| \\__,_||_| \\___||_| |_| \\__,_| \\__,_||_|    |_|  |_| \\__,_||_| |_| \\__,_| \\__, | \\___||_|");
        System.out.println(
                "                                                                                   __/ |");
        System.out.println(
                "                                                                                  |___/");*/

        System.out.println("1 - Se connecter");
        System.out.println("2 - Créer un compte");
        System.out.println("Choix : ");
      switch (scanner.nextLine()) {
          /*  case "1":
                System.out.print("Nom d'utilisateur: ");
                utilisateur = scanner.nextLine();

                if (utilisateur.equals("Roger")) {
                    String motDePasse = scanner.nextLine();
                    if (!motDePasse.equals("Chat")) {
                        utilisateur = null;
                    }
                } else {
                    if (utilisateur.equals("Pierre")) {
                        String motDePasse = scanner.nextLine();
                        if (!motDePasse.equals("KiRouhl")) {
                            utilisateur = null;
                        }
                    } else {
                        System.out.print("Mot de passe: ");
                        String motDePasse = scanner.nextLine();

                        for (int i = 0; i < nbUtilisateurs; i = i + 1) {
                            assert utilisateurs[i] != null;
                            if (utilisateurs[i].equals(utilisateur) && motsDePasses[i].equals(motDePasse)) {
                                utilisateur = utilisateurs[i];
                            }
                        }
                    }
                }
                break;
*/
            case "2":
                System.out.print("Nom d'utilisateur: ");
                utilisateur.creerNom(scanner);
                System.out.print("Mot de passe: ");
                utilisateur.creerMotDePasse(scanner);
                System.out.print("Répéter mot de passe: ");
                if (scanner.nextLine().equals(utilisateur.getMotDePasse())) {
                    utilisateurs.addUtilisateur(utilisateur);

                } else {
                    System.out.println("Les mots de passes ne correspondent pas...");
                    utilisateur = null;
                }
                break;
        }
        return utilisateur;
    }
}
