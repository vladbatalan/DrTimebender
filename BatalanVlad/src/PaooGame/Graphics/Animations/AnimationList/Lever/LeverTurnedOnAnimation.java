package PaooGame.Graphics.Animations.AnimationList.Lever;

import PaooGame.Graphics.Animations.AnimationList.IAnimation;
import PaooGame.Graphics.ImageLoader;

public class LeverTurnedOnAnimation extends IAnimation {

    public LeverTurnedOnAnimation(){
        imagesArray.add( ImageLoader.LoadImage("/textures/Objects/Switch (1).png"));
    }

    @Override
    public String getAnimationName() {
        return "TurnedOn";
    }
}
