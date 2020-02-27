package metier;

import java.time.LocalDate;
import domaine.Tache;
import domaine.TacheException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.JUnitException;
import static org.junit.jupiter.api.Assertions.*;

class GestionnaireTachesTest {

    @Test
    void constructeurNullNOK() {
        GestionnaireTaches listeTaches = new GestionnaireTaches();
        try {
            listeTaches.creerTache(1, null, null, null, null, null);
            Assertions.fail();

        } catch (TacheException ignored) { }
    }

    @Test
    void constructeurOpenNOK() {
        GestionnaireTaches listeTaches = new GestionnaireTaches();
        LocalDate dateAujourdhui = LocalDate.now();

        try {
            listeTaches.creerTache(1, "JP", "prendre rendez-vous chez le médecin", dateAujourdhui, "a", "ignored");
            Assertions.fail();

        } catch (TacheException | IllegalArgumentException ignored) { }
    }

    @Test
    void creerTacheDateOK() {
        GestionnaireTaches listeTaches = new GestionnaireTaches();
        LocalDate date = LocalDate.of(2021, 9, 1);

        try {
            listeTaches.creerTache(1, "JP", "prendre rendez-vous chez le médecin", date, "open", "ignored");

        } catch (TacheException e) { Assertions.fail(e); }
    }

    @Test
    void creerTacheDateNOK() {
        GestionnaireTaches listeTaches = new GestionnaireTaches();
        LocalDate dateTropTot = LocalDate.of(2020, 2, 1);

        try {
            listeTaches.creerTache(1, "JP", "préparer le contrôle 634", dateTropTot, "open", "ignored");
            Assertions.fail();

        } catch (TacheException ignored) { }
    }

    @Test
    void creerTacheNullNOK() {
        GestionnaireTaches listeTaches = new GestionnaireTaches();
        LocalDate dateTropTot = LocalDate.of(2021, 2, 1);

        try {
            listeTaches.creerTache(1, "JP", null, dateTropTot, "open", "ignored");
            Assertions.fail();

        } catch (TacheException ignored) { }
    }

    @Test
    void replanifierTacheOK() throws TacheException {
        GestionnaireTaches listeTaches = new GestionnaireTaches();
        LocalDate date = LocalDate.of(2021, 2, 1);
        LocalDate dateNew = LocalDate.of(2020, 4, 3);
        listeTaches.creerTache(1, "JP", "prendre rendez-vous chez le médecin", date, "open", "ignored");
        Tache tache = listeTaches.getTache(1, "JP");

        try {
            listeTaches.replanifierTache("JP", tache, dateNew);

        } catch (TacheException e) { Assertions.fail(e); }
    }

    @Test
    void replanifierTacheCreateurNOK() throws TacheException {
        GestionnaireTaches listeTaches = new GestionnaireTaches();
        LocalDate date = LocalDate.of(2021, 2, 1);
        LocalDate dateNew = LocalDate.of(2020, 4, 3);
        listeTaches.creerTache(1, "JP", "prendre rendez-vous chez le médecin", date, "open", "ignored");
        Tache tache = listeTaches.getTache(1, "JP");

        try {
            listeTaches.replanifierTache("J", tache, dateNew);
            Assertions.fail();

        } catch (TacheException ignored) { }
    }

    @Test
    void replanifierTacheTacheNOK() throws TacheException {
        GestionnaireTaches listeTaches = new GestionnaireTaches();
        LocalDate date = LocalDate.of(2021, 2, 1);
        LocalDate dateNew = LocalDate.of(2020, 4, 3);
        listeTaches.creerTache(1, "JP", "prendre rendez-vous chez le médecin", date, "open", "ignored");
        Tache tache = listeTaches.getTache(2, "JP");

        try {
            listeTaches.replanifierTache("JP", tache, dateNew);
            Assertions.fail();

        } catch (TacheException ignored) { }
    }

    @Test
    void replanifierTacheDateNOK() throws TacheException {
        GestionnaireTaches listeTaches = new GestionnaireTaches();
        LocalDate date = LocalDate.of(2021, 2, 1);
        LocalDate dateNew = LocalDate.of(2019, 4, 3);
        listeTaches.creerTache(1, "JP", "prendre rendez-vous chez le médecin", date, "open", "ignored");
        Tache tache = listeTaches.getTache(1, "JP");

        try {
            listeTaches.replanifierTache("JP", tache, dateNew);
            Assertions.fail();

        } catch (TacheException ignored) { }
    }

    @Test
    void consulterTachesNOK() throws TacheException {
        GestionnaireTaches listeTaches = new GestionnaireTaches();

        LocalDate dateAujourdhui = LocalDate.now();
        listeTaches.creerTache(1, "JP", "prendre rendez-vous chez le médecin", dateAujourdhui, "open", "ignored");

        try {
            listeTaches.consulterTaches("P");
            Assertions.fail();

        } catch (TacheException ignored) { }
    }

    @Test
    void consulterTachesOK() throws TacheException {
        GestionnaireTaches listeTaches = new GestionnaireTaches();

        LocalDate dateAujourdhui = LocalDate.now();
        listeTaches.creerTache(1, "JP", "prendre rendez-vous chez le médecin", dateAujourdhui, "open", "ignored");

        try {
            listeTaches.consulterTaches("JP");

        } catch (TacheException e) { Assertions.fail(e); }
    }

    @Test
    void annulerTacheOK() throws TacheException {

    }

    @Test
    void annulerTacheNOK() throws TacheException {

    }
}