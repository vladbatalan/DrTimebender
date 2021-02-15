package PaooGame.GameObjects.EffectObjects;

import PaooGame.ActionTimers.ITimerInterupter;
import PaooGame.Game;
import PaooGame.GameObjects.ObjectID;
import PaooGame.GameObjects.StillObjects.StillObject;
import PaooGame.Physics.Body;
import PaooGame.Physics.PVector;
import PaooGame.Tiles.Map;

import java.awt.*;
import java.util.ArrayList;

// TurnOnObjectEffect is special designed to be created when a switch changes it's state and tundOn an object
// The effect created is the one that at the starting point this object will be created and will move
//                                                 in timeUntilDissapear ticks over the destination (endPosition)

public class TurnOnObjectEffect extends StillObject {
    private PVector velocity = new PVector();
    private PVector endPosition = new PVector();
    private int timeUntilDisappear = 30;

    public TurnOnObjectEffect(PVector startPosition, PVector endPosition){
        this.id = ObjectID.TurnOnObjectEffect;
        this.body = new Body(startPosition, 5, 5, 50);
        this.body.setMobility(false);
        this.body.setBodyColor(new Color(0x5BFF6A));


        this.endPosition = endPosition;
        // calculate the velocity based on the dissapear time and start and destination
        this.velocity = endPosition.sub(startPosition);
        // ajusting the velocity to execute exactly time UntilDissapear units of movement
        this.velocity = this.velocity.scalarMultiply(1/(float) timeUntilDisappear);
    }

    public void Draw(Graphics g) {
        body.Draw(g);
    }

    public void Update(Map currentMap) {
        PVector oldPosition = body.getPosition();
        float oldDistance = oldPosition.distanceTo(endPosition);

        // evaluate new position
        body.setPosition(oldPosition.add(velocity));

        // evaluate new distance
        float newDistance = body.getPosition().distanceTo(endPosition);

        boolean destroyTheObject = false;
        // we have passed by the destination, initiate the distruction of the object
        if(oldDistance < newDistance){
            destroyTheObject = true;
        }

        if(destroyTheObject){
            Game.currentLevel.getHandler().getStillObjects().remove(this);
        }

    }

    public int getTimeUntilDisappear(){
        return timeUntilDisappear;
    }
}
