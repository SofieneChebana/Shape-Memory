package src.view;

import java.awt.Color;
import javax.swing.*;

/**
 * Classe abstraite pour créer des composants d'interface utilisateur (UI) selon le thème spécifié.
 * Les sous-classes concrètes définissent les couleurs et le style spécifiques.
 */
public abstract class UIFactory {

    protected Color buttonBackground;
    protected Color buttonForeground;
    protected Color labeColor;
    protected Color backgroundColor;

    public Color getColorButtonForeground() {
        return this.buttonForeground;
    }

    public Color getColorButtonBackground() {
        return this.buttonBackground;
    }

    public Color getColorLabel() {
        return this.labeColor;
    }

    public Color getBackgroundColor() {
        return this.backgroundColor;
    }

    public abstract JButton createButton(String text);
    public abstract JPanel createPanel();
    public abstract JLabel createLabel(String text);
}