package src.controller;

import src.model.EtapeJeu;
import src.model.EtapeObservation;
import src.model.EtapeObservationDeuxJoueurs;
import src.model.EtapeProductionReference;
import src.model.EtapeReproduction;
import src.model.EtapeScore;
import src.model.Jeu;
import src.model.Joueur;
import src.util.AbstractModeleEcoutable;
import src.view.PanneauDessin;

/**
 * Le contrôleur de jeu est responsable de la gestion des étapes du jeu. Il orchestre les différentes phases du jeu 
 * comme l'observation, la reproduction, et la validation du score, en fonction du mode de jeu (1 ou 2 joueurs).
 */
public class ControleurJeu extends AbstractModeleEcoutable {
    private Jeu jeu;
    private PanneauDessin panneau;
    private ControleurDessin controleur;
    private EtapeJeu etapeCourante;
    private boolean preloaded;
    private int tours=0;

    public ControleurJeu(Jeu jeu, PanneauDessin panneau, ControleurDessin controleur){
        this.jeu = jeu;
        this.panneau = panneau;
        this.controleur = controleur;
        this.preloaded = false;
    }

    /**
     * Définir si le jeu est préchargé (si un dessin a été chargé au préalable).
     * 
     * @param preloaded état du jeu (préchargé ou non)
     */
    public void setPreloaded(boolean preloaded) {
        this.preloaded = preloaded;
    }

    public Jeu getJeu(){
        return this.jeu;
    }

    /**
     * Démarre une nouvelle partie en réinitialisant les dessins, le score, le chrono et le tour.
     * Il passe à l'étape d'observation, puis à la reproduction selon le mode de jeu (1 ou 2 joueurs).
     */
    public void demarrer() {
        if(!this.preloaded)
            jeu.resetDessins();
        jeu.resetScore();
        jeu.resetChrono(10);
        if(jeu.getJoueurs().size() == 1) {
            jeu.resetTour();
            passerAEtape(new EtapeObservation(jeu, () -> {
                passerAEtape(new EtapeReproduction(panneau, controleur, () -> {}));
            }));
        } else {
            jeu.resetTour();
            passerAEtape(new EtapeProductionReference(panneau, controleur, () -> {}));
        }
    }

    /**
     * Valide l'étape actuelle du jeu, en passant à l'étape suivante (observation ou reproduction)
     * et en gérant le score à chaque changement d'étape.
     */
    public void valider() {
        if (tours == 5) {
            Joueur gagnant = null;
            int scoreMax = Integer.MIN_VALUE;
            for (Joueur joueur : jeu.getJoueurs()) {
               
                if (joueur.getScore() > scoreMax) {
                    scoreMax = joueur.getScore();
                    gagnant = joueur;
                }
            }
            if (gagnant != null) {
                super.notifierEcouteurs("Le gagnant est " + gagnant.getNom() + " avec un score de " + scoreMax + " !");
            } else {
                super.notifierEcouteurs("Aucun gagnant.");
            }
            return;
        }
        if (etapeCourante instanceof EtapeReproduction) {
            EtapeScore etape = new EtapeScore(jeu);
            passerAEtape(etape);
            super.notifierEcouteurs(etape);
            jeu.resetDessins();
            Joueur joueurCourant = jeu.getJoueurCourant();
            joueurCourant.incrementScore(etape.getScore());
            if (jeu.getJoueurs().size() > 1) {
                passerAEtape(new EtapeObservationDeuxJoueurs(jeu, () -> {
                    passerAEtape(new EtapeReproduction(panneau, controleur, () -> {}));
                   
                    jeu.resetScore();
                    jeu.resetDessins();
                    jeu.resetChrono(10);
                    jeu.resetTour();
                    tours++;
                    passerAEtape(new EtapeProductionReference(panneau, controleur, () -> {}));
                }));
            }
        } else if (etapeCourante instanceof EtapeProductionReference) {
            jeu.passerAuJoueurSuivant();
            passerAEtape(new EtapeObservationDeuxJoueurs(jeu, () -> {
                passerAEtape(new EtapeReproduction(panneau, controleur, () -> {}));
            }));
        }
    }

    private void passerAEtape(EtapeJeu nouvelle) {
        if(etapeCourante != null) {
            etapeCourante.quitter();
        }
        etapeCourante = nouvelle;
        etapeCourante.executer(() -> {});
    }
}