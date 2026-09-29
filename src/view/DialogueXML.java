package src.view;

import javax.swing.*;
import java.io.File;

import src.model.Dessin;
import src.model.DessinXMLDAO;
import src.model.Forme;
import src.model.Jeu;

/**
 * Classe utilitaire pour afficher un dialogue permettant de charger un dessin à partir d'un fichier XML.
 */
public class DialogueXML {
    public static void afficherDialogueChargementDessin(JFrame parent, Jeu jeu) {
        Object[] options = { "Charger un dessin", "Annuler" };
        int choix = JOptionPane.showOptionDialog(
            parent,
            "Souhaitez-vous charger un dessin depuis un fichier XML ?",
            "Charger un dessin",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options[0]
        );

        if (choix == 0) { // Si "Charger un dessin"
            JFileChooser fileChooser = new JFileChooser();
            int retour = fileChooser.showOpenDialog(parent);

            if (retour == JFileChooser.APPROVE_OPTION) {
                File fichier = fileChooser.getSelectedFile();
                DessinXMLDAO dao = new DessinXMLDAO();
                Dessin dessinCharge = dao.charger(fichier);

                for (Forme f : dessinCharge.getFormesListe()) {
                    jeu.getDessinReference().ajouterForme(f);
                }

                jeu.genereNotif(); // Met à jour les vues
            }
        }
    }
}
