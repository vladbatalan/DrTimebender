package PaooGame.Graphics.Animations.AnimationList.Objective;

import PaooGame.Graphics.Animations.AnimationList.IAnimation;
import PaooGame.Graphics.ImageLoader;

public class ObjectiveClosedAnimation extends IAnimation {

    public ObjectiveClosedAnimation(){
        imagesArray.add( ImageLoader.LoadImage("/textures/Objects/DoorLocked.png"));
    }

    @Override
    public String getAnimationName() {
        return "ClosedAnimation";
    }
}
