import Evenements.Rdv.RdvPersonnel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.time.LocalDateTime;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import Evenements.*;


public class JsonStorageTest {
    private static final String TEST_FILE = "test_evenements.json";
    private Evenements evenement;

    @BeforeEach
    void setup() {
        evenement = new RdvPersonnel(
                new EventId("Test"),
                new TitreEvenement("Dentiste"),
                new Proprietaire("Alice"),
                new DateEvenement(LocalDateTime.of(2025, 3, 18, 10, 0)),
                new DureeEvenement(30)
        );
    }

    @Test
    void testSauvegardeEtChargement() {
        List<Evenements> evenements = List.of(evenement);

        // Sauvegarde dans un fichier JSON
        JsonStorage.sauvegarderEvenements(evenements, TEST_FILE);

        // Vérification que le fichier existe
        File fichier = new File(TEST_FILE);
        assertTrue(fichier.exists());

        // Chargement des événements depuis le fichier
        List<Evenements> evenementsCharges = JsonStorage.chargerEvenements(TEST_FILE);

        // Vérification que l'événement chargé est identique à celui sauvegardé
        assertEquals(1, evenementsCharges.size());
        assertEquals(evenement.getTitre(), evenementsCharges.getFirst().getTitre);
    }
}
