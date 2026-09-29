package src.controller;

import java.awt.event.MouseEvent;

import src.model.CommandeAjouterForme;
import src.model.Jeu;
import src.model.Point;
import src.model.Rectangle;
import src.view.PanneauDessin;

/**
 * Outil de dessin permettant de dessiner des rectangles sur le panneau de dessin.
 * Cet outil gère la création d'un rectangle et son redimensionnement en fonction du mouvement de la souris.
 */
public class OutilDessinRectangle implements OutilDessin {
    
    private int xDepart, yDepart;
    private Rectangle rectangleTemp;

    @Override
    public void mousePressed(MouseEvent e, PanneauDessin panneau, Jeu jeu) {
        xDepart = e.getX();
        yDepart = e.getY();
        rectangleTemp = new Rectangle(new Point(xDepart, yDepart), 0, 0);
        panneau.setTemporaireForme(rectangleTemp);
    }

    @Override
    public void mouseDragged(MouseEvent e, PanneauDessin panneau, Jeu jeu) {
        if(rectangleTemp != null) {
            int largeur = Math.abs(e.getX() - xDepart);
            int hauteur = Math.abs(e.getY() - yDepart);
            int xMin = Math.min(e.getX(), xDepart);
            int yMin = Math.min(e.getY(), yDepart);
            rectangleTemp.setPosition(xMin, yMin);
            rectangleTemp.setLargeur(largeur);
            rectangleTemp.setHauteur(hauteur);
            panneau.repaint();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e, PanneauDessin panneau, Jeu jeu) {
        if(rectangleTemp != null) {
            jeu.ajouterForme(rectangleTemp);
            jeu.getUndoRedo().handle(new CommandeAjouterForme(jeu, rectangleTemp));
            panneau.setTemporaireForme(null);
            panneau.repaint();
        }
    }
}