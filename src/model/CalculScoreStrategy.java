package src.model;

/**
 * Interface qui définit la stratégie de calcul du score pour le jeu.
 * Les implémentations de cette interface permettent de calculer le score de manière différente selon la stratégie choisie.
 */
public interface CalculScoreStrategy {
    int calculerScore(Jeu jeu);
}