package Evenements.Reunions;

import Evenements.*;
import Utilisateurs.Utilisateur;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;
import java.util.stream.Collectors;

public class Reunion extends Evenements {
    private final LieuEvenement lieu;
    private final Participants participants;

    public Reunion(TitreEvenement titre, EventId eventId, Proprietaire proprietaire, DateEvenement dateDebut, DureeEvenement duree, LieuEvenement lieu, Participants participants) {
        super(titre, eventId, proprietaire, dateDebut, duree);
        this.lieu = lieu;
        this.participants = participants;
    }

    @Override
    protected String typeEvenement() {
        return "Réunion";
    }

    @Override
    public String description() {
        return super.description() + " à " + lieu.valeur() +
                " avec " + participants.liste().stream().map(Utilisateur::toString).collect(Collectors.joining(", ")) + ".";
    }

    public String getLieu() {
        return lieu.valeur();
    }

    public List<Utilisateur> getParticipants() {
        return participants.liste();
    }

    @Override
    public JSONObject toJson() {
        JSONObject obj = super.baseJson();
        obj.put("lieu", this.getLieu());
        obj.put("participants", new JSONArray(this.getParticipants()));
        return obj;
    }

}
