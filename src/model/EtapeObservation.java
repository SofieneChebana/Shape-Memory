package src.model;

import java.util.TimerTask;
import java.util.Timer;

/**
 * Représente l'étape d'observation du jeu où le joueur observe le dessin de référence
 * avant de commencer à le reproduire. Un compte à rebours est effectué pendant cette étape.
 */
public class EtapeObservation implements EtapeJeu {
    private Jeu jeu;
    private Runnable onFinish;

    public EtapeObservation(Jeu jeu, Runnable onFinish) {
        this.jeu = jeu;
        this.onFinish = onFinish;
    }

    @Override
    public void executer(Runnable onEtapeFinie) {
        if(jeu.getDessinReference().getFormesListe().size() == 0) {
            jeu.genererDessinReference();
        }
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