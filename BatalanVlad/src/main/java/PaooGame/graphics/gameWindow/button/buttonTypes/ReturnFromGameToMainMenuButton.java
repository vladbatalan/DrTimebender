package PaooGame.graphics.gameWindow.button.buttonTypes;

import PaooGame.Game;
import PaooGame.GameStates;
import PaooGame.graphics.gameWindow.button.ButtonType;
import PaooGame.graphics.gameWindow.button.MyButton;

import java.awt.*;

public class ReturnFromGameToMainMenuButton extends MyButton {

    public ReturnFromGameToMainMenuButton(){
        this.type = ButtonType.ReturnMainMenuButton;
        initButtonStats();
    }
    public ReturnFromGameToMainMenuButton(Rectangle buttonBody, String buttonText){
        super(buttonBody, buttonText);
        this.type = ButtonType.ReturnMainMenuButton;
        initButtonStats();
    }

    public ReturnFromGameToMainMenuButton(Rectangle buttonBody, String buttonText, Point textPosition){
        this.buttonBody = buttonBody;
        this.buttonText = buttonText;
        this.textPosition = textPosition;
        this.type = ButtonType.ReturnMainMenuButton;
        initButtonStats();
    }


    public ReturnFromGameToMainMenuButton(Rectangle buttonBody, String buttonText, Point textPosition, Color backgroundColor, Color edgeColor, Color textColor, Integer padding, Font buttonFont)
    {
        super(buttonBody, buttonText,textPosition, backgroundColor, edgeColor, textColor, padding, buttonFont);
        this.type = ButtonType.ReturnMainMenuButton;
    }


    private void initButtonStats(){
        buttonFont = new Font("arial", Font.BOLD, 14);
        backgroundColor = new Color(0x38,0x81,0x82, 150);
        edgeColor = Color.black;
        textColor = new Color(0xFFEDF6);
        padding = 5;
    }

    public void ButtonPressed(Game game) {
        game.gameState = GameStates.MENU;
        game.currentLevel.getGameTimer().stopTimer();
        game.currentLevel.getGameTimer().hideTimer();
        game.currentLevel = null;

    }
}
