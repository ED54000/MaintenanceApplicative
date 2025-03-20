import Evenements.*;
import Evenements.EvenementPeriodique.EvenementPeriodique;
import Evenements.EvenementPeriodique.FrequenceEvenement;
import Evenements.Formations.Formation;
import Evenements.Rdv.RdvPersonnel;
import Evenements.Reunions.LieuEvenement;
import Evenements.Reunions.Participants;
import Evenements.Reunions.Reunion;
import Utilisateurs.*;

import java.time.LocalDateTime;
import java.time.temporal.WeekFields;
import java.util.*;

class GestionEvenement {
    private final CalendarManager calendar;
    private final Scanner scanner;
    private Utilisateur utilisateur;
    private final Map<String, Runnable> actions;

    public GestionEvenement(CalendarManager calendar, Scanner scanner, Utilisateur utilisateur) {
        this.calendar = calendar;
        this.scanner = scanner;
        this.utilisateur = utilisateur;
        this.actions = initialiserActions();
    }

    public void afficherMenu() {
        System.out.println("\nBonjour, " + utilisateur);
        System.out.println("=== Menu Gestionnaire d'Événements ===");
        System.out.println("1 - Voir les événements");
        System.out.println("2 - Ajouter un rendez-vous perso");
        System.out.println("3 - Ajouter une réunion");
        System.out.println("4 - Ajouter un évènement périodique");
        System.out.println("5 - Ajouter une formation");
        System.out.println("6 - Se déconnecter");
        System.out.print("Votre choix : ");

        actions.getOrDefault(scanner.nextLine(), this::choixInvalide).run();
    }

    private Map<String, Runnable> initialiserActions() {
        return Map.of(
                "1", this::afficherEvenement,
                "2", () -> ajouterRdvPersonnel(calendar, scanner, utilisateur),
                "3", () -> ajouterReunion(calendar, scanner, utilisateur),
                "4", () -> ajouterEvenementPeriodique(calendar, scanner, utilisateur),
                "5", () -> ajouterFormation(calendar,scanner,utilisateur),
                "6", this::seDeconnecter
        );
    }

    private void afficherEvenement() {
        System.out.println("\n=== Menu de visualisation d'Événements ===");
        System.out.println("1 - Afficher TOUS les événements");
        System.out.println("2 - Afficher les événements d'un MOIS précis");
        System.out.println("3 - Afficher les événements d'une SEMAINE précise");
        System.out.println("4 - Afficher les événements d'un JOUR précis");
        System.out.println("5 - Retour");
        System.out.print("Votre choix : ");

        Map<String, Runnable> affichageActions = Map.of(
                "1", calendar::afficherEvenements,
                "2", this::afficherEvenementsMois,
                "3", this::afficherEvenementsSemaine,
                "4", this::afficherEvenementsJour
        );

        affichageActions.getOrDefault(scanner.nextLine(), this::choixInvalide).run();
    }

    private static void afficherListe(List<Evenements> evenements) {
        Optional.of(evenements)
                .filter(list -> !list.isEmpty())
                .ifPresentOrElse(
                        list -> {
                            System.out.println("Événements trouvés : ");
                            list.forEach(e -> System.out.println("- " + e.description()));
                        },
                        () -> System.out.println("Aucun événement trouvé pour cette période.")
                );
    }

    private void ajouterRdvPersonnel(CalendarManager calendar, Scanner scanner, Utilisateur utilisateur) {
        System.out.print("Titre de l'événement : ");
        String titre = scanner.nextLine();
        LocalDateTime date = demanderDate(scanner);
        System.out.println("Durée (en minutes) :");
        int duree = Integer.parseInt(scanner.nextLine());

        RdvPersonnel rdv = new RdvPersonnel(
                EventId.generer(),
                new TitreEvenement(titre),
                new Proprietaire(utilisateur.getNom()),
                new DateEvenement(date),
                new DureeEvenement(duree)
        );
        calendar.ajouterEvent(rdv);
        System.out.println("Événement ajouté.");
    }

    private void ajouterReunion(CalendarManager calendar, Scanner scanner, Utilisateur utilisateur) {
        System.out.print("Titre de la réunion : ");
        String titre = scanner.nextLine();
        LocalDateTime date = demanderDate(scanner);
        System.out.print("Durée (en minutes) : ");
        int duree = Integer.parseInt(scanner.nextLine());
        System.out.print("Lieu : ");
        String lieu = scanner.nextLine();
        Utilisateurs participants = new Utilisateurs();
        participants.addParticipants();

        Reunion reunion = new Reunion(
                new TitreEvenement(titre),
                EventId.generer(),
                new Proprietaire(utilisateur.getNom()),
                new DateEvenement(date),
                new DureeEvenement(duree),
                new LieuEvenement(lieu),
                new Participants(participants.getUtilisateurs())
        );

        calendar.ajouterEvent(reunion);
        System.out.println("Réunion ajoutée.");
    }

    private void ajouterEvenementPeriodique(CalendarManager calendar, Scanner scanner, Utilisateur utilisateur) {
        System.out.print("Titre de l'événement périodique : ");
        String titre = scanner.nextLine();
        LocalDateTime date = demanderDate(scanner);
        System.out.print("Durée (en minutes) : ");
        int duree = Integer.parseInt(scanner.nextLine());
        System.out.print("Fréquence (en jours) : ");
        int frequence = Integer.parseInt(scanner.nextLine());

        EvenementPeriodique event = new EvenementPeriodique(
                new TitreEvenement(titre),
                EventId.generer(),
                new Proprietaire(utilisateur.getNom()),
                new DateEvenement(date),
                new DureeEvenement(duree),
                new FrequenceEvenement(frequence)
        );

        calendar.ajouterEvent(event);
        System.out.println("Événement périodique ajouté.");
    }

    private void ajouterFormation(CalendarManager calendar, Scanner scanner, Utilisateur utilisateur) {
        System.out.print("Titre de la formation : ");
        String titre = scanner.nextLine();
        System.out.print("Formateur : ");
        String formateur = scanner.nextLine();
        LocalDateTime date = demanderDate(scanner);
        System.out.print("Durée (en minutes) : ");
        int duree = Integer.parseInt(scanner.nextLine());
        Formation formation = new Formation(
                new TitreEvenement(titre),
                EventId.generer(),
                new Proprietaire(utilisateur.getNom()),
                new DateEvenement(date),
                new DureeEvenement(duree),
                formateur);
        calendar.ajouterEvent(formation);
        System.out.println("Formation ajoutée !");
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

    private void afficherEvenementsMois() {
        int annee = demanderEntier("Année (AAAA) : ");
        int mois = demanderEntier("Mois (1-12) : ");
        LocalDateTime debut = LocalDateTime.of(annee, mois, 1, 0, 0);
        LocalDateTime fin = debut.plusMonths(1).minusSeconds(1);

        afficherListe(calendar.eventsDansPeriode(new DateEvenement(debut), new DateEvenement(fin)));
    }

    private void afficherEvenementsSemaine() {
        int annee = demanderEntier("Année (AAAA) : ");
        int semaine = demanderEntier("Numéro de semaine (1-52) : ");
        LocalDateTime debut = LocalDateTime.now()
                .withYear(annee)
                .with(WeekFields.of(Locale.FRANCE).weekOfYear(), semaine)
                .with(WeekFields.of(Locale.FRANCE).dayOfWeek(), 1)
                .withHour(0).withMinute(0);
        LocalDateTime fin = debut.plusDays(7).minusSeconds(1);

        afficherListe(calendar.eventsDansPeriode(new DateEvenement(debut), new DateEvenement(fin)));
    }

    private void afficherEvenementsJour() {
        int annee = demanderEntier("Année (AAAA) : ");
        int mois = demanderEntier("Mois (1-12) : ");
        int jour = demanderEntier("Jour (1-31) : ");
        LocalDateTime debut = LocalDateTime.of(annee, mois, jour, 0, 0);
        LocalDateTime fin = debut.plusDays(1).minusSeconds(1);

        afficherListe(calendar.eventsDansPeriode(new DateEvenement(debut), new DateEvenement(fin)));
    }

    private int demanderEntier(String message) {
        System.out.print(message);
        return Integer.parseInt(scanner.nextLine());
    }

    private void seDeconnecter() {
        System.out.println("Déconnexion...");
        utilisateur = null;
    }

    private void choixInvalide() {
        System.out.println("Choix invalide.");
    }
}