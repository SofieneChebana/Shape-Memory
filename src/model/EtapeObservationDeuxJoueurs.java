package src.model;

import java.util.TimerTask;
import java.util.Timer;

/**
 * Représente l'étape d'observation du jeu dans le mode à deux joueurs,
 * où chaque joueur observe le dessin de l'autre avant de commencer à le reproduire.
 * Un compte à rebours est effectué pendant cette étape.
 */
public class EtapeObservationDeuxJoueurs implements EtapeJeu {
    private Jeu jeu;
    private Runnable onFinish;

    public EtapeObservationDeuxJoueurs(Jeu jeu, Runnable onFinish) {
        this.jeu = jeu;
        this.onFinish = onFinish;
    }

    @Override
    public void executer(Runnable onEtapeFinie) {
        System.out.println("EtapeObservation");
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Temps restant: " + jeu.getChrono() + "s");
                jeu.decrementerChrono();
                jeu.genereNotif();

                if (jeu.getChrono() <= 0) {
                    timer.cancel();
                    jeu.passerTour();

                    onFinish.run();
                }
            }
        }, 0, 1000);
    }

    @Override
    public void quitter() { 
    }
}