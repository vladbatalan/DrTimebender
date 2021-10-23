package PaooGame.graphics.animations.animationCollections;

import PaooGame.graphics.animations.animationList.pushButton.PushYellowButtonTurnOffType1;
import PaooGame.graphics.animations.animationList.pushButton.PushYellowButtonTurnOnType1;

public class PushYellowButtonType1AnimationCollection extends AnimationCollection {
    public PushYellowButtonType1AnimationCollection(){
        this.addAnimation(new PushYellowButtonTurnOnType1());
        this.addAnimation(new PushYellowButtonTurnOffType1());
    }
}
