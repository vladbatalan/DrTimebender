package PaooGame.GameObjects.StillObjects;

import PaooGame.GameObjects.ISwitchable;
import PaooGame.GameObjects.ObjectID;
import PaooGame.Physics.Body;
import PaooGame.Physics.PointVector;

import java.awt.*;

public class MovingPlatformSwitchable extends MovingPlatform implements ISwitchable {

    private boolean isActive;
    public MovingPlatformSwitchable(PointVector positionStart, PointVector positionEnd, Color backColor){
        this.backColor = backColor;
        initMovingPlatform(positionStart, positionEnd);
    }

    public MovingPlatformSwitchable(PointVector positionStart, PointVector positionEnd){
        initMovingPlatform(positionStart, positionEnd);
    }

    private void initMovingPlatform(PointVector positionStart, PointVector positionEnd){
        this.id = ObjectID.MovingPlatform;
        this.body = new Body(positionStart, 60, 15, 50);
        this.body.setMobility(false);
        this.body.setBodyColor(backColor);
        this.animation = null;

        this.startPosition = positionStart;
        this.endPosition = positionEnd;
        this.targetPosition = startPosition;
        this.velocity = (endPosition.sub(startPosition)).scalarMultiply(1/(float)timeToFinish);

        this.isActive = false;
        this.isCollisional = true;
    }

    // this function is used to change the target position when we reach the end of the path
    protected void changeDirectionOfMovement(){
        // this does nothing, the direction is dictated by the switch
    }

    public void resetToInitialState() {
        isActive = false;
        body.setPosition(startPosition);
        targetPosition = startPosition;
        velocity = (endPosition.sub(startPosition)).scalarMultiply(1/(float)timeToFinish);
    }

    @Override
    public void turnOn(String command) {
        targetPosition = endPosition;
        velocity = (endPosition.sub(startPosition)).scalarMultiply(1/(float)timeToFinish);
        isActive = true;
    }

    @Override
    public void turnOff(String command) {
        targetPosition = startPosition;
        velocity = (endPosition.sub(startPosition)).scalarMultiply(1/(float)timeToFinish).scalarMultiply(-1);
        isActive = false;
    }

    @Override
    public PointVector getSwitchablePosition() {
        return body.getPosition();
    }
}
