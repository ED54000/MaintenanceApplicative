import Evenements.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

class TestSportEvenement {

    @Test
    void testCreationEvenementSportif() {
        TitreEvenement titre = new TitreEvenement("Tournoi de Tennis");
        Proprietaire proprietaire = new Proprietaire("Bob");
        DateEvenement date = new DateEvenement(LocalDateTime.of(2025, 6, 15, 10, 0));
        DureeEvenement duree = new DureeEvenement(180);
        EvenementSportif tournoi = new EvenementSportif(titre, proprietaire, date, duree, "Tennis");

        assertEquals("Tournoi de Tennis", tournoi.titre().valeur());
        assertEquals("Bob", tournoi.proprietaire().valeur());
        assertEquals(LocalDateTime.of(2025, 6, 15, 10, 0), tournoi.date().valeur());
        assertEquals(180, tournoi.duree().valeur());
        assertEquals("Tennis", tournoi.getSport());
    }
}
