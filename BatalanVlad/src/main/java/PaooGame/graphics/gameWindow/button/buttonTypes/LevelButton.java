package PaooGame.graphics.gameWindow.button.buttonTypes;

import PaooGame.Game;
import PaooGame.GameStates;
import PaooGame.graphics.gameWindow.button.ButtonType;
import PaooGame.graphics.gameWindow.button.MyButton;
import PaooGame.graphics.ImageLoader;
import PaooGame.levels.Level;

import java.awt.*;
import java.awt.image.BufferedImage;

public class LevelButton extends MyButton {

    private boolean lockedState = false;
    private Level btnLevel;
    private BufferedImage lockedBackground = ImageLoader.LoadImage("/textures/Icons/level-locked.jpg");
    private BufferedImage unlockedBackground = ImageLoader.LoadImage("/textures/Icons/unlocked-level.png");

    public LevelButton(Level btnLevel){
        this.type = ButtonType.LevelButton;
        this.btnLevel = btnLevel;
    }
    public LevelButton(Rectangle buttonBody, String buttonText, Level btnLevel){
        super(buttonBody, buttonText);
        this.btnLevel = btnLevel;
        this.type = ButtonType.LoadGameButton;
    }

    public LevelButton(Rectangle buttonBody, String buttonText, Point textPosition, Level btnLevel){
        this.buttonBody = buttonBody;
        this.buttonText = buttonText;
        this.textPosition = textPosition;
        this.type = ButtonType.LoadGameButton;
        this.btnLevel = btnLevel;
    }


    public LevelButton(Rectangle buttonBody, String buttonText, Point textPosition, Color backgroundColor, Color edgeColor, Color textColor, Integer padding, Font buttonFont, Level btnLevel)
    {
        super(buttonBody, buttonText,textPosition, backgroundColor, edgeColor, textColor, padding, buttonFont);
        this.type = ButtonType.LoadGameButton;
        this.btnLevel = btnLevel;
    }

    public void Draw(Graphics g){
        int newButtonX = buttonBody.x;
        int newButtonY = buttonBody.y;

        //the button can follow a camera
        if(camera!=null){
            newButtonX += (int) camera.getCameraCoordinates().getX();
            newButtonY += (int) camera.getCameraCoordinates().getY();
        }


        if(!lockedState) {
            g.drawImage(unlockedBackground, newButtonX, newButtonY, buttonBody.width, buttonBody.height, null); //text of the button
        }
        else{
            g.drawImage(lockedBackground, newButtonX, newButtonY, buttonBody.width, buttonBody.height, null); //text of the button
        }

        //background of a button
        g.setColor(edgeColor);
        g.drawRect(newButtonX, newButtonY, buttonBody.width, buttonBody.height);
        g.setColor(backgroundColor);
        g.fillRect(newButtonX + 2, newButtonY + 2, buttonBody.width - 2, buttonBody.height - 2);

        //text of the button
        g.setColor(textColor);
        g.setFont(buttonFont);
        g.drawString(buttonText, newButtonX + padding, newButtonY + buttonBody.height - padding);
    }


    public void ButtonPressed() {
        // System.out.println(btnLevel.getClass().getName() + " button pressed!");

        if (!lockedState) {
            Game.setCurrentLevel(btnLevel);
            Game.gameState = GameStates.GAME;
        } else {
            System.out.println("Level is not locked yet!");
        }
    }

    public void setLockedState(boolean lockState) {
        this.lockedState = lockState;
    }
}
