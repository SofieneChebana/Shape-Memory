package src.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import src.controller.ControleurDessin;
import src.controller.OutilDessinCercle;
import src.controller.OutilDessinMouvement;
import src.controller.OutilDessinRectangle;
import src.controller.OutilDessinSupprimer;
import src.model.EtapeScore;
import src.model.Joueur;
import src.controller.ControleurJeu;
import src.util.*;

/**
 * Vue représentant la barre d'outils dans l'interface de jeu.
 * Contient des boutons pour les actions de dessin, la gestion du score et du temps, 
 * et la sélection du mode de jeu.
 */
public class BarreOutilVue extends JPanel implements EcouteurModele {
    private ControleurJeu controleurJeu;
    private JButton currentBouton = null;
    private JLabel labelTimer;
    private JLabel labelScore1;
    private JLabel labelScore2;
    private JLabel labelScore;
    private JLabel labelJoueur;
    private boolean started;
    private UIFactory factory;

    public BarreOutilVue(ControleurDessin controleur, ControleurJeu controleurJeu, UIFactory factory) {
        super(new BorderLayout());
        this.setPreferredSize(new Dimension(200, 600));
        this.controleurJeu = controleurJeu;
        this.controleurJeu.getJeu().ajoutEcouteur(this);
        this.controleurJeu.ajoutEcouteur(this);
        this.factory = factory;
        this.started = false;
        JPanel infoPanel = factory.createPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        labelTimer = factory.createLabel("Temps restant : "+controleurJeu.getJeu().getChrono()+"s");
        labelTimer.setAlignmentX(Component.CENTER_ALIGNMENT);
        if(!controleurJeu.getJeu().isSinglePlayer()){
            labelScore1 = factory.createLabel("Score "+controleurJeu.getJeu().getJoueurs().get(0).getNom()+ " : 0");
            labelScore2 = factory.createLabel("Score "+controleurJeu.getJeu().getJoueurs().get(1).getNom()+ " : 0");
            labelScore1.setAlignmentX(Component.CENTER_ALIGNMENT);
            labelScore2.setAlignmentX(Component.CENTER_ALIGNMENT);
        }else{
            labelScore = factory.createLabel("Score : 0");
            labelScore.setAlignmentX(Component.CENTER_ALIGNMENT);
        }
        labelJoueur = factory.createLabel("Joueur actuel : " + (controleurJeu.getJeu().getJoueurs().isEmpty() ? "Aucun joueur" : controleurJeu.getJeu().getJoueurCourant().getNom()));
        labelJoueur.setAlignmentX(Component.CENTER_ALIGNMENT);
        infoPanel.add(Box.createVerticalStrut(10));
        infoPanel.add(labelTimer);
        if(!controleurJeu.getJeu().isSinglePlayer()){
            infoPanel.add(Box.createVerticalStrut(5));
            infoPanel.add(labelScore1);
            infoPanel.add(labelScore2);
        }else{
            infoPanel.add(Box.createVerticalStrut(5));
            infoPanel.add(labelScore);
        }
  
        infoPanel.add(Box.createVerticalStrut(10));
        infoPanel.add(labelJoueur);
        JPanel toolsPanel = factory.createPanel();
        toolsPanel.setLayout(new BoxLayout(toolsPanel, BoxLayout.Y_AXIS));
        ajouterBouton("Selection", e -> { controleur.setOutilDessin(new OutilDessinMouvement()); gererSelection((JButton)e.getSource()); }, toolsPanel);
        ajouterBouton("Supprimer", e -> { controleur.setOutilDessin(new OutilDessinSupprimer()); gererSelection((JButton)e.getSource()); }, toolsPanel);
        ajouterBouton("Ajouter un rectangle", e -> { controleur.setOutilDessin(new OutilDessinRectangle()); gererSelection((JButton)e.getSource()); }, toolsPanel);
        ajouterBouton("Ajouter un cercle", e -> { controleur.setOutilDessin(new OutilDessinCercle()); gererSelection((JButton)e.getSource()); }, toolsPanel);
        ajouterBouton("Undo", event -> controleur.undo(), toolsPanel);
        ajouterBouton("Redo", event -> controleur.redo(), toolsPanel);
        toolsPanel.add(Box.createVerticalStrut(10));
        JPanel validatePanel = factory.createPanel();
        validatePanel.setLayout(new BoxLayout(validatePanel, BoxLayout.Y_AXIS));
        ActionListener actionCommencer = (event) -> {
            if (this.started == false) {
                this.started = true;
                this.controleurJeu.demarrer();
            }
        };
        ajouterBouton("Valider les formes", event -> controleurJeu.valider(), validatePanel);
        ajouterBouton("Commencer la partie", actionCommencer, validatePanel);
        validatePanel.add(Box.createVerticalStrut(10));
        JPanel topPanel = factory.createPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.add(infoPanel, BorderLayout.NORTH);
        topPanel.add(toolsPanel, BorderLayout.CENTER);
        JPanel fakeContentPane = factory.createPanel();
        fakeContentPane.setLayout(new BorderLayout());
        fakeContentPane.add(topPanel, BorderLayout.NORTH);
        fakeContentPane.add(validatePanel, BorderLayout.SOUTH);
        this.add(fakeContentPane);
    }

    private void ajouterBouton(String nom, ActionListener e, JPanel panel) {
        JButton bouton = factory.createButton(nom);
        bouton.setAlignmentX(Component.CENTER_ALIGNMENT);
        bouton.addActionListener(e);
        panel.add(Box.createVerticalStrut(10));
        panel.add(bouton);

    }

    /**
     * Gère la sélection des boutons en les mettant en surbrillance.
     * 
     * @param bouton le bouton actuellement sélectionné
     */
    private void gererSelection(JButton bouton) {
        bouton.setBackground(Color.GREEN);
        bouton.setOpaque(true);
        if (this.currentBouton !=null && this.currentBouton != bouton){
            this.currentBouton.setBackground(this.factory.getColorButtonBackground());
        }
        this.currentBouton = bouton;
    }

     /**
     * Mise à jour des labels de la barre d'outils en fonction de l'état du jeu.
     * 
     * @param source l'objet source qui a déclenché la mise à jour
     */
    @Override
    public void modeleMisAJour(Object source) {
        if (source instanceof String) {
            String message = (String) source;
            JOptionPane.showMessageDialog(this, message, "Fin du jeu", JOptionPane.INFORMATION_MESSAGE);
        } else {
            Joueur joueurCourant = controleurJeu.getJeu().getJoueurCourant();
            labelJoueur.setText("Joueur actuel : " + joueurCourant.getNom());
            if (!controleurJeu.getJeu().isSinglePlayer()) {
                labelScore1.setText("Score " + controleurJeu.getJeu().getJoueurs().get(0).getNom() + " : " +
                        controleurJeu.getJeu().getJoueurs().get(0).getScore());
                labelScore2.setText("Score " + controleurJeu.getJeu().getJoueurs().get(1).getNom() + " : " +
                        controleurJeu.getJeu().getJoueurs().get(1).getScore());
            } else {
                labelScore.setText("Score : " + joueurCourant.getScore());
            }
            if (source instanceof EtapeScore) {
                EtapeScore etapeScore = (EtapeScore) source;
                int score = etapeScore.getScore();
                JOptionPane.showMessageDialog(this, "Score obtenu : " + score, "Résultat", JOptionPane.INFORMATION_MESSAGE);
                this.started = false;
            } else {
                labelTimer.setText("Temps restant : " + controleurJeu.getJeu().getChrono() + "s");
            }
        }
    }
}
