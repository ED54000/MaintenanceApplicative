package Evenements.Formations;

import Evenements.*;


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


}
