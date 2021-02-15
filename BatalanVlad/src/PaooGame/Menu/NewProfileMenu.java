package PaooGame.Menu;

import PaooGame.Game;
import PaooGame.GameWindow.Button.ButtonCollection;
import PaooGame.GameWindow.Button.ButtonTypes.*;
import PaooGame.GameWindow.Button.MyButton;
import javafx.util.Pair;

import java.awt.*;

public class NewProfileMenu extends Menu{

    private String profileName = "";
    private String errorString = "";

    public NewProfileMenu(){
        InitMenu();
    }

    public void InitMenu(){
        int distanceToTop = Game.GAME_WINDOW_HEIGHT/2;
        int distanceToLeft = Game.GAME_WINDOW_WIDTH/2;
        int buttonWidth = 130;
        int buttonHeight = 40;

        MyButton startGame = new StartGameButton(
                new Rectangle(distanceToLeft-buttonWidth/2, distanceToTop, buttonWidth, buttonHeight),
                "Start Game",
                new Point()
        );

        MyButton returnMenu = new ReturnMainMenuButton(
                new Rectangle(distanceToLeft-buttonWidth/2 - 15 , Game.GAME_WINDOW_HEIGHT - 100 , buttonWidth + 30, buttonHeight),
                "Back to Menu",
                new Point()

        );

        buttons.AddElement(startGame);
        buttons.AddElement(returnMenu);
    }

    public void Draw(Graphics g){
        Font titleFont = new Font("arial", Font.PLAIN, 40);

        Pair<Integer, Integer> fontSize = this.getFontSize(g, titleFont, "Choose your name:");
        g.setFont(titleFont);
        g.setColor(Color.black);
        g.drawString("Choose your name:",Game.GAME_WINDOW_WIDTH/2 - fontSize.getKey()/2+2, 180+2 );
        g.setColor(Color.white);
        g.drawString("Choose your name:",Game.GAME_WINDOW_WIDTH/2 - fontSize.getKey()/2, 180 );


        Font profileFont = new Font("arial", Font.BOLD, 30);
        fontSize = this.getFontSize(g, profileFont, profileName);
        g.setFont(profileFont);
        g.setColor(Color.white);
        g.fillRect(Game.GAME_WINDOW_WIDTH/2 - 400/2, 250-fontSize.getValue(), 400, fontSize.getValue() + 10);

        g.setColor(Color.black);
        g.drawString(profileName,Game.GAME_WINDOW_WIDTH/2 - fontSize.getKey()/2 + 2, 250 + 1);
        g.setColor(new Color(0xDC882E));
        g.drawString(profileName,Game.GAME_WINDOW_WIDTH/2 - fontSize.getKey()/2, 250 );


        Font errorFont = new Font("arial", Font.BOLD, 20);
        fontSize = this.getFontSize(g, errorFont, errorString);
        g.setFont(errorFont);
        g.setColor(Color.black);
        g.drawString(errorString,Game.GAME_WINDOW_WIDTH/2 - fontSize.getKey()/2 + 1, 400 + 1);
        g.setColor(new Color(0xFF0002));
        g.drawString(errorString,Game.GAME_WINDOW_WIDTH/2 - fontSize.getKey()/2, 400 );

        buttons.Draw(g);
    }

    public ButtonCollection getButtons() {
        return buttons;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }
    public String getProfileName() {
        return profileName;
    }
    public void setErrorString(String errorString) {
        this.errorString = errorString;
    }
}
