package Evenements.Sauvegarde;

import Evenements.Evenements;
import org.json.JSONObject;

@FunctionalInterface
public interface EvenementFactory {
    Evenements create(JSONObject obj);
}
