package PaooGame.graphics.animations.animationCollections;

import PaooGame.graphics.animations.animationList.scalePan.ScalePanAnimation;

import java.awt.*;

public class ScalePanAnimationCollection extends AnimationCollection {
    public ScalePanAnimationCollection(){
        this.addAnimation(new ScalePanAnimation());
    }
    public ScalePanAnimationCollection(Color myBackgroundColor){
        this.addAnimation(new ScalePanAnimation(myBackgroundColor));
    }
}
