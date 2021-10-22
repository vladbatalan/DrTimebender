package PaooGame.graphics.animations.animationCollections;

import PaooGame.graphics.animations.animationList.objective.ObjectiveClosedAnimation;
import PaooGame.graphics.animations.animationList.objective.ObjectiveOpenAnimation;

public class ObjectiveAnimationCollection extends AnimationCollection {
    public ObjectiveAnimationCollection(){
        this.addAnimation(new ObjectiveOpenAnimation());
        this.addAnimation(new ObjectiveClosedAnimation());
    }
}
