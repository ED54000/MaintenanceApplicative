package Utilisateurs;

import java.util.*;

public class Utilisateurs {

    List<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
    int nbUtilisateurs = 0;


    public void addUtilisateur(Utilisateur utilisateur) {
        utilisateurs.add(utilisateur);
        nbUtilisateurs++;
    }

    public void addParticipants(){
        System.out.println("Ajouter des participants ? (tapez 'fin' pour arrêter)");
        try (Scanner scanner = new Scanner(System.in)) {
            String participant = scanner.nextLine();
            while (!participant.equals("fin")) {
                addUtilisateur(new Utilisateur(participant));
                System.out.println("Ajouter des participants ? (tapez 'fin' pour arrêter)");
                participant = scanner.nextLine();
            }
        }
    }

    public List<Utilisateur> getUtilisateurs() {
        return utilisateurs;
    }

}
