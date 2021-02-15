package PaooGame.Graphics.Animations.AnimationList.Objective;

import PaooGame.Graphics.Animations.AnimationList.IAnimation;
import PaooGame.Graphics.ImageLoader;

public class ObjectiveOpenAnimation extends IAnimation {

    public ObjectiveOpenAnimation(){
        imagesArray.add( ImageLoader.LoadImage("/textures/Objects/DoorOpen.png"));
    }

    @Override
    public String getAnimationName() {
        return "OpenAnimation";
    }
}
