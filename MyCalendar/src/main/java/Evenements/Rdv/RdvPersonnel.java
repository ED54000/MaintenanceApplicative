package Evenements.Rdv;

import Evenements.*;
import org.json.JSONObject;

public class RdvPersonnel extends Evenements {
    public RdvPersonnel(EventId eventId, TitreEvenement titre, Proprietaire proprietaire, DateEvenement dateDebut, DureeEvenement duree) {
        super(titre, eventId, proprietaire, dateDebut, duree);
    }

    @Override
    protected String typeEvenement() {
        return "Rendez-vous Personnel";
    }

    @Override
    public String description() {
        return super.description() + ".";
    }

    @Override
    public JSONObject toJson() {
        return super.baseJson();
    }

}


