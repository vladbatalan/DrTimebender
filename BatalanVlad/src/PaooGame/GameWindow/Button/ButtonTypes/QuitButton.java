package PaooGame.GameWindow.Button.ButtonTypes;

import PaooGame.Game;
import PaooGame.GameWindow.Button.MyButton;
import PaooGame.GameWindow.Button.ButtonType;

import java.awt.*;

public class QuitButton extends MyButton {

    public QuitButton(){
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



    public void ButtonPressed() {
        Game.database.Close();
        System.exit(0);
    }
}
