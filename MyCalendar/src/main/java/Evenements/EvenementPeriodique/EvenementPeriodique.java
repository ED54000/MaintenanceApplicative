package Evenements.EvenementPeriodique;

import Evenements.*;
import org.json.JSONObject;

public class EvenementPeriodique extends Evenements {
    private final FrequenceEvenement frequence;

    public EvenementPeriodique(TitreEvenement titre,EventId eventId ,Proprietaire proprietaire, DateEvenement dateDebut, DureeEvenement duree, FrequenceEvenement frequence) {
        super(titre,eventId , proprietaire, dateDebut, duree);
        this.frequence = frequence;
    }

    @Override
    protected String typeEvenement() {
        return "Événement périodique";
    }

    @Override
    public String description() {
        return super.description() +" tous les " + frequence.jours() + " jours.";
    }


    public int getFrequence() {
        return frequence.jours();
    }

    @Override
    public JSONObject toJson() {
        JSONObject obj = super.baseJson();
        obj.put("frequence", this.frequence);
        return obj;
    }
}

