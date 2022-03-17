package PaooGame.gameObjects.effectObjects;

import PaooGame.Game;
import PaooGame.gameObjects.ObjectID;
import PaooGame.gameObjects.stillObjects.StillObject;
import PaooGame.physics.Body;
import PaooGame.physics.PointVector;
import PaooGame.tiles.Map;

import java.awt.*;

// TurnOnObjectEffect is special designed to be created when a switch changes it's state and tundOn an object
// The effect created is the one that at the starting point this object will be created and will move
//                                                 in timeUntilDissapear ticks over the destination (endPosition)

public class TurnOffObjectEffect extends StillObject {
    private PointVector velocity;
    private PointVector endPosition;
    private int timeUntilDisappear = 30;

    public TurnOffObjectEffect(PointVector startPosition, PointVector endPosition){
        this.id = ObjectID.TurnOffObjectEffect;
        this.body = new Body(startPosition, 5, 5, 50);
        this.body.setMobility(false);
        this.body.setBodyColor(new Color(0xFF0002));


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
        PointVector oldPosition = body.getPosition();
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
