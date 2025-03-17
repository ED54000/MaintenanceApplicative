import Evenements.*;
import Evenements.EvenementPeriodique.EvenementPeriodique;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CalendarManager {
    public List<Evenements> events;

    public CalendarManager() {
        this.events = new ArrayList<>();
    }

    public void ajouterEvent(Evenements evenements) {
        events.add(evenements);
    }

    public List<Evenements> eventsDansPeriode(DateEvenement debut, DateEvenement fin) {
        List<Evenements> result = new ArrayList<>();
        for (Evenements e : events) {
            if (e instanceof EvenementPeriodique periodic) {
                LocalDateTime temp = periodic.getDateDebut();
                while (temp.isBefore(fin.valeur())) {
                    if (!temp.isBefore(debut.valeur())) {
                        result.add(e);
                        break;
                    }
                    temp = temp.plusDays(periodic.getFrequence());
                }
            } else if (!e.getDateDebut().isBefore(debut.valeur()) && !e.getDateDebut().isAfter(fin.valeur())) {
                result.add(e);
            }
        }
        return result;
    }

    public boolean conflit(Evenements e1, Evenements e2) {
        LocalDateTime fin1 = e1.getDateDebut().plusMinutes(e1.getDuree());
        LocalDateTime fin2 = e2.getDateDebut().plusMinutes(e2.getDuree());

        return e1.getDateDebut().isBefore(fin2) && fin1.isAfter(e2.getDateDebut());
    }

    public void afficherEvenements() {
        for (Evenements e : events) {
            System.out.println(e.description());
        }
    }
}