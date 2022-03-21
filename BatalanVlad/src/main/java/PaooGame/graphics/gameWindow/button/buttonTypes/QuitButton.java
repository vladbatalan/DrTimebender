package PaooGame.graphics.gameWindow.button.buttonTypes;

import PaooGame.Game;
import PaooGame.graphics.gameWindow.button.ButtonType;
import PaooGame.graphics.gameWindow.button.MyButton;

import java.awt.*;

public class QuitButton extends MyButton {

    public QuitButton(){
        this.type = ButtonType.QuitButton;
    }

    public QuitButton(Rectangle buttonBody, String buttonText){
        super(buttonBody, buttonText);
        this.type = ButtonType.QuitButton;
    }

    public QuitButton(Rectangle buttonBody, String buttonText, Point textPosition){
        this.buttonBody = buttonBody;
        this.buttonText = buttonText;
        this.textPosition = textPosition;
        this.type = ButtonType.QuitButton;
    }


    public QuitButton(Rectangle buttonBody, String buttonText, Point textPosition, Color backgroundColor, Color edgeColor, Color textColor, Integer padding, Font buttonFont)
    {
        super(buttonBody, buttonText,textPosition, backgroundColor, edgeColor, textColor, padding, buttonFont);
        this.type = ButtonType.QuitButton;
    }



    public void ButtonPressed(Game game) {
        System.exit(0);
    }
}
