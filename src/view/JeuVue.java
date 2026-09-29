package src.view;

import javax.swing.*;

import src.controller.ControleurDessin;
import src.controller.ControleurJeu;
import src.model.Jeu;

/**
 * Vue représentant l'interface du jeu, avec le panneau de dessin et la barre d'outils.
 */
public class JeuVue extends JPanel{

    public JeuVue(Jeu jeu, UIFactory factory){
        PanneauDessin panneau = new PanneauDessin(jeu);
        ControleurDessin controleurDessin = new ControleurDessin(panneau, jeu);
        ControleurJeu controleurJeu = new ControleurJeu(jeu, panneau, controleurDessin);
        if(jeu.getDessinReference().getFormesListe().size() > 0) controleurJeu.setPreloaded(true);
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        add(panneau);
        add(new BarreOutilVue(controleurDessin, controleurJeu, factory));
    }
}