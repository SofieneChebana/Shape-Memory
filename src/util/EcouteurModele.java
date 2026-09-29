package src.util;

/**
 * Interface pour les écouteurs de modèles.
 * Les classes implémentant cette interface peuvent recevoir des notifications lorsqu'un modèle est mis à jour.
 */
public interface EcouteurModele {
    public void modeleMisAJour(Object source);
}
