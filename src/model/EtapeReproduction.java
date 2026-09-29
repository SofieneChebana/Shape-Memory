package src.model;

import src.controller.ControleurDessin;
import src.view.PanneauDessin;

/**
 * Représente l'étape de reproduction du dessin.
 * Dans cette étape, le joueur utilise le contrôleur de dessin pour reproduire le dessin de référence.
 */
public class EtapeReproduction implements EtapeJeu {
    private PanneauDessin panneau;
    private ControleurDessin controleur;
    private Runnable onFinish;

    public EtapeReproduction(PanneauDessin panneau, ControleurDessin controleur, Runnable onFinish) {
        this.panneau = panneau;
        this.controleur = controleur;
        this.onFinish = onFinish;
    }

    @Override
    public void executer(Runnable onEtapeFinie) {
        System.out.println("Etape reproduction executer");
        panneau.ajouterControleur(controleur);
    }

    @Override
    public void quitter() {
        System.out.println("Etape reproduction quitter");
        panneau.supprimerControleur(controleur);
        onFinish.run();
    }
}