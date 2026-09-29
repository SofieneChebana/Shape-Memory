package src.model;

import src.controller.ControleurDessin;
import src.view.PanneauDessin;

/**
 * Représente l'étape de production du dessin de référence.
 * Dans cette étape, le contrôleur de dessin est activé pour permettre la création du dessin de référence.
 */
public class EtapeProductionReference implements EtapeJeu {
    private PanneauDessin panneau;
    private ControleurDessin controleur;

    public EtapeProductionReference(PanneauDessin panneau, ControleurDessin controleur, Runnable onFinish) {
        this.panneau = panneau;
        this.controleur = controleur;
    }

    @Override
    public void executer(Runnable onEtapeFinie) {
        System.out.println("Etape production reference executer");
        panneau.ajouterControleur(controleur);
    }

    @Override
    public void quitter() {
    }
}