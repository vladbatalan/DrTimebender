package PaooGame.graphics.gameWindow.button.buttonTypes;

import PaooGame.Game;
import PaooGame.GameStates;
import PaooGame.actionTimers.actions.MouseInputPauseFlagOn;
import PaooGame.actionTimers.timer.DelayedActionTimer;
import PaooGame.graphics.gameWindow.button.ButtonType;
import PaooGame.graphics.gameWindow.button.MyButton;
import PaooGame.input.MouseInput;

import java.awt.*;

//import sun.rmi.runtime.NewThreadAction;

public class ProfileSelectButton extends MyButton {
    private int userId;
    private String userName;

    public ProfileSelectButton(){
        this.type = ButtonType.ProfileSelectButton;
    }
    public ProfileSelectButton(Rectangle buttonBody, String buttonText){
        super(buttonBody, buttonText);
        this.type = ButtonType.ProfileSelectButton;
    }

    public ProfileSelectButton(Rectangle buttonBody, String buttonText, Point textPosition){
        this.buttonBody = buttonBody;
        this.buttonText = buttonText;
        this.textPosition = textPosition;
        this.type = ButtonType.ProfileSelectButton;
    }
    public ProfileSelectButton(Rectangle buttonBody, String buttonText, Point textPosition, Color backgroundColor, Color edgeColor, Color textColor, Integer padding, Font buttonFont)
    {
        super(buttonBody, buttonText,textPosition, backgroundColor, edgeColor, textColor, padding, buttonFont);
        this.type = ButtonType.ProfileSelectButton;
    }

    public void ButtonPressed(Game game) {
        game.userId = userId;
        game.levelMenu.setMaxAvailableLevels(game.database.CheckLevelsCompleted(userId));
        game.gameState = GameStates.LEVEL_MENU;
        MouseInput.mouseInputPause = true;

        MouseInputPauseFlagOn mouseInputPauseFlagOn = new MouseInputPauseFlagOn();
        DelayedActionTimer endPause = new DelayedActionTimer(mouseInputPauseFlagOn, 30);
        endPause.startTimer();
    }

    public void setUserData(int userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }
    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }
}
