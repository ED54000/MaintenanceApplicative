package Evenements.Reunions;

import Evenements.*;

public class Reunion extends Evenements {
    private final LieuEvenement lieu;
    private final Participants participants;

    public Reunion(TitreEvenement titre, Proprietaire proprietaire, DateEvenement dateDebut, DureeEvenement duree, LieuEvenement lieu, Participants participants) {
        super(titre, proprietaire, dateDebut, duree);
        this.lieu = lieu;
        this.participants = participants;
    }

    @Override
    public String description() {
        return "Réunion : " + titre.valeur() + " à " + lieu.valeur() + " avec " + String.join( ", ", (CharSequence) participants.liste());
    }
}
