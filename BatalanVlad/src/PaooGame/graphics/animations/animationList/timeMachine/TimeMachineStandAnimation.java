package PaooGame.graphics.animations.animationList.timeMachine;

import PaooGame.graphics.ImageLoader;
import PaooGame.graphics.animations.animationList.IAnimation;

public class TimeMachineStandAnimation extends IAnimation {

    public TimeMachineStandAnimation(){
        imagesArray.add( ImageLoader.LoadImage("/textures/Objects/Time_Machine_HD.png"));
    }

    @Override
    public String getAnimationName() {
        return "StandAnimation";
    }
}
