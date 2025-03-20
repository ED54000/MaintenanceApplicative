import static org.junit.jupiter.api.Assertions.*;

import Evenements.*;
import Evenements.Rdv.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.UUID;

public class CalendrierTest {
    private CalendarManager calendar;
    private EventId eventId;

    @BeforeEach
    void setUp() {
        calendar = new CalendarManager();

        // Création d'un événement avec un ID unique
        eventId = new EventId(UUID.randomUUID().toString());

        RdvPersonnel rdv = new RdvPersonnel(
                eventId,
                new TitreEvenement("RDV Test"),
                new Proprietaire("Alice"),
                new DateEvenement(LocalDateTime.now().plusDays(1)),
                new DureeEvenement(60)
        );

        calendar.ajouterEvent(rdv);
    }

    @Test
    void testSuppressionEvenementParId() {
        assertTrue(calendar.contientEvent(eventId), "L'événement doit être présent avant suppression");

        calendar.supprimerEvent(eventId);

        assertFalse(calendar.contientEvent(eventId), "L'événement ne doit plus être présent après suppression");
    }
}
