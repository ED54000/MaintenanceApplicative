package Utilisateurs;

import java.util.*;

public class Utilisateurs {

    List<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
    int nbUtilisateurs = 0;
    private final Map<String, String> comptesFixes = Map.of(
            "Roger", "Chat",
            "Pierre", "KiRouhl"
    );

    public void addUtilisateur(Utilisateur utilisateur) {
        utilisateurs.add(utilisateur);
        nbUtilisateurs++;
    }

    public void addParticipants(Scanner scanner) {
        System.out.println("Ajouter des participants ? (tapez 'fin' pour arrêter)");
            String participant = scanner.nextLine();
            while (!participant.equals("fin")) {
                addUtilisateur(new Utilisateur(participant));
                System.out.println("Ajouter des participants ? (tapez 'fin' pour arrêter)");
                participant = scanner.nextLine();
        }
    }

    public List<Utilisateur> getUtilisateurs() {
        return utilisateurs;
    }

    public int getNbUtilisateurs() {
        return nbUtilisateurs;
    }

    public Utilisateur authentifier(String nom, String motDePasse) {
        return Optional.ofNullable(comptesFixes.get(nom))
                .filter(mp -> mp.equals(motDePasse))
                .map(mp -> new Utilisateur(nom, mp))
                .orElseGet(() -> utilisateurs.stream()
                        .filter(u -> u.getNom().equals(nom) && u.getMotDePasse().equals(motDePasse))
                        .findFirst()
                        .orElseGet(() -> {
                            System.out.println("Utilisateur ou mot de passe incorrect.");
                            return null;
                        }));
    }

    public Utilisateur ajouterUtilisateur(String nom, String motDePasse) {
        Utilisateur newUser = new Utilisateur(nom, motDePasse);
        utilisateurs.add(newUser);
        System.out.println("Compte créé avec succès !");
        return newUser;
    }
}
