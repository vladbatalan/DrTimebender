package PaooGame.Graphics.Animations.AnimationCollections;

import PaooGame.Graphics.Animations.AnimationList.Objective.ObjectiveClosedAnimation;
import PaooGame.Graphics.Animations.AnimationList.Objective.ObjectiveOpenAnimation;

public class ObjectiveAnimationCollection extends AnimationCollection {
    public ObjectiveAnimationCollection(){
        this.addAnimation(new ObjectiveOpenAnimation());
        this.addAnimation(new ObjectiveClosedAnimation());
    }
}
