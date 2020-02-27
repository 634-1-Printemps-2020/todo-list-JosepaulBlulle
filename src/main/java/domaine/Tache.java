package domaine;

import java.time.LocalDate;

public class Tache {
    private int id;
    private String description;
    private String createur;
    private LocalDate date;
    private Statut statut;
    private Resolution resolution;
    public enum Statut { open, closed, canceled; }
    public enum Resolution { done, ignored; }

    public Tache(int id, String createur, String description, LocalDate date, String statut, String resolution) {
        this.id = id;
        this.description = description;
        this.createur = createur;
        this.date = date;
        this.statut = Statut.valueOf(statut);
        this.resolution = Resolution.valueOf(resolution);
    }

    public String getDescription() {
        return description;
    }

    public String getCreateur() {
        return createur;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getStatut() {
        return statut.toString();
    }

    public String getResolution() {
        return resolution.toString();
    }

    public void annuler() {
        this.statut = Statut.canceled;
        this.resolution = Resolution.ignored;
    }

    public void replanifier(LocalDate date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        return (this.id == ((Tache) o).id && this.createur.equals(((Tache) o).createur));
    }

    @Override
    public String toString() {
        return "Tache{" +
                "description='" + description + '\'' +
                ", createur='" + createur + '\'' +
                ", date=" + date +
                ", statut=" + statut +
                ", resolution=" + resolution +
                '}';
    }
}
