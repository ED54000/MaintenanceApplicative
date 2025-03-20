
import Evenements.*;
import Evenements.Formations.Formation;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

class FormationTest {

    @Test
    void shouldCreateFormationEvent() {
        TitreEvenement titre = new TitreEvenement("Java TDD");
        Proprietaire proprietaire = new Proprietaire("Alice");
        DateEvenement date = new DateEvenement(LocalDateTime.of(2025, 3, 20, 14, 0));
        DureeEvenement duree = new DureeEvenement(120);
        EventId eventId = EventId.generer();
        String formateur = "Bob";

        Formation formation = new Formation(titre,eventId, proprietaire, date, duree, formateur);

        assertEquals("Java TDD", formation.getTitre());
        assertEquals("Alice", formation.getProprietaire());
        assertEquals(LocalDateTime.of(2025, 3, 20, 14, 0), formation.getDateDebut());
        assertEquals(120, formation.getDuree());
        assertEquals("Bob", formation.getFormateur());
    }
}
