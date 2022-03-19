package PaooGame.menu;

import PaooGame.Game;
import PaooGame.graphics.gameWindow.button.ButtonCollection;
import PaooGame.graphics.gameWindow.button.MyButton;
import PaooGame.graphics.gameWindow.button.buttonTypes.ProfileSelectButton;
import PaooGame.graphics.gameWindow.button.buttonTypes.ReturnMainMenuButton;
import PaooGame.graphics.gameWindow.utils.FontUtils;
import javafx.util.Pair;

import java.awt.*;
import java.util.List;

public class ProfileSelectionMenu extends Menu {
    public ProfileSelectionMenu() {
        InitMenu();
    }

    public void InitMenu() {
        int distanceToTop = 120;
        int distanceToSides = 100;
        int buttonHeight = 40;
        int buttonSpacer = 50;

        List<Pair<Integer, String>> databaseUsers = Game.database.GetAllUsers();


        MyButton returnMenu = new ReturnMainMenuButton(
                new Rectangle(Game.GAME_WINDOW_WIDTH / 2 - 75, Game.GAME_WINDOW_HEIGHT - 100, 145, 40),
                "Back to Menu"
        );
        buttons.AddElement(returnMenu);

        if (databaseUsers.isEmpty()) {
            // display string that says that it is empty

            return;
        }


        int buttonNumber = 0;
        for (Pair<Integer, String> registration : databaseUsers) {

            ProfileSelectButton profileButton = new ProfileSelectButton(
                    new Rectangle(distanceToSides, distanceToTop + buttonNumber * buttonSpacer, Game.GAME_WINDOW_WIDTH - 2 * distanceToSides, buttonHeight),
                    "",
                    new Point()
            );
            profileButton.setUserData(registration.getKey(), registration.getValue());
            profileButton.setButtonFont(new Font("arial", Font.PLAIN, 20));

            // the number of completed levels
            int completedLevels = Game.database.CheckLevelsCompleted(registration.getKey());

            profileButton.setButtonText((buttonNumber + 1) + ") " + registration.getValue() + "   ...   " + completedLevels + " Levels completed");
            profileButton.setTextColor(new Color(0xF9EE35));

            buttons.AddElement(profileButton);

            buttonNumber++;
        }

    }

    public void Draw(Graphics g) {
        Font mainFont = new Font("arial", Font.BOLD, 40);
        Pair<Integer, Integer> fontSize =  FontUtils.getFontSize(mainFont, g, "Select Profile");
        g.setFont(mainFont);
        g.setColor(Color.black);
        g.drawString("Select Profile", Game.GAME_WINDOW_WIDTH / 2 - fontSize.getKey() / 2 + 2, 80 + 2);
        g.setColor(Color.white);
        g.drawString("Select Profile", Game.GAME_WINDOW_WIDTH / 2 - fontSize.getKey() / 2, 80);

        buttons.Draw(g);
    }

    public ButtonCollection getButtons() {
        return buttons;
    }
}
