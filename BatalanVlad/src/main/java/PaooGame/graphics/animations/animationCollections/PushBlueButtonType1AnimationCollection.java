package PaooGame.graphics.animations.animationCollections;

import PaooGame.graphics.animations.animationList.pushButton.PushBlueButtonTurnOffType1;
import PaooGame.graphics.animations.animationList.pushButton.PushBlueButtonTurnOnType1;

public class PushBlueButtonType1AnimationCollection extends AnimationCollection {
    public PushBlueButtonType1AnimationCollection(){
        this.addAnimation(new PushBlueButtonTurnOnType1());
        this.addAnimation(new PushBlueButtonTurnOffType1());
    }
}
