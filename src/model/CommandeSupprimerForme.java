package src.model;

/**
 * Représente une commande pour supprimer une forme dans le jeu.
 * Cette commande permet de supprimer une forme d'un dessin (référence ou reproduction).
 */
public class CommandeSupprimerForme implements Commande {
    private Jeu jeu;
    private Forme forme;

    public CommandeSupprimerForme(Jeu jeu, Forme forme) {
        this.jeu = jeu;
        this.forme = forme;
    }

    @Override
    public void executer() {
        jeu.supprimerForme(forme);
    }

    @Override
    public void annuler() {
        jeu.ajouterForme(forme);
    }   
}