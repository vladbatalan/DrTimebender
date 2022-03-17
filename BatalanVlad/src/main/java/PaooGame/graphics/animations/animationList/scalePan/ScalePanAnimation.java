package PaooGame.graphics.animations.animationList.scalePan;

import PaooGame.graphics.ImageLoader;
import PaooGame.graphics.animations.animationList.IAnimation;
import PaooGame.physics.Body;

import java.awt.*;

public class ScalePanAnimation extends IAnimation {
    private Color backgroundColor;
    public ScalePanAnimation(){
        imagesArray.add( ImageLoader.LoadImage("/textures/Objects/strippedYellowBlackLines.png"));
        backgroundColor = new Color(0x090D58);
    }
    public ScalePanAnimation(Color myColor){
        imagesArray.add( ImageLoader.LoadImage("/textures/Objects/strippedYellowBlackLines.png"));
        backgroundColor = myColor;
    }
    public void displayAnimation(Body body, Graphics g){
        g.drawImage(imagesArray.get(animationTimer), (int)body.getPosition().getX(), (int)body.getPosition().getY(), body.getBodyWidth(), body.getBodyHeight()*3/4, null);
        g.setColor(backgroundColor);
        g.fillRect((int)body.getPosition().getX(), (int)body.getPosition().getY() + body.getBodyHeight()*3/4, body.getBodyWidth(), body.getBodyHeight()/4);
        UpdateTimer();
    }

    @Override
    public String getAnimationName() {
        return "ScalePanAnimation";
    }
}
