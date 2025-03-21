import Evenements.*;
import Evenements.Rdv.RdvPersonnel;
import Evenements.Reunions.*;
import Utilisateurs.Utilisateur;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CalendarManagerTest {

    private CalendarManager calendar;

    @BeforeEach
    void setUp() {
        calendar = new CalendarManager();
    }

    @Test
    void shouldReturnEventsWithinGivenPeriod() {
        LocalDateTime debut = LocalDateTime.of(2025, 3, 1, 0, 0);
        LocalDateTime fin = LocalDateTime.of(2025, 3, 31, 23, 59);

        TitreEvenement titre1 = new TitreEvenement("Réunion Projet");
        Proprietaire proprietaire1 = new Proprietaire("Alice");
        DateEvenement date1 = new DateEvenement(LocalDateTime.of(2025, 3, 10, 14, 0));
        DureeEvenement duree1 = new DureeEvenement(60);
        Utilisateur alice = new Utilisateur("Alice");
        Utilisateur bob = new Utilisateur("Bob");
        Reunion reunion = new Reunion(titre1, EventId.generer(), proprietaire1, date1, duree1, new LieuEvenement("Salle A"), new Participants(List.of(alice,bob)));

        TitreEvenement titre2 = new TitreEvenement("Rendez-vous médical");
        Proprietaire proprietaire2 = new Proprietaire("Bob");
        DateEvenement date2 = new DateEvenement(LocalDateTime.of(2025, 4, 5, 10, 0));
        DureeEvenement duree2 = new DureeEvenement(30);
        RdvPersonnel rdv = new RdvPersonnel(EventId.generer(), titre2, proprietaire2, date2, duree2);

        calendar.ajouterEvent(reunion);
        calendar.ajouterEvent(rdv);

        List<Evenements> events = calendar.afficherEvenementsDansPeriode(new DateEvenement(debut), new DateEvenement(fin));

        assertEquals(1, events.size());
        assertEquals("Réunion Projet", events.getFirst().getTitre());
    }

    @Test
    void shouldDetectConflictingEvents() {
        TitreEvenement titre1 = new TitreEvenement("Réunion Projet");
        Proprietaire proprietaire1 = new Proprietaire("Alice");
        DateEvenement date1 = new DateEvenement(LocalDateTime.of(2025, 3, 10, 14, 0));
        DureeEvenement duree1 = new DureeEvenement(60);
        Utilisateur alice = new Utilisateur("Alice");
        Utilisateur bob = new Utilisateur("Bob");
        Reunion reunion = new Reunion(titre1, EventId.generer(), proprietaire1, date1, duree1, new LieuEvenement("Salle A"), new  Participants(List.of(alice,bob)));

        TitreEvenement titre2 = new TitreEvenement("Rendez-vous Médical");
        Proprietaire proprietaire2 = new Proprietaire("Bob");
        DateEvenement date2 = new DateEvenement(LocalDateTime.of(2025, 3, 10, 14, 30)); // Se chevauche avec la réunion
        DureeEvenement duree2 = new DureeEvenement(30);
        RdvPersonnel rdv = new RdvPersonnel(EventId.generer(), titre2, proprietaire2, date2, duree2);

        calendar.ajouterEvent(reunion);
        calendar.ajouterEvent(rdv);

        List<Evenements> conflits = calendar.detecterConflits();

        assertEquals(2, conflits.size());
        assertTrue(conflits.contains(reunion));
        assertTrue(conflits.contains(rdv));
    }


    @Test
    void shouldNotDetectConflictsWhenEventsAreSeparated() {
        TitreEvenement titre1 = new TitreEvenement("Réunion Matin");
        Proprietaire proprietaire1 = new Proprietaire("Alice");
        DateEvenement date1 = new DateEvenement(LocalDateTime.of(2025, 3, 10, 9, 0));
        DureeEvenement duree1 = new DureeEvenement(60);
        Utilisateur alice = new Utilisateur("Alice");
        Utilisateur bob = new Utilisateur("Bob");
        Reunion reunion = new Reunion(titre1, EventId.generer(), proprietaire1, date1, duree1, new LieuEvenement("Salle A"), new Participants(List.of(alice,bob)));

        TitreEvenement titre2 = new TitreEvenement("Rendez-vous Après-midi");
        Proprietaire proprietaire2 = new Proprietaire("Bob");
        DateEvenement date2 = new DateEvenement(LocalDateTime.of(2025, 3, 10, 11, 0)); // Aucun chevauchement
        DureeEvenement duree2 = new DureeEvenement(30);
        RdvPersonnel rdv = new RdvPersonnel(EventId.generer(), titre2, proprietaire2, date2, duree2);

        calendar.ajouterEvent(reunion);
        calendar.ajouterEvent(rdv);

        List<Evenements> conflits = calendar.detecterConflits();

        assertTrue(conflits.isEmpty());
    }
}