package Evenements;

import Evenements.EvenementPeriodique.EvenementPeriodique;
import Evenements.EvenementPeriodique.FrequenceEvenement;
import Evenements.Rdv.RdvPersonnel;
import Evenements.Reunions.LieuEvenement;
import Evenements.Reunions.Participants;
import Evenements.Reunions.Reunion;
import Utilisateurs.Utilisateur;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EvenementsTest {

    @Test
    void shouldGenerateCorrectDescriptionForReunion() {
        Utilisateur alice = new Utilisateur("Alice");
        Utilisateur bob = new Utilisateur("Bob");
        Reunion reunion = new Reunion(
                new TitreEvenement("Réunion Projet"),
                new Proprietaire("Alice"),
                new DateEvenement(LocalDateTime.of(2025, 3, 10, 14, 0)),
                new DureeEvenement(60),
                new LieuEvenement("Salle A"),
                new Participants(List.of(alice,bob))
        );

        String expectedDescription = "Réunion : Réunion Projet organisé par Alice à Salle A avec Alice, Bob.";
        assertEquals(expectedDescription, reunion.description());
    }

    @Test
    void shouldGenerateCorrectDescriptionForRdvPersonnel() {
        RdvPersonnel rdv = new RdvPersonnel(
                new TitreEvenement("Rendez-vous Médical"),
                new Proprietaire("Bob"),
                new DateEvenement(LocalDateTime.of(2025, 3, 11, 10, 30)),
                new DureeEvenement(30)
        );

        String expectedDescription = "Rendez-vous personnel : Rendez-vous Médical de Bob.";
        assertEquals(expectedDescription, rdv.description());
    }

    @Test
    void shouldGenerateCorrectDescriptionForEvenementPeriodique() {
        EvenementPeriodique event = new EvenementPeriodique(
                new TitreEvenement("Cours de Yoga"),
                new Proprietaire("Claire"),
                new DateEvenement(LocalDateTime.of(2025, 3, 12, 18, 0)),
                new DureeEvenement(90),
                new FrequenceEvenement(7) // Hebdomadaire
        );

        String expectedDescription = "Événement périodique : Cours de Yoga organisé par Claire tous les 7 jours.";
        assertEquals(expectedDescription, event.description());
    }
}
