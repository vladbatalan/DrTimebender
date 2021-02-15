package PaooGame.GameWindow.Button.ButtonTypes;

import PaooGame.Game;
import PaooGame.GameStates;
import PaooGame.GameWindow.Button.MyButton;
import PaooGame.GameWindow.Button.ButtonType;

import java.awt.*;

public class HelpButton extends MyButton {

    public HelpButton(){
        this.type = ButtonType.HelpButton;
    }
    public HelpButton(Rectangle buttonBody, String buttonText, Point textPosition){
        this.buttonBody = buttonBody;
        this.buttonText = buttonText;
        this.textPosition = textPosition;
        this.type = ButtonType.HelpButton;
    }


    public HelpButton(Rectangle buttonBody, String buttonText, Point textPosition, Color backgroundColor, Color edgeColor, Color textColor, Integer padding, Font buttonFont)
    {
        super(buttonBody, buttonText,textPosition, backgroundColor, edgeColor, textColor, padding, buttonFont);
        this.type = ButtonType.HelpButton;
    }



    public void ButtonPressed() {
        Game.gameState = GameStates.HELP_MENU;
    }
}
