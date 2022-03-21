package PaooGame.graphics.gameWindow.button.buttonTypes;

import PaooGame.Game;
import PaooGame.GameStates;
import PaooGame.graphics.gameWindow.button.ButtonType;
import PaooGame.graphics.gameWindow.button.MyButton;
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

    public void ButtonPressed(Game game) {
        // database comunication to be added
        String profileName = game.newProfileMenu.getProfileName();

        if(profileName.length() == 0){
            game.newProfileMenu.setErrorString("The name must be completed!");
            return;
        }

        int user_id = game.database.InsertNewProfile(profileName);

        if(user_id == -1){
            game.newProfileMenu.setErrorString("Name already exists!");
            return;
        }

        game.userId = user_id;
        game.newProfileMenu.setErrorString("");
        game.setCurrentLevel(new Level0(game));
        game.gameState = GameStates.GAME;
    }
}
