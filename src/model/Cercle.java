package src.model;

/**
 * Représente un cercle dans le jeu.
 * Un cercle est défini par sa position (x, y) et son rayon.
 */
public class Cercle extends Forme {
    private int rayon;

    public Cercle(int x, int y, int rayon) {
        super(x, y);
        this.rayon = rayon;
    }

    public Cercle(Point position, int rayon) {
        super(position.getX(), position.getY());
        this.rayon = rayon;
    }

    public int getRayon() {
        return rayon;
    }

    public void setRayon(int rayon) {
        this.rayon = rayon;
    }

    @Override
    public boolean contientPoint(int px, int py) {
        int dx = px-position.getX();
        int dy = py-position.getY();
        return (dx*dx+dy*dy) <= (rayon*rayon);
    }
}