package Evenements.Rdv;

import Evenements.*;

public class RdvPersonnel extends Evenements {
    public RdvPersonnel(TitreEvenement titre, Proprietaire proprietaire, DateEvenement dateDebut, DureeEvenement duree) {
        super(titre, proprietaire, dateDebut, duree);
    }

    @Override
    public String description() {
        return "Rendez-vous personnel : " + titre.valeur() + " de " + proprietaire.valeur() + ".";
    }

}


