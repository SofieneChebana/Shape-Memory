package src.view;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import src.controller.GUI;
import src.model.CalculSimilitude;
import src.model.Dessin;
import src.model.DessinXMLDAO;
import src.model.CalculDistance;
import src.model.Jeu;

/**
 * Classe représentant le menu principal du jeu, avec les options pour choisir les thèmes, la stratégie de score, et le mode de jeu.
 */
public class Menu extends JPanel{
    private UIFactory factory;
    private JPanel topPane;
    private JButton play, quit, cheminButton;
    private JLabel titleLabel, themeLabel, strategyLabel, gameModeLabel, cheminLabel;
    private JComboBox<String> themeSelector, strategySelector, gameModeSelector;
    private JFrame fenetrePrincipale;
    private JTextField cheminChargement;
    private Jeu jeu;
    private DessinXMLDAO dessinXML = new DessinXMLDAO();

    public Menu(JFrame fenetrePrincipale, Jeu jeu, UIFactory factory){
        this.factory = factory;
        this.jeu = jeu;
        this.fenetrePrincipale = fenetrePrincipale;
        setLayout(new GridBagLayout());
        this.setBackground(factory.getBackgroundColor());
        createContenu();
    }

    private void createComponents() {
        this.topPane = factory.createPanel();
        this.titleLabel = factory.createLabel("Mémorisation de formes");
        this.titleLabel.setFont(new Font("Label.font",Font.BOLD, 16));
        this.play = factory.createButton("Jouer");
        this.quit = factory.createButton("Quitter");
        this.themeLabel = factory.createLabel("Choisir un thème :");
        String[] themes = { "Clair", "Sombre", "Solarisé", "Bleuté" };
        this.themeSelector = new JComboBox<>(themes);
        this.themeSelector.setSelectedItem(factory instanceof DarkUIFactory ? "Sombre" : "Clair");
        this.strategyLabel = factory.createLabel("Strategie de score :");
        String[] strategies = {"Distance", "Similitude"};
        this.strategySelector = new JComboBox<>(strategies);
        this.strategySelector.setSelectedItem("Distance");
        this.gameModeLabel = factory.createLabel("Méthode de jeu :");
        String[] gameMode = {"1 joueur", "2 joueurs"};
        this.gameModeSelector = new JComboBox<>(gameMode);
        this.gameModeSelector.setSelectedItem("1 joueurs"); // Par défaut
    
        this.cheminLabel = factory.createLabel("Chemin fichier XML (optionnel)");
        this.cheminButton = factory.createButton("Parcourir ...");
        this.cheminChargement = new JTextField("default_save.xml", 30);
        cheminChargement.setMaximumSize(new Dimension(300, 30));
        cheminChargement.setText("");
    }

    private void createContenu() {
        this.removeAll();
        createComponents();
        topPane.setLayout(new BoxLayout(this.topPane, BoxLayout.Y_AXIS));
        
        topPane.add(this.titleLabel);
        topPane.add(Box.createVerticalStrut(75));
        topPane.add(this.themeLabel);
        topPane.add(Box.createVerticalStrut(10));
        topPane.add(this.themeSelector);
        topPane.add(Box.createVerticalStrut(10));
        topPane.add(this.strategyLabel);
        topPane.add(Box.createVerticalStrut(5));
        topPane.add(this.strategySelector);
        topPane.add(Box.createVerticalStrut(10));
        topPane.add(this.gameModeLabel);
        topPane.add(Box.createVerticalStrut(5));
        topPane.add(this.gameModeSelector);
        topPane.add(Box.createVerticalStrut(10));
        topPane.add(this.cheminLabel);
        topPane.add(Box.createVerticalStrut(10));
        topPane.add(this.cheminChargement);
        topPane.add(this.cheminButton);
        topPane.add(Box.createVerticalStrut(10));
        topPane.add(this.play);
        topPane.add(Box.createVerticalStrut(10));
        topPane.add(this.quit);
        topPane.add(Box.createVerticalStrut(10));
        for (Component c: this.topPane.getComponents()){
            ((JComponent) c).setAlignmentX(Component.CENTER_ALIGNMENT);
        }

        this.cheminButton.addActionListener((event) -> {
            JFileChooser chooser = new JFileChooser();
            chooser.setDialogTitle("Choisir un fichier XML à charger");
            int result = chooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File fichier = chooser.getSelectedFile();
                cheminChargement.setText(fichier.getAbsolutePath());
        
                // Charger le dessin
                try {
                    DessinXMLDAO dao = new DessinXMLDAO();
                    Dessin dessinCharge = dao.charger(fichier);
                    jeu.setDessinReference(dessinCharge);
                    JOptionPane.showMessageDialog(this, "Dessin chargé avec succès !");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Erreur lors du chargement : " + ex.getMessage());
                }
            }
        });

        play.addActionListener((event) ->{

            String chemin = this.cheminChargement.getText().trim();
            if(!chemin.isEmpty()) {
                File fichier = new File(chemin);
                if (fichier.exists()) {
                    try {
                        Dessin dessinCharge = dessinXML.charger(fichier);
                        jeu.setDessinReference(dessinCharge);
                        System.out.println("Dessin chargé depuis : " + fichier.getAbsolutePath());
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(this, "Erreur de chargement du fichier XML :\n" + ex.getMessage());
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Le fichier spécifié n'existe pas !");
            }
            }

            if (jeu.getJoueurs().isEmpty()) {
                jeu.setSinglePlayerMode(true);
            }
            GUI.changePanel(this.fenetrePrincipale, new JeuVue(this.jeu, this.factory));
        });
        themeSelector.addActionListener((event) -> {
            String selected = (String) themeSelector.getSelectedItem();
            if (selected != null) {
                switch (selected) {
                    case "Clair":
                        this.factory = new LightUIFactory();
                        break;
                    case "Sombre":
                        this.factory = new DarkUIFactory();
                        break;
                    case "Solarisé":
                        this.factory = new SolarizedUIFactory();
                        break;
                    case "Bleuté":
                        this.factory = new BlueUIFactory();
                        break;
                }
                createContenu(); // Recharge avec nouveau thème
            }
        });
        strategySelector.addActionListener((event) -> {
            String selected = (String) strategySelector.getSelectedItem();
            if(selected != null) {
                switch (selected) {
                    case "Distance":
                        jeu.setCalculMethod(new CalculDistance());
                        break;
                    case "Similitude":
                        jeu.setCalculMethod(new CalculSimilitude());
                        break;
                    default:
                        break;
                }
            }
        });
        gameModeSelector.addActionListener((event) -> {
            String selected = (String) gameModeSelector.getSelectedItem();
            if (selected != null) {
                switch (selected) {
                    case "1 joueur":
                        jeu.setSinglePlayerMode(true);
                        break;
                    case "2 joueurs":
                        jeu.setSinglePlayerMode(false);
                        break;
                    default:
                        break;
                }
            }
        });
        quit.addActionListener((event) -> System.exit(0));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER; // Centrage total
        this.add(topPane, gbc);
        this.setBackground(factory.getBackgroundColor());
        this.revalidate(); // Mets à jour le layout
        this.repaint();
    }
    
}
