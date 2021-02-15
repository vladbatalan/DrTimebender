package PaooGame.Graphics.Animations.AnimationList.Lever;

import PaooGame.Graphics.Animations.AnimationList.IAnimation;
import PaooGame.Graphics.ImageLoader;

public class LeverTurnedOffAnimation extends IAnimation {

    public LeverTurnedOffAnimation(){
        imagesArray.add( ImageLoader.LoadImage("/textures/Objects/Switch (2).png"));
    }

    @Override
    public String getAnimationName() {
        return "TurnedOff";
    }
}
