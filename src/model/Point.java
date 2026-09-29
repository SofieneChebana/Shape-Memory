package src.model;

/**
 * Représente un point 2D dans l'espace du jeu.
 * Chaque point a une position en x et en y.
 */
public class Point {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() { 
        return x; 
    }

    public int getY() { 
        return y; 
    }

    public void setX(int x) { 
        this.x = x; 
    }

    public void setY(int y) { 
        this.y = y; 
    }

    public void deplacer(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }
}