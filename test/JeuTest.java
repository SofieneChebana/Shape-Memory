package test;

import junit.framework.*;
import src.model.Jeu;
import src.model.Cercle;
import src.model.Rectangle;
import src.model.Forme;

public class JeuTest extends TestCase {

    public JeuTest(String name) {
        super(name);
    }

    public static Test suite() {
        TestSuite suite = new TestSuite();
        suite.addTest(new JeuTest("testInitialisationJeu"));
        suite.addTest(new JeuTest("testAjoutForme"));
        suite.addTest(new JeuTest("testResetScore"));
        return suite;
    }

    /**
     * Vérifie que l'initialisation du jeu crée correctement un objet Jeu
     */
    public void testInitialisationJeu() {
        Jeu jeu = new Jeu(null);
        assertNotNull(jeu);
        assertEquals(0, jeu.getScore()); 
        assertEquals(10, jeu.getChrono());
        assertTrue(jeu.getDessinReference().getFormesListe().isEmpty());
    }

    /**
     * Vérifie l'ajout de formes dans le jeu
     */
    public void testAjoutForme() {
        Jeu jeu = new Jeu(null); 
        Forme cercle = new Cercle(10, 10, 5);
        Forme rectangle = new Rectangle(20, 20, 10, 20);
        jeu.ajouterForme(cercle);
        jeu.ajouterForme(rectangle);
        assertEquals(2, jeu.getDessinReference().getFormesListe().size());
        assertTrue(jeu.getDessinReference().getFormesListe().contains(cercle));
        assertTrue(jeu.getDessinReference().getFormesListe().contains(rectangle)); 
    }

    /**
     * Vérifie que le score est réinitialisé correctement
     */
    public void testResetScore() {
        Jeu jeu = new Jeu(null);
        jeu.resetScore();
        assertEquals(0, jeu.getScore());
    }

    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }
}