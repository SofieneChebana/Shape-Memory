package src.model;

/**
 * Représente un rectangle dans le jeu.
 * Un rectangle a une position (en haut à gauche), une largeur et une hauteur.
 */
public class Rectangle extends Forme {
    private int largeur;
    private int hauteur;

    public Rectangle(int x, int y, int largeur, int hauteur) {
        super(x, y);
        this.largeur = largeur;
        this.hauteur = hauteur;
    }

    public Rectangle(Point position, int largeur, int hauteur) {
        super(position.getX(), position.getY());
        this.largeur = largeur;
        this.hauteur = hauteur;
    }

    public int getLargeur() {
        return largeur;
    }

    public void setLargeur(int largeur) {
        this.largeur = largeur;
    }

    public int getHauteur() {
        return hauteur;
    }

    public void setHauteur(int hauteur) {
        this.hauteur = hauteur;
    }

    @Override
    public boolean contientPoint(int px, int py) {
        int x = position.getX();
        int y = position.getY();
        return px >= x && px <= x+largeur && py >= y && py <= y+hauteur;
    }
}