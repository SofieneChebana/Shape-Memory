package src.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe qui implémente la stratégie de calcul du score basée sur la similitude des formes entre le dessin de référence
 * et le dessin de reproduction. Elle ne prend pas en compte la distance, mais compare la présence et la forme des objets.
 */
public class CalculSimilitude implements CalculScoreStrategy {

    @Override
    public int calculerScore(Jeu jeu) {
        List<Forme> referenceRestantes = new ArrayList<>(jeu.getDessinReference().getFormesListe());
        List<Forme> formesRepro = new ArrayList<>(jeu.getDessinReproduction().getFormesListe());
        int scoreTotal = 0;
        final int scoreMaxParForme = 100;
        final double tolerance = 0.1; // 10% de tolérance sur la taille des formes
        for (Forme formeRepro : formesRepro) {
            Forme cible = null;
            for (Forme refForme : referenceRestantes) {
                if (formeRepro.getClass() == refForme.getClass()) {
                    if (formeRepro instanceof Cercle) {
                        Cercle cercleRepro = (Cercle) formeRepro;
                        Cercle cercleRef = (Cercle) refForme;
                        // Vérification si les cercles ont le même rayon (avec une tolérance)
                        if (Math.abs(cercleRepro.getRayon() - cercleRef.getRayon()) <= tolerance * cercleRef.getRayon()) {
                            cible = refForme;
                            break;
                        }
                    } else if (formeRepro instanceof Rectangle) {
                        Rectangle rectangleRepro = (Rectangle) formeRepro;
                        Rectangle rectangleRef = (Rectangle) refForme;
                        // Vérification si les rectangles ont la même largeur et hauteur (avec une tolérance)
                        double areaRepro = rectangleRepro.getLargeur() * rectangleRepro.getHauteur();
                        double areaRef = rectangleRef.getLargeur() * rectangleRef.getHauteur();
                        if (Math.abs(areaRepro - areaRef) <= tolerance * areaRef) {
                            cible = refForme;
                            break;
                        }
                    }
                }
            }
            if (cible != null) {
                referenceRestantes.remove(cible);
                scoreTotal += scoreMaxParForme;
            }
        }
        int penalty = Math.abs(formesRepro.size() - referenceRestantes.size()) * 2;
        scoreTotal -= penalty;
        int maxPossibleScore = Math.max(formesRepro.size(), referenceRestantes.size()) * scoreMaxParForme;
        if (maxPossibleScore == 0) return 0;
        return Math.max(0, (scoreTotal * 100) / maxPossibleScore);
    }
}