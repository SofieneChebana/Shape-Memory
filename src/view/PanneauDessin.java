package src.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import src.model.Cercle;
import src.model.Forme;
import src.model.Rectangle;
import src.model.Jeu;
import src.util.*;

/**
 * Panneau de dessin où les formes sont affichées et où l'utilisateur peut interagir pour ajouter, déplacer ou supprimer des formes.
 */
public class PanneauDessin extends JPanel implements EcouteurModele {
    private Jeu jeu;
    private Forme temporaireForme;

    public PanneauDessin(Jeu jeu) {
        this.setPreferredSize(new Dimension(jeu.getWidth(), jeu.getHeight()));
        this.setBackground(Color.WHITE);
        this.jeu = jeu;
        this.temporaireForme = null;
        jeu.ajoutEcouteur(this);
    }

    public void ajouterControleur(MouseAdapter controleur) {
        addMouseListener(controleur);
        addMouseMotionListener(controleur);
    }

    public void supprimerControleur(MouseAdapter controleur) {
        removeMouseListener(controleur);
        removeMouseMotionListener(controleur);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.ORANGE);
        for(Forme f : jeu.getCurrentDessin().getFormesListe()) {
            drawForme(g, f);
        }
        if(this.temporaireForme != null) {
            g.setColor(new Color((float)0.4, (float)0, (float)0, (float)0.3));
            drawForme(g, temporaireForme);
        }
        g.setColor(Color.BLACK);
        g.drawLine(getWidth()-1, 0, getWidth()-1, getHeight());
    }

    private void drawForme(Graphics g, Forme f) {
        //g.setColor(new Color(r.nextFloat(), r.nextFloat() ,r.nextFloat()));
        if (f instanceof Cercle) {
            Cercle cercle = (Cercle) f;
            int x = cercle.getPosition().getX()-cercle.getRayon();
            int y = cercle.getPosition().getY()-cercle.getRayon();
            g.fillOval(x, y, 2*cercle.getRayon(), 2*cercle.getRayon());
        } else if (f instanceof Rectangle) {
            Rectangle rectangle = (Rectangle) f;
            int x = rectangle.getPosition().getX();
            int y = rectangle.getPosition().getY();
            g.fillRect(x, y, rectangle.getLargeur(), rectangle.getHauteur());
        }
    }

    public Forme getTemporaireForme() {
        return this.temporaireForme;
    }

    public void setTemporaireForme(Forme forme) {
        this.temporaireForme = forme;
    }

     /**
     * Mise à jour du panneau de dessin lorsque l'état du jeu change.
     * 
     * @param source l'objet source du changement d'état
     */
    @Override
    public void modeleMisAJour(Object source) {
        repaint();
    }
}