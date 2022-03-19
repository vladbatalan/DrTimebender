package PaooGame.menu;

import PaooGame.graphics.gameWindow.button.ButtonCollection;

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
