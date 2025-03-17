package Evenements;

import java.time.LocalDateTime;

public abstract class Evenements {
    protected final TitreEvenement titre;
    protected final Proprietaire proprietaire;
    protected final DateEvenement dateDebut;
    protected final DureeEvenement duree;

    public Evenements(TitreEvenement titre, Proprietaire proprietaire, DateEvenement dateDebut, DureeEvenement duree) {
        this.titre = titre;
        this.proprietaire = proprietaire;
        this.dateDebut = dateDebut;
        this.duree = duree;
    }

    public abstract String description();

    public String getTitre() {
        return titre.valeur();
    }

    public String getProprietaire() {
        return proprietaire.valeur();
    }

    public LocalDateTime getDateDebut() {
        return dateDebut.valeur();
    }

    public int getDuree() {
        return duree.minutes();
    }
}

