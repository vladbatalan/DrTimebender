package PaooGame.graphics.animations.animationList;

import PaooGame.physics.Body;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public abstract class IAnimation {
    protected ArrayList<BufferedImage> imagesArray = new ArrayList<>();

    //timer
    protected int animationTimer = 0;
    protected int frameDelay = 0;
    protected int waitTime = 5;

    public void displayAnimation(Body body, Graphics g){
        g.drawImage(imagesArray.get(animationTimer), (int)body.getPosition().getX(), (int)body.getPosition().getY(), body.getBodyWidth(), body.getBodyHeight(), null);
        UpdateTimer();
    }

    protected void UpdateTimer(){
        int arraySize = imagesArray.size();
        if(arraySize == 0){
            animationTimer = 0;
            return;
        }

        frameDelay = (frameDelay + 1) % waitTime;
        if(frameDelay == 0)
            animationTimer = (animationTimer + 1) % arraySize;
    }

    public abstract String getAnimationName();
}
