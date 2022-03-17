package PaooGame.gameWindow.button.buttonTypes;

import PaooGame.Game;
import PaooGame.GameStates;
import PaooGame.gameWindow.button.ButtonType;
import PaooGame.gameWindow.button.MyButton;
import PaooGame.levels.Level0;

import java.awt.*;

//import sun.rmi.runtime.NewThreadAction;

public class StartGameButton extends MyButton {

    public StartGameButton(){
        this.type = ButtonType.StartGameButton;
    }

    public StartGameButton(Rectangle buttonBody, String buttonText){
        super(buttonBody, buttonText);
        this.type = ButtonType.StartGameButton;
    }

    public StartGameButton(Rectangle buttonBody, String buttonText, Point textPosition){
        this.buttonBody = buttonBody;
        this.buttonText = buttonText;
        this.textPosition = textPosition;
        this.type = ButtonType.StartGameButton;
    }


    public StartGameButton(Rectangle buttonBody, String buttonText, Point textPosition, Color backgroundColor, Color edgeColor, Color textColor, Integer padding, Font buttonFont)
    {
        super(buttonBody, buttonText,textPosition, backgroundColor, edgeColor, textColor, padding, buttonFont);
        this.type = ButtonType.StartGameButton;
    }

    public void ButtonPressed() {
        // database comunication to be added
        String profileName = Game.newProfileMenu.getProfileName();

        if(profileName.length() == 0){
            Game.newProfileMenu.setErrorString("The name must be completed!");
            return;
        }

        int user_id = Game.database.InsertNewProfile(profileName);

        if(user_id == -1){
            Game.newProfileMenu.setErrorString("Name already exists!");
            return;
        }

        Game.userId = user_id;
        Game.newProfileMenu.setErrorString("");
        Game.setCurrentLevel(new Level0());
        Game.gameState = GameStates.GAME;
    }
}
