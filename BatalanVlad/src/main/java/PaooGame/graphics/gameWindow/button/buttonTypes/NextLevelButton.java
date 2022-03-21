package PaooGame.graphics.gameWindow.button.buttonTypes;

import PaooGame.Game;
import PaooGame.GameStates;
import PaooGame.graphics.gameWindow.button.ButtonType;
import PaooGame.graphics.gameWindow.button.MyButton;

import java.awt.*;

public class NextLevelButton extends MyButton {

    public NextLevelButton(){
        this.type = ButtonType.NextLevelButton;
    }
    public NextLevelButton(Rectangle buttonBody, String buttonText){
        super(buttonBody, buttonText);
        this.type = ButtonType.NextLevelButton;
    }
    public NextLevelButton(Rectangle buttonBody, String buttonText, Point textPosition){
        this.buttonBody = buttonBody;
        this.buttonText = buttonText;
        this.textPosition = textPosition;
        this.type = ButtonType.NextLevelButton;
    }


    public NextLevelButton(Rectangle buttonBody, String buttonText, Point textPosition, Color backgroundColor, Color edgeColor, Color textColor, Integer padding, Font buttonFont)
    {
        super(buttonBody, buttonText,textPosition, backgroundColor, edgeColor, textColor, padding, buttonFont);
        this.type = ButtonType.NextLevelButton;
    }


    public void ButtonPressed(Game game) {
        // If this is not the last level
        if(game.currentLevel.getNextLevel() != null){
            game.currentLevel = game.currentLevel.getNextLevel();
            game.currentLevel.InitLevel();
            game.gameState = GameStates.GAME;
        }
        // This is last level
        else{
            game.gameState = GameStates.VICTORY_MENU;
        }

    }
}
