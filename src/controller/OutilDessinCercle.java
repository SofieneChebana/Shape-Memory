package src.controller;

import java.awt.event.MouseEvent;

import src.model.Jeu;
import src.model.Cercle;
import src.model.CommandeAjouterForme;
import src.view.PanneauDessin;


/**
 * Outil de dessin permettant de dessiner des cercles sur le panneau de dessin.
 * Cet outil gère la création d'un cercle, ainsi que sa mise à jour lors des déplacements de la souris.
 */
public class OutilDessinCercle implements OutilDessin {

    private int xDepart, yDepart;
    private Cercle cercleTemp;

    /**
     * Gère l'événement de pression de la souris pour commencer à dessiner un cercle.
     * 
     * @param e l'événement de pression de la souris
     * @param panneau le panneau de dessin où l'action se produit
     * @param jeu l'objet Jeu associé à l'application
     */
    @Override
    public void mousePressed(MouseEvent e, PanneauDessin panneau, Jeu jeu) {
        xDepart = e.getX();
        yDepart = e.getY();
        cercleTemp = new Cercle(xDepart, yDepart, 0);
        panneau.setTemporaireForme(cercleTemp);
    }

    @Override
    public void mouseDragged(MouseEvent e, PanneauDessin panneau, Jeu jeu) {
        if(cercleTemp != null) {
            int rayon = (int)Math.sqrt(Math.pow(e.getX() - xDepart, 2) + Math.pow(e.getY() - yDepart, 2));
            cercleTemp.setRayon(rayon);
            panneau.repaint();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e, PanneauDessin panneau, Jeu jeu) {
        if(cercleTemp != null) {
            jeu.ajouterForme(cercleTemp);
            jeu.getUndoRedo().handle(new CommandeAjouterForme(jeu, cercleTemp));
            cercleTemp = null;
            panneau.setTemporaireForme(null);
        }
    }
}