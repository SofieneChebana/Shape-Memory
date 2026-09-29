package src.controller;

import javax.swing.*;

/**
 * La classe GUI contient des méthodes statiques pour faciliter la gestion des vues dans l'application.
 * Elle permet de changer de panneau dans la fenêtre principale.
 */
public class GUI {

    public static void changePanel(JFrame frame, JPanel panel){
        frame.getContentPane().removeAll();
        frame.getContentPane().add(panel);
        frame.invalidate();
        frame.validate();
        frame.repaint();
    }
}