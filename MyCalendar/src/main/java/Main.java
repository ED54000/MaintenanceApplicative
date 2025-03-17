import Evenements.*;
import Evenements.EvenementPeriodique.EvenementPeriodique;
import Evenements.EvenementPeriodique.FrequenceEvenement;
import Evenements.Rdv.RdvPersonnel;
import Evenements.Reunions.LieuEvenement;
import Evenements.Reunions.Participants;
import Evenements.Reunions.Reunion;

import java.time.LocalDateTime;
import java.time.temporal.WeekFields;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CalendarManager calendar = new CalendarManager();
        try (Scanner scanner = new Scanner(System.in)) {
            String utilisateur = null;
            boolean continuer = true;

            String[] utilisateurs = new String[99];
            String[] motsDePasses = new String[99];
            int nbUtilisateurs = 0;

            while (true) {

                if (utilisateur == null) {
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
                            "                                                                                  |___/");

                    System.out.println("1 - Se connecter");
                    System.out.println("2 - Créer un compte");
                    System.out.println("Choix : ");

                    switch (scanner.nextLine()) {
                        case "1":
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

                        case "2":
                            System.out.print("Nom d'utilisateur: ");
                            utilisateur = scanner.nextLine();
                            System.out.print("Mot de passe: ");
                            String motDePasse = scanner.nextLine();
                            System.out.print("Répéter mot de passe: ");
                            if (scanner.nextLine().equals(motDePasse)) {
                                utilisateurs[nbUtilisateurs] = utilisateur;
                                motsDePasses[nbUtilisateurs] = motDePasse;
                                nbUtilisateurs = nbUtilisateurs + 1;
                            } else {
                                System.out.println("Les mots de passes ne correspondent pas...");
                                utilisateur = null;
                            }
                            break;
                    }
                }

                while (continuer && utilisateur != null) {
                    System.out.println("\nBonjour, " + utilisateur);
                    System.out.println("=== Menu Gestionnaire d'Événements ===");
                    System.out.println("1 - Voir les événements");
                    System.out.println("2 - Ajouter un rendez-vous perso");
                    System.out.println("3 - Ajouter une réunion");
                    System.out.println("4 - Ajouter un évènement périodique");
                    System.out.println("5 - Se déconnecter");
                    System.out.print("Votre choix : ");

                    String choix = scanner.nextLine();

                    switch (choix) {
                        case "1" -> afficherEvenements(calendar, scanner);
                        case "2" -> ajouterRdvPersonnel(calendar, scanner, utilisateur);
                        case "3" -> ajouterEvenementPeriodique(calendar, scanner, utilisateur);
                        case "4" -> ajouterReunion(calendar, scanner, utilisateur);
                        default -> {
                            System.out.println("Déconnexion ! Voulez-vous continuer ? (O/N)");
                            continuer = scanner.nextLine().trim().equalsIgnoreCase("oui");
                            utilisateur = null;
                        }
                    }
                }
            }
        }
    }


    private static void afficherEvenements(CalendarManager calendar, Scanner scanner) {
        System.out.println("\n=== Menu de visualisation d'Événements ===");
        System.out.println("1 - Afficher TOUS les événements");
        System.out.println("2 - Afficher les événements d'un MOIS précis");
        System.out.println("3 - Afficher les événements d'une SEMAINE précise");
        System.out.println("4 - Afficher les événements d'un JOUR précis");
        System.out.println("5 - Retour");
        System.out.print("Votre choix : ");

        String choix = scanner.nextLine();
        LocalDateTime debut, fin;

        switch (choix) {
            case "1" -> calendar.afficherEvenements();
            case "2" -> {
                System.out.print("Entrez l'année (AAAA) : ");
                int anneeMois = Integer.parseInt(scanner.nextLine());
                System.out.print("Entrez le mois (1-12) : ");
                int mois = Integer.parseInt(scanner.nextLine());

                debut = LocalDateTime.of(anneeMois, mois, 1, 0, 0);
                fin = debut.plusMonths(1).minusSeconds(1);
                afficherListe(calendar.eventsDansPeriode(new DateEvenement(debut), new DateEvenement(fin)));
            }

            case "3" -> {
                System.out.print("Entrez l'année (AAAA) : ");
                int anneeSemaine = Integer.parseInt(scanner.nextLine());
                System.out.print("Entrez le numéro de semaine (1-52) : ");
                int semaine = Integer.parseInt(scanner.nextLine());

                debut = LocalDateTime.now()
                        .withYear(anneeSemaine)
                        .with(WeekFields.of(Locale.FRANCE).weekOfYear(), semaine)
                        .with(WeekFields.of(Locale.FRANCE).dayOfWeek(), 1)
                        .withHour(0).withMinute(0);
                fin = debut.plusDays(7).minusSeconds(1);
                afficherListe(calendar.eventsDansPeriode(new DateEvenement(debut), new DateEvenement(fin)));
            }

            case "4" -> {
                System.out.print("Entrez l'année (AAAA) : ");
                int anneeJour = Integer.parseInt(scanner.nextLine());
                System.out.print("Entrez le mois (1-12) : ");
                int moisJour = Integer.parseInt(scanner.nextLine());
                System.out.print("Entrez le jour (1-31) : ");
                int jour = Integer.parseInt(scanner.nextLine());

                debut = LocalDateTime.of(anneeJour, moisJour, jour, 0, 0);
                fin = debut.plusDays(1).minusSeconds(1);

                afficherListe(calendar.eventsDansPeriode(new DateEvenement(debut), new DateEvenement(fin)));
            }
        }
    }

    private static void afficherListe(List<Evenements> evenements) {
        if (evenements.isEmpty()) {
            System.out.println("Aucun événement trouvé pour cette période.");
        } else {
            System.out.println("Événements trouvés : ");
            for (Evenements e : evenements) {
                System.out.println("- " + e.description());
            }
        }
    }

    private static void ajouterRdvPersonnel(CalendarManager calendar, Scanner scanner, String utilisateur) {
        // Ajout simplifié d'un RDV personnel
        System.out.print("Titre de l'événement : ");
        String titre = scanner.nextLine();
        LocalDateTime date = demanderDate(scanner);
        System.out.println("Durée (en minutes) :");
        int duree = Integer.parseInt(scanner.nextLine());

        RdvPersonnel rdv = new RdvPersonnel(
                new TitreEvenement(titre),
                new Proprietaire(utilisateur),
                new DateEvenement(date),
                new DureeEvenement(duree)
        );
        calendar.ajouterEvent(rdv);
        System.out.println("Événement ajouté.");

    }

    private static void ajouterReunion(CalendarManager calendar, Scanner scanner, String utilisateur) {
        System.out.print("Titre de la réunion : ");
        String titre = scanner.nextLine();
        LocalDateTime date = demanderDate(scanner);
        System.out.print("Durée (en minutes) : ");
        int duree = Integer.parseInt(scanner.nextLine());
        System.out.print("Lieu : ");
        String lieu = scanner.nextLine();

        System.out.println("Ajouter des participants ? (tapez 'fin' pour arrêter)");
        List<String> participants = new java.util.ArrayList<>();
        participants.add(utilisateur);

        while (true) {
            String participant = scanner.nextLine();
            if (participant.equalsIgnoreCase("fin")) break;
            participants.add(participant);
        }

        Reunion reunion = new Reunion(
                new TitreEvenement(titre),
                new Proprietaire(utilisateur),
                new DateEvenement(date),
                new DureeEvenement(duree),
                new LieuEvenement(lieu),
                new Participants(participants)
        );

        calendar.ajouterEvent(reunion);
        System.out.println("Réunion ajoutée.");
    }

    private static void ajouterEvenementPeriodique(CalendarManager calendar, Scanner scanner, String utilisateur) {
        System.out.print("Titre de l'événement périodique : ");
        String titre = scanner.nextLine();
        LocalDateTime date = demanderDate(scanner);
        System.out.print("Durée (en minutes) : ");
        int duree = Integer.parseInt(scanner.nextLine());
        System.out.print("Fréquence (en jours) : ");
        int frequence = Integer.parseInt(scanner.nextLine());

        EvenementPeriodique event = new EvenementPeriodique(
                new TitreEvenement(titre),
                new Proprietaire(utilisateur),
                new DateEvenement(date),
                new DureeEvenement(duree),
                new FrequenceEvenement(frequence)
        );

        calendar.ajouterEvent(event);
        System.out.println("Événement périodique ajouté.");
    }

    private static LocalDateTime demanderDate(Scanner scanner) {
        System.out.print("Année (AAAA) : ");
        int annee = Integer.parseInt(scanner.nextLine());
        System.out.print("Mois (1-12) : ");
        int mois = Integer.parseInt(scanner.nextLine());
        System.out.print("Jour (1-31) : ");
        int jour = Integer.parseInt(scanner.nextLine());
        System.out.print("Heure début (0-23) : ");
        int heure = Integer.parseInt(scanner.nextLine());
        System.out.print("Minute début (0-59) : ");
        int minute = Integer.parseInt(scanner.nextLine());

        return LocalDateTime.of(annee, mois, jour, heure, minute);
    }


}
