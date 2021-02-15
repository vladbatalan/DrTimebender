package PaooGame.Graphics.Animations.AnimationList.TimeMachine;

import PaooGame.Graphics.Animations.AnimationList.IAnimation;
import PaooGame.Graphics.ImageLoader;
import PaooGame.Graphics.SpriteSheet;

public class TimeMachineStandAnimation extends IAnimation {

    public TimeMachineStandAnimation(){
        imagesArray.add( ImageLoader.LoadImage("/textures/Objects/Time_Machine_HD.png"));
    }

    @Override
    public String getAnimationName() {
        return "StandAnimation";
    }
}
