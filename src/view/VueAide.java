package src.view;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * Vue affichant des informations d'aide sur le jeu.
 * Contient des explications sur les règles et les modes de jeu.
 */
public class VueAide extends JFrame {

    public VueAide(){
        JLabel label_aide = new JLabel("<html><body><H2>Explication</H2>Le but de ce jeu est de mémoriser des formes et de les reproduire correctement pendant une phase de reproduction.<br><br><H3>Règles :</H3><ul><li>Au début du jeu, un dessin de référence est généré. Ce dessin comporte plusieurs formes (cercles et/ou rectangles).</li><li>Après un temps d'observation de 10 secondes, le joueur doit reproduire le dessin en utilisant des outils de dessin (ajouter des cercles, des rectangles, déplacer ou supprimer des formes).</li><li>Le score est calculé en fonction de la similitude entre les formes du dessin de reproduction et celles du dessin de référence.</li><li>Le joueur doit respecter le placement et la taille des formes pour obtenir un score élevé.</li></ul><br><H3>Modes de Jeu :</H3><ul><li><strong>1 joueur :</strong> Vous jouez seul, avec un seul dessin de référence et un seul joueur pour la reproduction.</li><li><strong>2 joueurs :</strong> Deux joueurs alternent pour la phase d'observation et la phase de reproduction, chacun devant essayer de reproduire le dessin de l'autre.</li></ul></body></html>");
        JPanel panel_aide = new JPanel();
        panel_aide.setLayout(new BoxLayout(panel_aide, BoxLayout.Y_AXIS));
        panel_aide.add(label_aide);
        JPanel panel_bouton = new JPanel();
        JButton bouton_fermer = new JButton("Fermer");
        panel_bouton.add(bouton_fermer);
        JPanel marge = new JPanel();
        marge.setPreferredSize(new Dimension(8,0));

        bouton_fermer.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                dispose();
            }
        });

        this.add(panel_aide);
        this.add(marge, BorderLayout.WEST);
        this.add(panel_bouton,BorderLayout.SOUTH);
        this.setTitle("Aide");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500,525);
        this.setResizable(false);
        this.setVisible(true);
    }
}