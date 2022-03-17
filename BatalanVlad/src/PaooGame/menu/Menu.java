package PaooGame.menu;

import PaooGame.gameWindow.button.ButtonCollection;
import javafx.util.Pair;

import java.awt.*;

public abstract class Menu {
    protected ButtonCollection buttons = new ButtonCollection();

    public Menu() {
    }

    public abstract void InitMenu();

    public abstract void Draw(Graphics g);

    public ButtonCollection getButtons() {
        return buttons;
    }

}
