package src.view;

import javax.swing.*;
import java.awt.*;

/**
 * Fabrique d'interface utilisateur pour le thème "Solarized".
 * Définit les couleurs et les composants UI spécifiques au thème Solarized.
 */
public class SolarizedUIFactory extends UIFactory {

    public SolarizedUIFactory() {
        this.buttonBackground = new Color(38, 139, 210);
        this.buttonForeground = Color.WHITE;
        this.labeColor = new Color(88, 110, 117);
        this.backgroundColor = new Color(253, 246, 227); // base3
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