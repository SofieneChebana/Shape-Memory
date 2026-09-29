package src.model;

import java.util.ArrayList;
import java.util.List;
import src.util.AbstractModeleEcoutable;

/**
 * Représente un dessin dans le jeu.
 * Un dessin est constitué d'une liste de formes (cercles, rectangles, etc.).
 * Cette classe permet d'ajouter et de supprimer des formes, et notifie les écouteurs lors des changements.
 */
public class Dessin extends AbstractModeleEcoutable{
    private List<Forme> formes;

    public Dessin() {
        this.formes = new ArrayList<>();
    }

    public void ajouterForme(Forme forme) {
        formes.add(forme);
        notifierEcouteurs();
    }

    public void supprimerForme(Forme forme) {
        formes.remove(forme);
        notifierEcouteurs();
    }

    public List<Forme> getFormesListe() {
        return formes;
    }

    public Forme getFormeFromPoint(int x, int y) {
        for (Forme forme : formes) {
            if (forme.contientPoint(x, y)) {
                return forme;
            }
        }
        return null;
    }
}