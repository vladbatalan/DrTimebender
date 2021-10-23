package PaooGame.graphics.animations.animationCollections;

import PaooGame.graphics.animations.animationList.pushButton.PushRedButtonTurnOffType1;
import PaooGame.graphics.animations.animationList.pushButton.PushRedButtonTurnOnType1;

public class PushRedButtonType1AnimationCollection extends AnimationCollection {
    public PushRedButtonType1AnimationCollection(){
        this.addAnimation(new PushRedButtonTurnOnType1());
        this.addAnimation(new PushRedButtonTurnOffType1());
    }
}
