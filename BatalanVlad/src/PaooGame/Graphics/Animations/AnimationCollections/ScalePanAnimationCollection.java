package PaooGame.Graphics.Animations.AnimationCollections;

import PaooGame.Graphics.Animations.AnimationList.ScalePan.ScalePanAnimation;

import java.awt.*;

public class ScalePanAnimationCollection extends AnimationCollection {
    public ScalePanAnimationCollection(){
        this.addAnimation(new ScalePanAnimation());
    }
    public ScalePanAnimationCollection(Color myBackgroundColor){
        this.addAnimation(new ScalePanAnimation(myBackgroundColor));
    }
}
