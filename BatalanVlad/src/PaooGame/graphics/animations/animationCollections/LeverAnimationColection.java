package PaooGame.graphics.animations.animationCollections;

import PaooGame.graphics.animations.animationList.lever.LeverTurnedOffAnimation;
import PaooGame.graphics.animations.animationList.lever.LeverTurnedOnAnimation;

public class LeverAnimationColection extends AnimationCollection {
    public LeverAnimationColection(){
        this.addAnimation(new LeverTurnedOffAnimation());
        this.addAnimation(new LeverTurnedOnAnimation());
    }
}
