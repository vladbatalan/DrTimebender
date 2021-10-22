package PaooGame.graphics.animations.animationList.objective;

import PaooGame.graphics.ImageLoader;
import PaooGame.graphics.animations.animationList.IAnimation;

public class ObjectiveOpenAnimation extends IAnimation {

    public ObjectiveOpenAnimation(){
        imagesArray.add( ImageLoader.LoadImage("/textures/Objects/DoorOpen.png"));
    }

    @Override
    public String getAnimationName() {
        return "OpenAnimation";
    }
}
