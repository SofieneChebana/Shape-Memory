package src.view;

import javax.swing.*;
import java.awt.*;

/**
 * Fabrique d'interface utilisateur pour le thème "Clair".
 * Définit les couleurs et les composants UI spécifiques au thème clair.
 */
public class LightUIFactory extends UIFactory {
    
    public LightUIFactory() {
        this.buttonBackground = Color.WHITE;
        this.buttonForeground = this.labeColor = Color.BLACK;
        this.backgroundColor = Color.LIGHT_GRAY;
    }

    @Override
    public JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(this.buttonBackground);
        button.setForeground(this.buttonForeground);
        return button;
    }

    @Override
    public JPanel createPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(this.backgroundColor);
        return panel;
    }

    @Override
    public JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(this.buttonForeground);
        return label;
    }
}