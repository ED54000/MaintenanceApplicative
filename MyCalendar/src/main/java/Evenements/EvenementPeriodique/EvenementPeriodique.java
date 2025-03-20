package Evenements.EvenementPeriodique;

import Evenements.*;

public class EvenementPeriodique extends Evenements {
    private final FrequenceEvenement frequence;

    public EvenementPeriodique(TitreEvenement titre, Proprietaire proprietaire, DateEvenement dateDebut, DureeEvenement duree, FrequenceEvenement frequence) {
        super(titre, proprietaire, dateDebut, duree);
        this.frequence = frequence;
    }

    @Override
    public String description() {
        return "Événement périodique : " + titre.valeur() + " organisé par " + proprietaire.valeur() +
                " tous les " + frequence.jours() + " jours.";
    }


    public int getFrequence() {
        return frequence.jours();
    }
}

