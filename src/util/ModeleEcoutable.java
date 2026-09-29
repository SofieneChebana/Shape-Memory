package src.util;

/**
 * Interface pour les modèles écoutables.
 * Les classes implémentant cette interface peuvent ajouter ou retirer des écouteurs, ainsi que notifier les écouteurs lorsqu'un changement se produit dans le modèle.
 */
public interface ModeleEcoutable {
    public void ajoutEcouteur(EcouteurModele e);
    public void retraitEcouteur(EcouteurModele e);
}