package Evenements;

import java.util.UUID;

public record EventId(String valeur) {
    public EventId {
        if (valeur == null || valeur.isBlank()) {
            throw new IllegalArgumentException("L'identifiant d'événement ne peut pas être vide.");
        }
    }

    public static EventId generer() {
        return new EventId(UUID.randomUUID().toString());
    }
}
