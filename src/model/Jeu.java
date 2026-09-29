package src.model;

import src.util.AbstractModeleEcoutable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Représente l'état du jeu, y compris les dessins de référence et de reproduction, les joueurs, le score et les étapes du jeu.
 * La classe gère également les mécanismes de changement de tour, la gestion des dessins et le calcul du score.
 */
public class Jeu extends AbstractModeleEcoutable{
    private Dessin dessinReference;
    private Dessin dessinReproduction;
    private int tour;
    private int chrono; //Nombre de secondes d'observation avant le début du jeu.
    private Random r = new Random(System.currentTimeMillis());
    private UndoRedo undoRedo;
    private boolean modeReproduction;
    private CalculScoreStrategy calculMethod;
    private int score;
    private List<Joueur> joueurs;
    private int joueurCourantIndex;
    private final int WIDTH = 800, HEIGTH = 600;

    public Jeu(CalculScoreStrategy calculMethod) {
        this.joueurs = new ArrayList<>();
        this.joueurs.add(new Joueur("Default Player")); //ajout d'un joueur par défaut
        this.joueurCourantIndex = 0;
        this.dessinReference = new Dessin();
        this.dessinReproduction = new Dessin();
        this.tour = 0;
        this.chrono = 10;
        this.undoRedo = new UndoRedo();
        this.modeReproduction = false;
        this.calculMethod = calculMethod;
    }

    public void setDessinReference(Dessin dessin) {
        this.dessinReference = dessin;
    }

    public CalculScoreStrategy getCalculMethod() {
        return this.calculMethod;
    }

    public void setCalculMethod(CalculScoreStrategy calculMethod) {
        this.calculMethod = calculMethod;
    }

    public int getWidth() {
        return this.WIDTH;
    }

    public int getHeight() {
        return this.HEIGTH;
    }

    public Dessin getCurrentDessin() {
        return (tour == 0 ? dessinReference : dessinReproduction);
    }

    public void ajouterForme(Forme forme) {
        this.getCurrentDessin().ajouterForme(forme);
        super.notifierEcouteurs();
    }

    public void supprimerForme(Forme forme) {
        this.getCurrentDessin().supprimerForme(forme);
        super.notifierEcouteurs();
    }

    public Dessin getDessinReference() {
        return dessinReference;
    }

    public Dessin getDessinReproduction() {
        return dessinReproduction;
    }

    public int getTour() {
        return tour;
    }

    public void passerTour() {
        this.tour++;
        super.notifierEcouteurs();
    }

    public void setModeReprodution(boolean actif) {
        this.modeReproduction = actif;
        super.notifierEcouteurs();
    }

    public boolean isModeReproduction() {
        return modeReproduction;
    }

    public void resetScore() {
        this.score = 0;
        super.notifierEcouteurs();
    }

    public int getScore() {
        return this.score;
    }
    
    public void resetChrono(int temps) {
        this.chrono = temps;
    }

    public void decrementerChrono() {
        this.chrono--;
        super.notifierEcouteurs();
    }

    public int getChrono(){
        return this.chrono;
    }

    public UndoRedo getUndoRedo() {
        return this.undoRedo;
    }

    public void genereNotif(){
        super.notifierEcouteurs();
    }

    public int calculerScore() {
        return 0;
    }

    public void afficherScore() {
        return;
    }

    /**
     * Génère un dessin de référence aléatoire avec des cercles et des rectangles.
     */
    public void genererDessinReference(){
    int n = r.nextInt(3)+3; // Nombre maximal de formes.
    for (int i = 0; i < n; i++) {
        if (r.nextInt(2) == 1) { // Créer un cercle
            int rayon = r.nextInt(41) + 10; // Rayon entre 10 et 50
            int xPosition = r.nextInt(WIDTH-100 - rayon * 2) + rayon;
            int yPosition = r.nextInt(HEIGTH-100 - rayon * 2) + rayon;
            this.ajouterForme(new Cercle(new Point(xPosition, yPosition), rayon));
        }
        else { // Créer un rectangle
            int largeur = r.nextInt(81) + 20; // Largeur entre 20 et 100
            int hauteur = r.nextInt(81) + 20; // Hauteur entre 20 et 100
            int xPosition = r.nextInt(WIDTH-100 - largeur);
            int yPosition = r.nextInt(HEIGTH-100 - hauteur);
            this.ajouterForme(new Rectangle(new Point(xPosition, yPosition), largeur, hauteur));
        }
    }
}
    public void resetDessins() {
        dessinReference.getFormesListe().clear();
        dessinReproduction.getFormesListe().clear();
        super.notifierEcouteurs();
    }

    public void resetTour(){
        this.tour=0;
        super.notifierEcouteurs();
    }

    public void ajouterJoueur(Joueur joueur) {
        this.joueurs.add(joueur);
        super.notifierEcouteurs();
    }
    public Joueur getJoueurCourant() {
        if (joueurs.isEmpty()) {
            throw new IllegalStateException("Aucun joueur dans la partie.");
        }
        return joueurs.get(joueurCourantIndex);
    }

    public void passerAuJoueurSuivant() {
        joueurCourantIndex = (joueurCourantIndex + 1) % joueurs.size();
        super.notifierEcouteurs();
    }

    public List<Joueur> getJoueurs() {
        return joueurs;
    }

    public void setSinglePlayerMode(boolean isSinglePlayer) {
        if (isSinglePlayer) {
            this.joueurs.clear();
            this.joueurs.add(new Joueur("Default Player"));
            this.joueurCourantIndex = 0;
        } else {
            this.joueurs.clear();
            this.joueurs.add(new Joueur("Joueur 1"));
            this.joueurs.add(new Joueur("Joueur 2"));
            this.joueurCourantIndex = 0;
        }
        super.notifierEcouteurs();
    }
    
    public boolean isSinglePlayer() {
        return this.joueurs.size() == 1;
    }
}