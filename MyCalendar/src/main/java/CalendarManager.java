import Evenements.DateEvenement;
import Evenements.EvenementPeriodique.EvenementPeriodique;
import Evenements.Evenements;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

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
            for (LocalDateTime date :
                    e instanceof EvenementPeriodique periodic
                            ? Stream.iterate(periodic.getDateDebut(), d -> d.plusDays(periodic.getFrequence()))
                            .limit((int) (ChronoUnit.DAYS.between(periodic.getDateDebut(), fin.valeur()) / periodic.getFrequence()) + 1)
                            .toList()
                            : List.of(e.getDateDebut())) {

                result.addAll(Stream.of(e)
                        .filter(event -> !date.isBefore(debut.valeur()) && !date.isAfter(fin.valeur()))
                        .toList());
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