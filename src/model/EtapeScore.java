package src.model;

/**
 * Représente l'étape du calcul du score.
 * Cette étape calcule le score basé sur la similitude entre les dessins de référence et de reproduction.
 */
public class EtapeScore implements EtapeJeu {
    private Jeu jeu;
    private int score;

    public EtapeScore(Jeu jeu) {
        this.jeu = jeu;
    }

    @Override
    public void executer(Runnable onFinish) {
        System.out.println("EtapeScore exectuer");
        CalculScoreStrategy score = jeu.getCalculMethod();
        this.score = score.calculerScore(jeu);
        System.out.println(score.calculerScore(jeu));
        onFinish.run();
    }

    public int getScore() {
        return score;
    }

    @Override
    public void quitter() {
    }
}