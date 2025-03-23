package Evenements;

import java.time.LocalDateTime;
import org.json.JSONArray;
import org.json.JSONObject;

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
    public abstract JSONObject toJson();

    protected JSONObject baseJson() {
        JSONObject obj = new JSONObject();
        obj.put("id", this.getId().valeur());
        obj.put("titre", this.getTitre());
        obj.put("proprietaire", this.getProprietaire());
        obj.put("date", this.convertirDate());
        obj.put("duree", this.getDuree());
        obj.put("description", this.description());
        obj.put("type", this.getClass().getSimpleName());
        return obj;
    }

    public String description() {
        return typeEvenement() + " : " + titre.valeur() + " organisé par " + proprietaire.valeur();
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

    public String convertirDate() {
        assert this.dateDebut != null;
        return this.dateDebut.valeur().toString();
    }
}

