package PaooGame.GameWindow.Button.ButtonTypes;

import PaooGame.Game;
import PaooGame.GameStates;
import PaooGame.GameWindow.Button.ButtonType;
import PaooGame.GameWindow.Button.MyButton;

import java.awt.*;

public class NextLevelButton extends MyButton {

    public NextLevelButton(){
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



    public void ButtonPressed() {
        // If this is not the last level
        if(Game.currentLevel.getNextLevel() != null){
            Game.currentLevel = Game.currentLevel.getNextLevel();
            Game.currentLevel.InitLevel();
            Game.gameState = GameStates.GAME;
        }
        // This is last level
        else{
            Game.gameState = GameStates.VICTORY_MENU;
        }

    }
}
