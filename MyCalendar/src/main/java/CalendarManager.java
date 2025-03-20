import Evenements.DateEvenement;
import Evenements.EvenementPeriodique.EvenementPeriodique;
import Evenements.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
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

    public List<Evenements> afficherEvenementsDansPeriode(DateEvenement debut, DateEvenement fin) {
        return events.stream()
                .filter(e-> !e.getDateDebut().isBefore(debut.valeur()) &&
                        !e.getDateDebut().isAfter(fin.valeur()))
                .collect(Collectors.toList());
    }

    public List<Evenements> detecterConflits() {
        return events.stream()
                .filter(event1 -> events.stream()
                        .anyMatch(event2 -> !event1.equals(event2) && conflit(event1, event2)))
                .collect(Collectors.toList());
    }

    public void supprimerEvent(EventId id) {
        events.removeIf(event -> event.getId().equals(id));
    }

    public boolean contientEvent(EventId id) {
        return events.stream().anyMatch(event -> event.getId().equals(id));
    }
}