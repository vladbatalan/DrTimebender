package PaooGame.graphics.animations.animationCollections;

import PaooGame.graphics.animations.animationList.pushButton.PushGreenButtonTurnOffType1;
import PaooGame.graphics.animations.animationList.pushButton.PushGreenButtonTurnOnType1;

public class PushGreenButtonType1AnimationCollection extends AnimationCollection {
    public PushGreenButtonType1AnimationCollection(){
        this.addAnimation(new PushGreenButtonTurnOnType1());
        this.addAnimation(new PushGreenButtonTurnOffType1());
    }
}
