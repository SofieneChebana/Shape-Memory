package src.controller;

import java.awt.event.MouseEvent;
import src.model.Jeu;
import src.view.PanneauDessin;

/**
 * Interface représentant un outil de dessin.
 * Un outil de dessin permet de réaliser des actions sur le panneau de dessin, telles que dessiner, déplacer, ou supprimer des formes.
 */
public interface OutilDessin {
    void mousePressed(MouseEvent e, PanneauDessin panneau, Jeu jeu);
    void mouseDragged(MouseEvent e, PanneauDessin panneau, Jeu jeu);
    void mouseReleased(MouseEvent e, PanneauDessin panneau, Jeu jeu);
}
