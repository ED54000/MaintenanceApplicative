package Evenements;

import java.time.LocalDateTime;

public abstract class Evenements {
    protected final TitreEvenement titre;
    protected final Proprietaire proprietaire;
    protected final DateEvenement dateDebut;
    protected final DureeEvenement duree;
    protected final EventId id;

    public Evenements(TitreEvenement titre, EventId id, Proprietaire proprietaire, DateEvenement dateDebut, DureeEvenement duree) {
        this.titre = titre;
        this.id = id;
        this.proprietaire = proprietaire;
        this.dateDebut = dateDebut;
        this.duree = duree;
    }


    protected abstract String typeEvenement();

    public String description() {
        return typeEvenement() + " : " + titre.valeur() + " organisé par " + proprietaire.valeur() ;
    }

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

    public EventId getId() {
        return id;
    }
}

