package src.view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

/**
 * Vue affichant des informations à propos du jeu.
 * Contient des informations sur la version du jeu et les contributeurs.
 */
public class VueApropos extends JFrame {

    public VueApropos(){
        JLabel label_apropos = new JLabel("<html><body><H1>Mémorisation de formes</H1>Version : 0.0.1<br><br>Contributeurs :<ul><li>FAUQUETTE Maël</li><li>RABOT Valentin</li><li>DELILLE Mattchieu</li><li>CHEBANA Sofiene</li></ul></body></html>");
        JLabel lien = new JLabel("<html><body>Dépot : <a href=''>Forge</a></body></html>");
        JPanel panel_apropos = new JPanel();
        panel_apropos.setLayout(new BoxLayout(panel_apropos, BoxLayout.Y_AXIS));
        panel_apropos.add(label_apropos);
        panel_apropos.add(lien);
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

        lien.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    java.awt.Desktop.getDesktop().browse(java.net.URI.create("https://redmine-etu.unicaen.fr/projects/chebana-fauquette-rabot-delille/repository"));
                } catch (java.io.IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        this.add(panel_apropos);
        this.add(marge,BorderLayout.WEST);
        this.add(panel_bouton,BorderLayout.SOUTH);
        this.setTitle("À propos");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(300,325);
        this.setResizable(false);
        this.setVisible(true);
    }
}