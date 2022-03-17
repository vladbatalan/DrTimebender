package PaooGame.gameWindow.utils;

import javafx.util.Pair;

import java.awt.*;

public class FontUtils {
    public static Pair<Integer, Integer> getFontSize(Font font, Graphics g, String content){
        FontMetrics metrics = g.getFontMetrics(font);
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

        return new Pair<>(fontWidth, fontHeight);
    }
}
