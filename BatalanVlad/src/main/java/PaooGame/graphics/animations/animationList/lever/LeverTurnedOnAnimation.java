package PaooGame.graphics.animations.animationList.lever;

import PaooGame.graphics.ImageLoader;
import PaooGame.graphics.animations.animationList.IAnimation;

public class LeverTurnedOnAnimation extends IAnimation {

    public LeverTurnedOnAnimation(){
        imagesArray.add( ImageLoader.LoadImage("/textures/Objects/Switch (1).png"));
    }

    @Override
    public String getAnimationName() {
        return "TurnedOn";
    }
}
