package src.model;

/**
 * Représente une commande pour ajouter une forme dans le jeu.
 * Cette commande permet d'ajouter une forme à l'un des dessins (référence ou reproduction).
 */
public class CommandeAjouterForme implements Commande {
    private Jeu jeu;
    private Forme forme;

    public CommandeAjouterForme(Jeu jeu, Forme forme) {
        this.jeu = jeu;
        this.forme = forme;
    }

    @Override
    public void executer() {
        jeu.ajouterForme(forme);
    }

    @Override
    public void annuler() {
        jeu.supprimerForme(forme);
    }
}