package PaooGame.graphics.animations.animationCollections;

import PaooGame.graphics.animations.animationList.IAnimation;
import PaooGame.physics.Body;

import java.awt.*;
import java.util.ArrayList;

public abstract class AnimationCollection {
    private ArrayList<IAnimation> animationsList = new ArrayList<>();

    public void displayAnimation(String animationName, Body body, Graphics g) {
        for(IAnimation animation:animationsList){
            if(animation.getAnimationName().equals(animationName)){
                animation.displayAnimation(body, g);
                return;
            }
        }
    }

    public void addAnimation(IAnimation animation) {
        animationsList.add(animation);
    }

    public void removeAnimation(String animationName) {
        IAnimation removeAnimation = null;
        for(IAnimation animation:animationsList){
            if(animation.getAnimationName().equals(animationName))
            {
                removeAnimation = animation;
                break;
            }
        }
        if(removeAnimation != null)
            animationsList.remove(removeAnimation);
    }

    public ArrayList<IAnimation> getAnimationsList() {
        return animationsList;
    }
}
