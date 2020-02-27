package metier;

import domaine.Tache;
import domaine.TacheException;

import java.time.LocalDate;
import java.util.*;

public class GestionnaireTaches {
    private Map<String, Set<Tache>> listeTaches;

    public GestionnaireTaches(Map<String, Set<Tache>> listeTaches) {
        this.listeTaches = listeTaches;
    }

    public GestionnaireTaches() {
        this.listeTaches = new HashMap<>();
    }

    public void creerTache(int id, String createur, String description, LocalDate date, String statut, String resolution) throws TacheException {
        testNull(createur, description, date, statut, resolution);
        if (resolution.equals("done")) { testResolutionDoneDate(date); }
        else { testDate(date); }

        Tache tache = new Tache(id, createur, description, date, statut, resolution);
        this.listeTaches.putIfAbsent(createur, new HashSet<>());
        this.listeTaches.get(createur).add(tache);
    }

    public void replanifierTache(String createur, Tache tache, LocalDate date) throws TacheException {
        testNull("", "", date, "", "");
        testCreateurExiste(createur);
        testCreateurContainsTache(createur, tache);
        testStatutNoOpen(tache.getStatut());
        if (tache.getResolution().equals("done")) { testResolutionDoneDate(date); }
        else { testDate(date); }

        tache.replanifier(date);
    }

    public void annulerTache(String createur, Tache tache) throws TacheException {
        testCreateurExiste(createur);
        testCreateurContainsTache(createur, tache);
        tache.annuler();
    }

    public void consulterTaches(String createur, String statut, LocalDate date) throws TacheException {
        testCreateurExiste(createur);

        if (statut == null && date == null) {
            for (Tache tache : listeTaches.get(createur)) { System.out.println(tache); }

        } else {

            for (Tache tache: listeTaches.get(createur)) {
                if (tache.getStatut().equals(statut) && tache.getDate().equals(date)) {
                    System.out.println(tache);
                }
            }
        }
    }

    public void consulterTaches(String createur, String statut) throws TacheException { consulterTaches(createur, statut, null); }

    public void consulterTaches(String createur) throws TacheException { consulterTaches(createur, null, null); }

    // Pas de valeurs null
    private void testNull(String createur, String description, LocalDate date, String statut, String resolution) throws TacheException {
        if (createur == null || description == null || date == null || statut == null || resolution == null) {
            throw new TacheException("Des valeurs sont null : createur = " + createur + ", description = " + description + ", date = " + date + ", statut = " + statut + ", resolution = " + resolution);
        }
    }

    // Si résolution == "ignored", la date doit être = ou après aujourd'hui
    private void testDate(LocalDate date) throws TacheException {
        if (LocalDate.now().isAfter(date)) {
            throw new TacheException("La date entrée est une date passée : date = " + date + ", date auj = " + LocalDate.now().toString());
        }
    }

    // Le créateur doit exister (sauf creerTache ou l'on le crée)
    private void testCreateurExiste(String createur) throws TacheException {
        if (this.listeTaches.get(createur) == null) {
            throw new TacheException("Le créateur rentré n'existe pas : " + createur);
        }
    }

    // Le créateur doit contenir la tache que l'on souahite changer ou annuler
    private void testCreateurContainsTache(String createur, Tache tache) throws TacheException {
        if (!this.listeTaches.get(createur).contains(tache)) {
            throw new TacheException("Le créateur ne contient pas la tâche rentrée : créateur = " +  createur + ", tache = " + tache);
        }
    }

    // La date ne peux pas être modifiée si le statut n'est pas sur "open"
    private void testStatutNoOpen(String statut) throws TacheException {
        if (!statut.equals("open")) {
            throw new TacheException("Le statut n'est pas ouvert : statut = " + statut);
        }
    }

    // La résolution == "done", la date doit être = ou avant aujourd'hui
    private void testResolutionDoneDate(LocalDate date) throws  TacheException {
        if (date.isAfter(LocalDate.now())) {
            throw new TacheException("Le statut Done empêche la date d'être changé à un jour non passé : date = " + date);
        }
    }

    public Tache getTache(int id, String createur) {
        Tache recherche = new Tache(id, createur, "", LocalDate.now(), "open", "done");

        for (Tache t: listeTaches.get(createur)) {
            if (t.equals(recherche)) { return t; }
        }
        return null;
    }
}