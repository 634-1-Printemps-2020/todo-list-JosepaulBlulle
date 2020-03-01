package metier;

import domaine.Tache;
import domaine.TacheException;
import java.time.LocalDate;

public interface IGestionnaireTaches {
    void creerTache(int id, String createur, String description, LocalDate date, String statut, String resolution) throws TacheException;
    void annulerTache(String createur, Tache tache) throws TacheException;
    void replanifierTache(String createur, Tache tache, LocalDate date) throws TacheException;
    void consulterTaches(String createur, String statut, LocalDate date) throws TacheException;
    void consulterTaches(String createur, String statut) throws TacheException;
    void consulterTaches(String createur) throws TacheException;
}
