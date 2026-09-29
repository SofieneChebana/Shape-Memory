package src.view;

import javax.swing.*;
import src.model.Jeu;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe représentant la fenêtre principale du jeu, avec le menu et la gestion de l'interface de jeu.
 */
public class FenetrePrincipale extends JFrame {

	public FenetrePrincipale(Jeu jeu, UIFactory factory){
		super("Mémorisation de formes");
		setSize(1000,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Menu");
		JMenuItem itemAide = new JMenuItem("Aide");
		JMenuItem itemPropos = new JMenuItem("À propos");
		menu.add(itemAide);
		menu.addSeparator();
		menu.add(itemPropos);
		menuBar.add(menu);
		setJMenuBar(menuBar);

		itemAide.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new VueAide();
            }
        });

        itemPropos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new VueApropos();
            }
        });
		this.setLayout(new BorderLayout());
		this.getContentPane().add(new Menu(this, jeu, factory));
		setVisible(true);
	}
}