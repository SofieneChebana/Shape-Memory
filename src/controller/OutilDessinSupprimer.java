package src.controller;

import java.awt.event.MouseEvent;
import src.model.CommandeSupprimerForme;
import src.model.Forme;
import src.model.Jeu;
import src.view.PanneauDessin;

/**
 * Outil de dessin permettant de supprimer des formes existantes sur le panneau de dessin.
 * Cet outil gère la suppression d'une forme lorsque l'utilisateur clique dessus.
 */
public class OutilDessinSupprimer implements OutilDessin {

    @Override
    public void mousePressed(MouseEvent e, PanneauDessin panneau, Jeu jeu) {
        for(Forme f : jeu.getCurrentDessin().getFormesListe()) {
            if(f.contientPoint(e.getX(), e.getY())) {
                jeu.supprimerForme(f);
                jeu.getUndoRedo().handle(new CommandeSupprimerForme(jeu, f));
                break;
            }
        }
    }

     /**
     * Cette méthode ne fait rien pour l'outil de suppression, car le déplacement n'est pas applicable.
     * 
     * @param e l'événement de déplacement de la souris
     * @param panneau le panneau de dessin où l'action se produit
     * @param jeu l'objet Jeu associé à l'application
     */
    @Override
    public void mouseDragged(MouseEvent e, PanneauDessin panneau, Jeu jeu) {
    }

    @Override
    public void mouseReleased(MouseEvent e, PanneauDessin panneau, Jeu jeu) {
    }
}