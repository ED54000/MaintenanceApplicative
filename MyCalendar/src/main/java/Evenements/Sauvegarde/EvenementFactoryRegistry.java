package Evenements.Sauvegarde;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import Evenements.*;
import Evenements.EvenementPeriodique.*;
import Evenements.Formations.Formation;
import Evenements.Rdv.RdvPersonnel;
import Evenements.Reunions.*;
import Utilisateurs.Utilisateur;
import Utilisateurs.Utilisateurs;
import org.json.JSONObject;


public class EvenementFactoryRegistry {
    private static final Map<String, EvenementFactory> FACTORY_MAP = new HashMap<>();

    static {



        FACTORY_MAP.put("Reunion", obj -> new Reunion(
                new TitreEvenement(obj.getString("titre")),
                new EventId(obj.getString("id")),
                new Proprietaire(obj.getString("proprietaire")),
                new DateEvenement(LocalDateTime.parse(obj.getString("date"))),
                new DureeEvenement(obj.getInt("duree")),
                new LieuEvenement(obj.getString("lieu")),
                new Participants(obj.getJSONArray("participants").toList().stream()
                        .map(p -> {
                            Map<String, String> participantMap = (Map<String, String>) p; // Caste en Map
                            return new Utilisateur(participantMap.get("nom")); // Récupère le nom et crée un Utilisateur
                        })
                        .toList())
        ));

        FACTORY_MAP.put("Formation", obj -> new Formation(
                new TitreEvenement(obj.getString("titre")),
                new EventId(obj.getString("id")),
                new Proprietaire(obj.getString("proprietaire")),
                new DateEvenement(LocalDateTime.parse(obj.getString("date"))),
                new DureeEvenement(obj.getInt("duree")),
                obj.getString("formateur")
        ));

        FACTORY_MAP.put("EvenementPeriodique", obj -> new EvenementPeriodique(
                new TitreEvenement(obj.getString("titre")),
                new EventId(obj.getString("id")),
                new Proprietaire(obj.getString("proprietaire")),
                new DateEvenement(LocalDateTime.parse(obj.getString("date"))),
                new DureeEvenement(obj.getInt("duree")),
                new FrequenceEvenement(obj.getInt("frequence"))
        ));

        FACTORY_MAP.put("RdvPersonnel", obj -> new RdvPersonnel(
                new EventId(obj.getString("id")),
                new TitreEvenement(obj.getString("titre")),
                new Proprietaire(obj.getString("proprietaire")),
                new DateEvenement(LocalDateTime.parse(obj.getString("date"))),
                new DureeEvenement(obj.getInt("duree"))
        ));
    }

    public static Evenements createEvenement(JSONObject obj) {
        String type = obj.getString("type");
        return FACTORY_MAP.getOrDefault(type, o -> {
            throw new IllegalArgumentException("Type inconnu: " + type);
        }).create(obj);
    }
}
