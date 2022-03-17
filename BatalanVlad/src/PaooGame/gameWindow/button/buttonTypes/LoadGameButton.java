package PaooGame.gameWindow.button.buttonTypes;

import PaooGame.Game;
import PaooGame.GameStates;
import PaooGame.gameWindow.button.ButtonType;
import PaooGame.gameWindow.button.MyButton;

import java.awt.*;

public class LoadGameButton extends MyButton {

    public LoadGameButton(){
        this.type = ButtonType.LoadGameButton;
    }
    public LoadGameButton(Rectangle buttonBody, String buttonText){
        super(buttonBody, buttonText);
        this.type = ButtonType.LoadGameButton;
    }
    public LoadGameButton(Rectangle buttonBody, String buttonText, Point textPosition){
        this.buttonBody = buttonBody;
        this.buttonText = buttonText;
        this.textPosition = textPosition;
        this.type = ButtonType.LoadGameButton;
    }



    public LoadGameButton(Rectangle buttonBody, String buttonText, Point textPosition, Color backgroundColor, Color edgeColor, Color textColor, Integer padding, Font buttonFont)
    {
        super(buttonBody, buttonText,textPosition, backgroundColor, edgeColor, textColor, padding, buttonFont);
        this.type = ButtonType.LoadGameButton;
    }



    public void ButtonPressed() {
        Game.profileSelectionMenu.getButtons().ClearButtons();
        Game.profileSelectionMenu.InitMenu();
        Game.gameState = GameStates.PROFILE_SELECTION_MENU;
    }
}
