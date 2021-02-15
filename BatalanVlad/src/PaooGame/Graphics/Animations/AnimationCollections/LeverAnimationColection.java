package PaooGame.Graphics.Animations.AnimationCollections;

import PaooGame.Graphics.Animations.AnimationList.Lever.LeverTurnedOffAnimation;
import PaooGame.Graphics.Animations.AnimationList.Lever.LeverTurnedOnAnimation;

public class LeverAnimationColection extends AnimationCollection {
    public LeverAnimationColection(){
        this.addAnimation(new LeverTurnedOffAnimation());
        this.addAnimation(new LeverTurnedOnAnimation());
    }
}
