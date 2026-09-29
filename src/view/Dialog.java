package src.view;

import java.awt.BorderLayout;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import src.model.Dessin;
import src.model.DessinXMLDAO;

/**
 * Classe utilitaire pour afficher des dialogues liés aux actions de sauvegarde et de chargement de dessins.
 */
public class Dialog {

    public static void afficherScoreAvecSauvegarde(JFrame parent, int score, Dessin dessinASauvegarder, DessinXMLDAO dao) {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        JLabel scoreLabel = new JLabel("Votre score est : " + score);
        JButton saveButton = new JButton("Sauvegarder le dessin");
        panel.add(scoreLabel, BorderLayout.NORTH);
        panel.add(saveButton, BorderLayout.SOUTH);

        // Création du JOptionPane personnalisé
        JOptionPane optionPane = new JOptionPane(
            panel,
            JOptionPane.PLAIN_MESSAGE,
            JOptionPane.DEFAULT_OPTION
        );

        JDialog dialog = optionPane.createDialog(parent, "Résultat");

        // Action du bouton de sauvegarde
        saveButton.addActionListener(event -> {
            JFileChooser chooser = new JFileChooser();
            chooser.setDialogTitle("Choisir un emplacement de sauvegarde");
            int userSelection = chooser.showSaveDialog(dialog);
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToSave = chooser.getSelectedFile();
                try {
                    dao.sauvegarder(dessinASauvegarder, fileToSave);
                    JOptionPane.showMessageDialog(dialog, "Sauvegarde réussie !");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(dialog, "Erreur lors de la sauvegarde : " + ex.getMessage());
                }
            }
        });
        dialog.setVisible(true);
    }
}
