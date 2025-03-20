package Evenements.Rdv;

import Evenements.*;

public class RdvPersonnel extends Evenements {
    public RdvPersonnel(TitreEvenement titre, Proprietaire proprietaire, DateEvenement dateDebut, DureeEvenement duree) {
        super(titre, proprietaire, dateDebut, duree);
    }

    @Override
    protected String typeEvenement() {
        return "Rendez-vous Personnel";
    }

    @Override
    public String description() {
        return super.description() + ".";
    }

}


