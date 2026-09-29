package src.view;

import javax.swing.*;
import java.awt.*;

/**
 * Fabrique d'interface utilisateur pour le thème "Bleu".
 * Définit les couleurs et les composants UI spécifiques au thème bleu.
 */
public class BlueUIFactory extends UIFactory {

    public BlueUIFactory() {
        this.buttonBackground = new Color(100, 149, 237); // CornflowerBlue
        this.buttonForeground = Color.WHITE;
        this.labeColor = new Color(25, 25, 112); // Midnight Blue
        this.backgroundColor = new Color(220, 230, 250);
    }

    @Override
    public JPanel createPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(this.backgroundColor);
        return panel;
    }

    @Override
    public JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(this.buttonBackground);
        button.setForeground(this.buttonForeground);
        return button;
    }

    @Override
    public JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(this.labeColor);
        return label;
    }
}