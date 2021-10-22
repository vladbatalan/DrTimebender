package PaooGame.menu;

import PaooGame.gameWindow.button.ButtonCollection;
import PaooGame.gameWindow.button.MyButton;
import PaooGame.gameWindow.button.buttonTypes.*;

import java.awt.*;

public class MapCreationMenu extends Menu {
    public MapCreationMenu() {
        InitMenu();
    }

    public void InitMenu() {
        int distanceToTop = 200;
        int distanceToLeft = 50;
        int buttonWidth = 250;
        int buttonHeight = 40;
        int buttonSpacer = 70;

        MyButton newGameButton = new NewGameButton(
                new Rectangle(distanceToLeft, distanceToTop, buttonWidth, buttonHeight),
                "Start New Game",
                new Point()
        );

        MyButton loadGameButton = new LoadGameButton(
                new Rectangle(distanceToLeft, distanceToTop + buttonSpacer, buttonWidth, buttonHeight),
                "Load Game",
                new Point()
        );
        MyButton createMapButton = new CreateCustomMapButton(
                new Rectangle(distanceToLeft, distanceToTop + 2 * buttonSpacer, buttonWidth, buttonHeight),
                "Create Custom Map",
                new Point()
        );
        MyButton helpButton = new HelpButton(
                new Rectangle(distanceToLeft, distanceToTop + 3 * buttonSpacer, buttonWidth, buttonHeight),
                "Help",
                new Point()
        );
        MyButton quitButton = new QuitButton(
                new Rectangle(distanceToLeft, distanceToTop + 4 * buttonSpacer, buttonWidth, buttonHeight),
                "Quit Game",
                new Point()
        );
        buttons.AddElement(newGameButton);
        buttons.AddElement(loadGameButton);
        buttons.AddElement(createMapButton);
        buttons.AddElement(helpButton);
        buttons.AddElement(quitButton);
    }

    public void Draw(Graphics g) {
        g.setFont(new Font("arial", Font.BOLD, 50));
        g.setColor(Color.white);
        g.drawString("MapCreationModule", 50, 80);

        g.setFont(new Font("arial", Font.PLAIN, 20));
        g.setColor(Color.white);
        g.drawString("Created by Batalan Vlad", 150, 110);

        buttons.Draw(g);
    }

    public ButtonCollection getButtons() {
        return buttons;
    }
}
