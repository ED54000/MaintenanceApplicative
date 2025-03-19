
import Evenements.Formation;
import Evenements.Proprietaire;
import Evenements.TitreEvenement;
import Evenements.DateEvenement;
import Evenements.DureeEvenement;
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
        String formateur = "Bob";

        Formation formation = new Formation(titre, proprietaire, date, duree, formateur);

        assertEquals("Java TDD", formation.titre().valeur());
        assertEquals("Alice", formation.proprietaire().valeur());
        assertEquals(LocalDateTime.of(2025, 3, 20, 14, 0), formation.dateDebut().valeur());
        assertEquals(120, formation.duree().valeur());
        assertEquals("Bob", formation.getFormateur());
    }
}
