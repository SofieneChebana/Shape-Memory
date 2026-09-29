package src.model;

/**
 * Représente une commande pour déplacer une forme dans le jeu.
 * Cette commande permet de déplacer une forme à une nouvelle position.
 */
public class CommandeDeplacerForme implements Commande {
    private Forme forme;
    private int dx, dy;

    public CommandeDeplacerForme(Forme forme, int dx, int dy) {
        this.forme = forme;
        this.dx = dx;
        this.dy = dy;
    }

    @Override
    public void executer() {
        forme.move(dx, dy);
    }

    @Override
    public void annuler() {
        forme.move(-dx, -dy);
    }
}