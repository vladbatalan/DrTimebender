package PaooGame.graphics.animations.animationList.objective;

import PaooGame.graphics.ImageLoader;
import PaooGame.graphics.animations.animationList.IAnimation;

public class ObjectiveClosedAnimation extends IAnimation {

    public ObjectiveClosedAnimation(){
        imagesArray.add( ImageLoader.LoadImage("/textures/Objects/DoorLocked.png"));
    }

    @Override
    public String getAnimationName() {
        return "ClosedAnimation";
    }
}
