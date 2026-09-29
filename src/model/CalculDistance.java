package src.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe qui implémente la stratégie de calcul du score basée sur la distance entre les formes du dessin de référence et du dessin de reproduction.
 */
public class CalculDistance implements CalculScoreStrategy {

    @Override
    public int calculerScore(Jeu jeu) {
        List<Forme> referenceRestantes = new ArrayList<>(jeu.getDessinReference().getFormesListe());
        List<Forme> formesRepro = new ArrayList<>(jeu.getDessinReproduction().getFormesListe());
        int scoreTotal = 0;
        final int scoreMaxParForme = 100;
        for (Forme formeRepro : formesRepro) {
            Forme cible = null;
            double distanceMin = Double.MAX_VALUE;
            for (Forme refForme : referenceRestantes) {
                int dx = formeRepro.getX() - refForme.getX();
                int dy = formeRepro.getY() - refForme.getY();
                double distance = Math.sqrt(dx * dx + dy * dy);
                if (distance < distanceMin) {
                    distanceMin = distance;
                    cible = refForme;
                }
            }
            if (cible != null) {
                referenceRestantes.remove(cible);
                double tailleDiff = 0;
                if (formeRepro instanceof Cercle && cible instanceof Cercle){

                    tailleDiff = Math.abs(((Cercle) formeRepro).getRayon() - ((Cercle) cible).getRayon() );
                }

                else if (formeRepro instanceof Rectangle && cible instanceof Rectangle){

                    tailleDiff = Math.abs(((Rectangle) formeRepro).getLargeur() * ((Rectangle) formeRepro).getHauteur()  - ((Rectangle) cible).getLargeur() * ((Rectangle) cible).getHauteur() );
                }

                tailleDiff = tailleDiff * 0.3;
                int score = (int)(scoreMaxParForme - distanceMin - tailleDiff);
                scoreTotal += Math.max(score, 0);
            }
        }
        // Calcul de la pénalité si le nombre de formes dans chaque dessin ne correspond pas
        if (formesRepro.size() < referenceRestantes.size()) {
            int penalty = (referenceRestantes.size() - formesRepro.size()) * 5;
            scoreTotal -= penalty;
        }
        else if (formesRepro.size() > referenceRestantes.size()) {
            int penalty = (formesRepro.size() - referenceRestantes.size()) * 5;
            scoreTotal -= penalty;
        }
        int nbMatchables = Math.min(
            jeu.getDessinReference().getFormesListe().size(),
            jeu.getDessinReproduction().getFormesListe().size()
        );
        int maxPossibleScore = nbMatchables * scoreMaxParForme;
        if (maxPossibleScore == 0) return 0;
        return Math.max(0, (scoreTotal * 100)/maxPossibleScore);
    }
}