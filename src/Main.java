package src;

import src.model.*;
import src.view.*;

/**
 * La classe Main est le point d'entrée de l'application.
 * Elle initialise le jeu et crée la fenêtre principale de l'application.
 */
public class Main{
	public static void main(String args[] ){
		Jeu jeu = new Jeu(new CalculDistance());
		new FenetrePrincipale(jeu, new DarkUIFactory());
	}
}

//dans le dossier livraison
//javac -d build src/*.java
//java -cp build src.Main