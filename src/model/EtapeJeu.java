package src.model;

/**
 * Interface représentant une étape du jeu.
 * Chaque étape du jeu a des comportements différents (observation, reproduction, etc.)
 * et doit implémenter la méthode 'executer' pour être lancée.
 */
public interface EtapeJeu {
    void executer(Runnable onEtapeFinie);
    void quitter();
}