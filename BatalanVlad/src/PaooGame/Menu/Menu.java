package PaooGame.Menu;

import PaooGame.GameWindow.Button.ButtonCollection;
import javafx.util.Pair;

import java.awt.*;

public abstract class Menu {
    protected ButtonCollection buttons = new ButtonCollection();

    public Menu(){
    }

    public abstract void InitMenu();
    public abstract void Draw(Graphics g);
    public ButtonCollection getButtons() {
        return buttons;
    }

    // returns the size of a string
    protected Pair<Integer, Integer> getFontSize(Graphics g, Font myFont, String content){
        // get metrics from the graphics
        FontMetrics metrics = g.getFontMetrics(myFont);
        // get the height of a line of text in this
        // font and render context
        int hgt = metrics.getHeight();
        // get the advance of my text in this font
        // and render context
        int adv = metrics.stringWidth(content);
        // calculate the size of a box to hold the
        // text with some padding.
        Dimension size = new Dimension(adv, hgt);

        int fontWidth = size.width;
        int fontHeight = size.height;

        return new Pair<Integer, Integer>(fontWidth, fontHeight);
    }
}
