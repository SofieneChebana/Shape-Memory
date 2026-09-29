package src.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe abstraite qui implémente la gestion des écouteurs pour les modèles.
 * Cette classe permet d'ajouter, retirer et notifier des écouteurs lorsque l'état du modèle change.
 */
public abstract class AbstractModeleEcoutable implements ModeleEcoutable {
    private List<EcouteurModele> ecouteurs = new ArrayList<>();

    public void ajoutEcouteur(EcouteurModele e) {
        ecouteurs.add(e);
    }

    public void retraitEcouteur(EcouteurModele e) {
        ecouteurs.remove(e);
    }

    protected void notifierEcouteurs() {
        notifierEcouteurs(this);
    }

    protected void notifierEcouteurs(Object source) {
        for (EcouteurModele e : ecouteurs) {
            e.modeleMisAJour(source);
        }
    }
}