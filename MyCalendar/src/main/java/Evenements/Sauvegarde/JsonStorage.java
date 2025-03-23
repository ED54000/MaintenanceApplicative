package Evenements.Sauvegarde;

import Evenements.Evenements;
import Management.CalendarManager;
import org.json.JSONArray;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class JsonStorage {

    public static void sauvegarderEvenements(List<Evenements> evenements, String cheminFichier) {
        JSONArray jsonArray = new JSONArray();
        for (Evenements e : evenements) {
            jsonArray.put(e.toJson());
        }
        try (FileWriter file = new FileWriter(cheminFichier)) {
            file.write(jsonArray.toString(2));
            System.out.println("Données sauvegardées");
        } catch (IOException ex) {
            throw new RuntimeException("Erreur lors de la sauvegarde", ex);
        }
    }


    public static  List<Evenements> chargerEvenements(String cheminFichier, CalendarManager calendarManager) {
        List<Evenements> evenements = new ArrayList<>();

        try {
            String contenu = new String(Files.readAllBytes(Paths.get(cheminFichier)));
            JSONArray jsonArray = new JSONArray(contenu);

            for (int i = 0; i < jsonArray.length(); i++) {
                evenements.add(EvenementFactoryRegistry.createEvenement(jsonArray.getJSONObject(i)));
            }
        } catch (IOException ex) {
            System.err.println("Erreur lors du chargement, fichier introuvable !");
        }

        calendarManager.events =  evenements;
        System.out.println("Données chargées");
        return evenements;
    }

}
