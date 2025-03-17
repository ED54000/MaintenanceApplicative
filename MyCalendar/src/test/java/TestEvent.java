import Evenements.*;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.assertTrue;


class CalendarManagerTest {
    @Test
    void testAjoutEvenementSportif() {
        CalendarManager calendar = new CalendarManager();
        TitreEvenement titre = new TitreEvenement("Match de foot");
        Proprietaire proprietaire = new Proprietaire("Alice");
        DateEvenement date = new DateEvenement(LocalDateTime.of(2025, 5, 10, 15, 0));
        DureeEvenement duree = new DureeEvenement(120);
        EvenementSportif match = new EvenementSportif(titre, proprietaire, date, duree, "Football");

        calendar.ajouterEvent(match);

        assertTrue(calendar.events().contains(match), "L'événement sportif doit être ajouté au calendrier");
    }
}
