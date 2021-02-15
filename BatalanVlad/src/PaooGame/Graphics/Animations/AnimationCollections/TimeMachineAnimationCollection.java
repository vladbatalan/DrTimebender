package PaooGame.Graphics.Animations.AnimationCollections;

import PaooGame.Graphics.Animations.AnimationList.TimeMachine.TimeMachineStandAnimation;

public class TimeMachineAnimationCollection extends AnimationCollection {
    public TimeMachineAnimationCollection(){
        this.addAnimation(new TimeMachineStandAnimation());
    }
}
