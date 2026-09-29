package src.controller;

import src.view.PanneauDessin;
import src.model.Jeu;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Le contrôleur de dessin est responsable de gérer les événements liés aux outils de dessin (comme les cercles, rectangles,etc.)
 * et d'interagir avec le jeu pour effectuer des actions de dessin ou de modification dans la vue.
 */
public class ControleurDessin extends MouseAdapter{
    private PanneauDessin panneau;
    private OutilDessin outilDessin;
    private Jeu jeu;

    public ControleurDessin(PanneauDessin panneau, Jeu jeu) {
        this.panneau = panneau;
        this.jeu = jeu;
        this.outilDessin = null;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(outilDessin != null) outilDessin.mousePressed(e, panneau, jeu);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(outilDessin != null) outilDessin.mouseReleased(e, panneau, jeu);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(outilDessin != null) {
            outilDessin.mouseDragged(e, panneau, jeu);
        }
    }

    /**
     * Annule la dernière action de dessin en utilisant le mécanisme Undo/Redo du jeu.
     */
    public void undo() {
        jeu.getUndoRedo().undo();
    }

    /**
     * Refait la dernière action annulée en utilisant le mécanisme Undo/Redo du jeu.
     */
    public void redo() {
        jeu.getUndoRedo().redo();
    }

    public void setOutilDessin(OutilDessin outil) {
        this.outilDessin = outil;
        System.out.println("Outil dessin = " + outilDessin);
    }
}