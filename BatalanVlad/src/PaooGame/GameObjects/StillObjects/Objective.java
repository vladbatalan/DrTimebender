package PaooGame.GameObjects.StillObjects;

import PaooGame.GameObjects.ISwitchable;
import PaooGame.GameObjects.ObjectID;
import PaooGame.Graphics.Animations.AnimationCollections.ObjectiveAnimationCollection;
import PaooGame.Physics.Body;
import PaooGame.Physics.PointVector;
import PaooGame.Tiles.Map;

import java.awt.*;

public class Objective extends StillObject implements ISwitchable {

    // tells if the door to the next level is open or not
    private boolean isActive;

    public Objective(PointVector position, boolean isInitiallyActive){
        this.id = ObjectID.Objective;
        this.body = new Body(position, 40, 80, 50);
        this.body.setMobility(false);
        this.body.setBodyColor(new Color(0,0,0,0));
        this.animation = new ObjectiveAnimationCollection();
        this.isActive = isInitiallyActive;
    }

    @Override
    public void Draw(Graphics g) {
        if(isActive)
            animation.displayAnimation("OpenAnimation", body, g);
        else
            animation.displayAnimation("ClosedAnimation", body, g);
    }

    @Override
    public void Update(Map currentMap) {

    }

    @Override
    public void turnOn(String command) {
        isActive = true;
    }

    @Override
    public void turnOff(String command) {
        isActive = false;
    }

    public boolean getActiveState(){
        return isActive;
    }
    public PointVector getSwitchablePosition(){
        return body.getPosition();
    }
}
