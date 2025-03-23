package Evenements.Formations;

import Evenements.*;
import org.json.JSONObject;

public class Formation extends Evenements {
    private final String formateur;

    public Formation(TitreEvenement titre,EventId eventId, Proprietaire proprietaire, DateEvenement date, DureeEvenement duree, String formateur) {
        super(titre,eventId ,proprietaire, date, duree);
        this.formateur = formateur;
    }

    public String getFormateur() {
        return formateur;
    }

    @Override
    protected String typeEvenement() {
        return "Formation";
    }

    @Override
    public String description() {
        return super.description() + " animée par " + formateur;
    }

    @Override
    public JSONObject toJson() {
        JSONObject obj = super.baseJson();
        obj.put("formateur", this.formateur);
        return obj;
    }

}
