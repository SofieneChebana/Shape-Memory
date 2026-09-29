package src.controller;

import java.awt.event.MouseEvent;
import src.model.CommandeDeplacerForme;
import src.model.Forme;
import src.model.Jeu;
import src.view.PanneauDessin;

/**
 * Outil de dessin permettant de déplacer des formes existantes sur le panneau de dessin.
 * Cet outil permet de sélectionner une forme et de la déplacer en fonction du mouvement de la souris.
 */
public class OutilDessinMouvement implements OutilDessin {

    private Forme selectedForme;
    private int xCur, yCur;
    private int xDepart, yDepart;

    @Override
    public void mousePressed(MouseEvent e, PanneauDessin panneau, Jeu jeu) {
        for(Forme f : jeu.getCurrentDessin().getFormesListe()) {
            if(f.contientPoint(e.getX(), e.getY())) {
                selectedForme = f;
                xDepart = e.getX();
                yDepart = e.getY();
                xCur = e.getX();
                yCur = e.getY();
                return;
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent e, PanneauDessin panneau, Jeu jeu) {
        if(selectedForme != null) {
            selectedForme.move(e.getX() - xCur, e.getY() - yCur);
            xCur = e.getX();
            yCur = e.getY();
            panneau.repaint();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e, PanneauDessin panneau, Jeu jeu) {
        jeu.getUndoRedo().handle(new CommandeDeplacerForme(selectedForme, e.getX() - xDepart, e.getY() - yDepart));
        selectedForme = null;
    }
}