package PaooGame.graphics.gameWindow.button.buttonTypes;

import PaooGame.Game;
import PaooGame.GameStates;
import PaooGame.graphics.gameWindow.button.ButtonType;
import PaooGame.graphics.gameWindow.button.MyButton;

import java.awt.*;

public class NewGameButton extends MyButton {

    public NewGameButton(){
        this.type = ButtonType.NewGameButton;
    } public NewGameButton(Rectangle buttonBody, String buttonText){
        super(buttonBody, buttonText);
        this.type = ButtonType.NewGameButton;
    }
    public NewGameButton(Rectangle buttonBody, String buttonText, Point textPosition){
        this.buttonBody = buttonBody;
        this.buttonText = buttonText;
        this.textPosition = textPosition;
        this.type = ButtonType.NewGameButton;
    }


    public NewGameButton(Rectangle buttonBody, String buttonText, Point textPosition, Color backgroundColor, Color edgeColor, Color textColor, Integer padding, Font buttonFont)
    {
        super(buttonBody, buttonText,textPosition, backgroundColor, edgeColor, textColor, padding, buttonFont);
        this.type = ButtonType.NewGameButton;
    }

    public void ButtonPressed() {
        Game.gameState = GameStates.NEW_PROFILE_MENU;
    }
}
