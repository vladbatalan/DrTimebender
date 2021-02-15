package PaooGame.Menu;

import PaooGame.Game;
import PaooGame.GameWindow.Button.ButtonCollection;
import PaooGame.GameWindow.Button.ButtonTypes.*;
import PaooGame.GameWindow.Button.MyButton;
import javafx.util.Pair;

import java.awt.*;
import java.util.ArrayList;

public class ProfileSelectionMenu extends Menu{
    public ProfileSelectionMenu(){
        InitMenu();
    }

    public void InitMenu(){
        Integer distanceToTop = 120;
        Integer distanceToSides = 100;
        Integer buttonHeight = 40;
        Integer buttonSpacer = 50;

        ArrayList<Pair<Integer, String>> databaseUsers = Game.database.GetAllUsers();


        MyButton returnMenu = new ReturnMainMenuButton(
                new Rectangle(Game.GAME_WINDOW_WIDTH/2 - 75 , Game.GAME_WINDOW_HEIGHT - 100 , 145 , 40),
                "Back to Menu",
                new Point()
        );
        buttons.AddElement(returnMenu);

        if(databaseUsers.isEmpty()){
            // display string that says that it is empty

            return;
        }


        int buttonNumber = 0;
        for(Pair<Integer, String> registration : databaseUsers){

            ProfileSelectButton profileButton = new ProfileSelectButton(
                    new Rectangle(distanceToSides, distanceToTop + buttonNumber * buttonSpacer, Game.GAME_WINDOW_WIDTH-2 * distanceToSides, buttonHeight),
                    "",
                    new Point()
            );
            profileButton.setUserData(registration.getKey(), registration.getValue());
            profileButton.setButtonFont(new Font("arial", Font.PLAIN, 20));

            // the number of completed levels
            int completedLevels = Game.database.CheckLevelsCompleted(registration.getKey());

            profileButton.setButtonText((buttonNumber+1) + ") " + registration.getValue() + "   ...   " + completedLevels + " Levels completed");
            profileButton.setTextColor(new Color(0xF9EE35));

            buttons.AddElement(profileButton);

            buttonNumber ++;
        }

    }

    public void Draw(Graphics g){
        Font mainFont = new Font("arial", Font.BOLD, 40);
        Pair<Integer, Integer> fontSize = this.getFontSize(g, mainFont, "Select Profile");
        g.setFont(mainFont);
        g.setColor(Color.black);
        g.drawString("Select Profile", Game.GAME_WINDOW_WIDTH/2 - fontSize.getKey()/2 + 2, 80 + 2);
        g.setColor(Color.white);
        g.drawString("Select Profile", Game.GAME_WINDOW_WIDTH/2 - fontSize.getKey()/2, 80 );

        buttons.Draw(g);
    }

    public ButtonCollection getButtons() {
        return buttons;
    }
}
