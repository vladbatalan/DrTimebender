package PaooGame.graphics.gameWindow.button.buttonTypes;

import PaooGame.Game;
import PaooGame.GameStates;
import PaooGame.graphics.gameWindow.button.ButtonType;
import PaooGame.graphics.gameWindow.button.MyButton;

import java.awt.*;

public class ResetLevelOnWinButton extends MyButton {

    public ResetLevelOnWinButton(){
        this.type = ButtonType.RestartGameButton;
    }

    public ResetLevelOnWinButton(Rectangle buttonBody, String buttonText){
        super(buttonBody, buttonText);
        this.type = ButtonType.RestartGameButton;
    }


    public ResetLevelOnWinButton(Rectangle buttonBody, String buttonText, Point textPosition){
        this.buttonBody = buttonBody;
        this.buttonText = buttonText;
        this.textPosition = textPosition;
        this.type = ButtonType.RestartGameButton;
    }


    public ResetLevelOnWinButton(Rectangle buttonBody, String buttonText, Point textPosition, Color backgroundColor, Color edgeColor, Color textColor, Integer padding, Font buttonFont)
    {
        super(buttonBody, buttonText,textPosition, backgroundColor, edgeColor, textColor, padding, buttonFont);
        this.type = ButtonType.RestartGameButton;
    }



    public void ButtonPressed(Game game) {
        game.gameState = GameStates.GAME;
        game.currentLevel.getHandler().clearOldInstances();
        game.currentLevel.resetLevel();
    }
}
