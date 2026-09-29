package src.model;

/**
 * Interface représentant une commande générique dans le jeu.
 * Les commandes peuvent être exécutées ou annulées.
 */
public interface Commande {
    void executer();
    void annuler();
}
