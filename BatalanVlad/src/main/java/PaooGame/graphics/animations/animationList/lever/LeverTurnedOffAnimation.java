package PaooGame.graphics.animations.animationList.lever;

import PaooGame.graphics.ImageLoader;
import PaooGame.graphics.animations.animationList.IAnimation;

public class LeverTurnedOffAnimation extends IAnimation {

    public LeverTurnedOffAnimation(){
        imagesArray.add( ImageLoader.LoadImage("/textures/Objects/Switch (2).png"));
    }

    @Override
    public String getAnimationName() {
        return "TurnedOff";
    }
}
