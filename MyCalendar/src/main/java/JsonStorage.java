import Evenements.*;
import Evenements.EvenementPeriodique.FrequenceEvenement;
import Evenements.Formations.Formation;
import Evenements.Rdv.RdvPersonnel;
import Evenements.Reunions.LieuEvenement;
import Evenements.Reunions.Participants;
import Evenements.Reunions.Reunion;
import Evenements.EvenementPeriodique.EvenementPeriodique;
import Utilisateurs.Utilisateur;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class JsonStorage {

    public static void sauvegarderEvenements(List<Evenements> evenements, String cheminFichier) {
        JSONArray jsonArray = new JSONArray();
        for (Evenements e : evenements) {
            JSONObject obj = new JSONObject();
            obj.put("id", e.getId().valeur());
            obj.put("titre", e.getTitre());
            obj.put("proprietaire", e.getProprietaire());
            obj.put("date", e.convertirDate());
            obj.put("duree", e.getDuree());
            obj.put("description", e.description());
            obj.put("type", e.getClass().getSimpleName());
            if (e instanceof Reunion reunion) {
                obj.put("lieu", reunion.getLieu());
                obj.put("participants", new JSONArray(reunion.getParticipants()));
            } else if (e instanceof Formation formation) {
                obj.put("formateur", formation.getFormateur());
            } else if (e instanceof EvenementPeriodique evenementPeriodique) {
                obj.put("frequence", evenementPeriodique.getFrequence());
            }
            jsonArray.put(obj);
        }

        try (FileWriter file = new FileWriter(cheminFichier)) {
            file.write(jsonArray.toString(2)); // Format JSON indenté
        } catch (IOException ex) {
            throw new RuntimeException("Erreur lors de la sauvegarde", ex);
        }
    }

    public static List<Evenements> chargerEvenements(String cheminFichier) {
        List<Evenements> evenements = new ArrayList<>();

        try {
            String contenu = new String(Files.readAllBytes(Paths.get(cheminFichier)));
            JSONArray jsonArray = new JSONArray(contenu);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                EventId eventId = new EventId(obj.getString("id"));
                TitreEvenement titre = new TitreEvenement(obj.getString("titre"));
                Proprietaire proprietaire = new Proprietaire(obj.getString("proprietaire"));
                LocalDateTime date = LocalDateTime.parse(obj.getString("date"));
                DureeEvenement dureeEvenement = new DureeEvenement(obj.getInt("duree"));
                String type = obj.getString("type");

                Evenements evenement = switch (type) {
                    case "Reunion" -> new Reunion(titre, eventId, proprietaire, new DateEvenement(date), dureeEvenement,
                            new LieuEvenement(obj.getString("lieu")),
                            new Participants(obj.getJSONArray("participants").toList().stream()
                                    .map(p -> new Utilisateur((String) p)).toList()));
                    case "Formation" -> new Formation(titre, eventId, proprietaire, new DateEvenement(date), dureeEvenement,
                            obj.getString("formateur"));
                    case "EvenementPeriodique" -> new EvenementPeriodique(titre, eventId, proprietaire, new DateEvenement(date),
                            dureeEvenement, new FrequenceEvenement(obj.getInt("frequence")));
                    default -> new RdvPersonnel(eventId, titre, proprietaire, new DateEvenement(date), dureeEvenement);
                };

                evenements.add(evenement);
            }
        } catch (IOException ex) {
            System.err.println("Erreur lors du chargement, fichier introuvable !");
        }

        return evenements;
    }
}
