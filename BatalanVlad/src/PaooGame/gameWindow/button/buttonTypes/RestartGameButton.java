package PaooGame.gameWindow.button.buttonTypes;

import PaooGame.Game;
import PaooGame.gameWindow.button.ButtonType;
import PaooGame.gameWindow.button.MyButton;

import java.awt.*;

public class RestartGameButton extends MyButton {

    public RestartGameButton() {
        this.type = ButtonType.RestartGameButton;
        initButtonStats();
    }

    public RestartGameButton(Rectangle buttonBody, String buttonText){
        super(buttonBody, buttonText);
        this.type = ButtonType.RestartGameButton;
    }

    public RestartGameButton(Rectangle buttonBody, String buttonText, Point textPosition) {
        this.buttonBody = buttonBody;
        this.buttonText = buttonText;
        this.textPosition = textPosition;
        this.type = ButtonType.RestartGameButton;
        initButtonStats();
    }


    public RestartGameButton(Rectangle buttonBody, String buttonText, Point textPosition, Color backgroundColor, Color edgeColor, Color textColor, Integer padding, Font buttonFont) {
        super(buttonBody, buttonText, textPosition, backgroundColor, edgeColor, textColor, padding, buttonFont);
        this.type = ButtonType.RestartGameButton;
    }


    private void initButtonStats() {
        buttonFont = new Font("arial", Font.BOLD, 14);
        backgroundColor = new Color(0x38, 0x81, 0x82, 150);
        edgeColor = Color.black;
        textColor = new Color(0xFFEDF6);
        padding = 5;
    }

    public void ButtonPressed() {
        if (Game.currentLevel.getLevelRunningState() && !Game.currentLevel.getOnResetState()) {

            Game.currentLevel.getHandler().clearOldInstances();
            Game.currentLevel.resetLevel();
        }
    }
}
