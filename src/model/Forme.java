package src.model;

import src.util.AbstractModeleEcoutable;

/**
 * Classe abstraite représentant une forme géométrique dans le jeu.
 * Les formes peuvent être des cercles ou des rectangles, et chaque forme doit implémenter la méthode 'contientPoint'.
 */
public abstract class Forme extends AbstractModeleEcoutable {
    protected Point position;

    public Forme(int x, int y) {
        this.position = new Point(x, y);
    }

    public Point getPosition() {
        return position;
    }
    
    public int getX() {
        return position.getX();
    }
    
    public int getY() {
        return position.getY();
    }

    public void move(int dx, int dy) {
        this.position.deplacer(dx, dy);
        notifierEcouteurs();
    }

    public void setPosition(int x, int y) {
        this.position = new Point(x, y);
    }

    public abstract boolean contientPoint(int x, int y);
}