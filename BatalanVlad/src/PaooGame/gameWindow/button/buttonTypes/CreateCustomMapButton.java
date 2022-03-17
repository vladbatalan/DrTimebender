package PaooGame.gameWindow.button.buttonTypes;

import PaooGame.Game;
import PaooGame.GameStates;
import PaooGame.gameWindow.button.ButtonType;
import PaooGame.gameWindow.button.MyButton;

import java.awt.*;

public class CreateCustomMapButton extends MyButton {

    public CreateCustomMapButton(){
        this.type = ButtonType.CreateCustomMapButton;
    }
    public CreateCustomMapButton(Rectangle buttonBody, String buttonText){
        super(buttonBody, buttonText);
        this.type = ButtonType.CreateCustomMapButton;
    }

    public CreateCustomMapButton(Rectangle buttonBody, String buttonText, Point textPosition){
        this.buttonBody = buttonBody;
        this.buttonText = buttonText;
        this.textPosition = textPosition;
        this.type = ButtonType.CreateCustomMapButton;
    }



    public CreateCustomMapButton(Rectangle buttonBody, String buttonText, Point textPosition, Color backgroundColor, Color edgeColor, Color textColor, Integer padding, Font buttonFont)
    {
        super(buttonBody, buttonText,textPosition, backgroundColor, edgeColor, textColor, padding, buttonFont);
        this.type = ButtonType.CreateCustomMapButton;
    }



    public void ButtonPressed() {
        Game.gameState = GameStates.MAP_CREATION;
    }
}
