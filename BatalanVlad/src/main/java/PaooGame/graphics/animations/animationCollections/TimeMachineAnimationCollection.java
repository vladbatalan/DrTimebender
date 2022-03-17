package PaooGame.graphics.animations.animationCollections;

import PaooGame.graphics.animations.animationList.timeMachine.TimeMachineStandAnimation;

public class TimeMachineAnimationCollection extends AnimationCollection {
    public TimeMachineAnimationCollection(){
        this.addAnimation(new TimeMachineStandAnimation());
    }
}
