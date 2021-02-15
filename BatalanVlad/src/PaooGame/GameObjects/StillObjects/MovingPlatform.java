package PaooGame.GameObjects.StillObjects;

import PaooGame.Game;
import PaooGame.GameObjects.GameObjectHandler;
import PaooGame.GameObjects.ISwitchable;
import PaooGame.GameObjects.MobileObjects.MobileObject;
import PaooGame.GameObjects.ObjectID;
import PaooGame.Physics.Body;
import PaooGame.Physics.PVector;
import PaooGame.Tiles.Map;
import javafx.util.Pair;

import java.awt.*;
import java.util.ArrayList;

public class MovingPlatform extends StillObject {

    // tells if the door to the next level is open or not
    protected Color backColor= new Color(0x090D58);
    protected int timeToFinish = 60;

    protected PVector velocity;
    protected PVector startPosition;
    protected PVector endPosition;
    protected PVector targetPosition;

    protected MovingPlatform(){}

    public MovingPlatform(PVector positionStart, PVector positionEnd, Color backColor){
        this.backColor = backColor;
        initMovingPlatform(positionStart, positionEnd);
    }

    public MovingPlatform(PVector positionStart, PVector positionEnd){
        initMovingPlatform(positionStart, positionEnd);
    }

    private void initMovingPlatform(PVector positionStart, PVector positionEnd){
        this.id = ObjectID.MovingPlatform;
        this.body = new Body(positionStart, 60, 15, 50);
        this.body.setMobility(false);
        this.body.setBodyColor(backColor);
        this.animation = null;

        this.startPosition = positionStart;
        this.endPosition = positionEnd;
        this.targetPosition = endPosition;
        this.velocity = (endPosition.sub(startPosition)).scalarMultiply(1/(float)timeToFinish);

        this.isCollisional = true;
    }

    @Override
    public void Draw(Graphics g) {
        // draw body
        body.Draw(g);
    }

    @Override
    public void Update(Map currentMap) {
        float oldDiff = body.getPosition().sub(targetPosition).abs();

        ArrayList<Pair<MobileObject, Float>> interacting = new ArrayList<>();

        // move all bodys toching the platform
        GameObjectHandler handler = Game.currentLevel.getHandler();

        for(int index = 0; index < handler.getMobileObjects().size(); index ++){
            MobileObject mobile = handler.getMobileObjects().get(index);

            if(mobile.getBody().getHitBox().intersects(this.getTopPlatform())){
                float widthDisplacement = mobile.getBody().getPosition().getX() - body.getPosition().getX();
                interacting.add(new Pair<MobileObject, Float>(mobile, widthDisplacement));
                // make body not move into the box depending on the direction of moving
                mobile.getBody().setJumpPermission(true);
            }

        }

        PVector newPosition = body.getPosition().add(this.velocity);

        // test if we got to the end
        float newDiff = newPosition.sub(targetPosition).abs();
        if(oldDiff < newDiff){
            newPosition = body.getPosition();
            //we got to the end
            //change sense of movement
            changeDirectionOfMovement();
        }

        for(Pair<MobileObject, Float> myPair : interacting){
            MobileObject mobile = myPair.getKey();
            float displacement = myPair.getValue();
            mobile.getBody().setPosition(new PVector(newPosition.getX() + displacement, newPosition.getY() - mobile.getBody().getBodyHeight()));
        }
        body.setPosition(newPosition);
    }

    // this function is used to change the target position when we reach the end of the path
    protected void changeDirectionOfMovement(){
        velocity = velocity.scalarMultiply(-1);
        if(targetPosition.equals(startPosition))
            targetPosition = endPosition;
        else
            targetPosition = startPosition;
    }

    public void resetToInitialState() {
        body.setPosition(startPosition);
        targetPosition = endPosition;
        velocity = (endPosition.sub(startPosition)).scalarMultiply(1/(float)timeToFinish);
    }


    private Rectangle getTopPlatform(){
        return new Rectangle((int)body.getPosition().getX()+8, (int)body.getPosition().getY(), body.getBodyWidth() - 16, body.getBodyHeight()/3);
    }

    // time to path describes the number of ticks required for the pan to travel from minHeight to maxHeight
    // this function is designed to be used by a Scale to synchronize it s Pans
    public void setTimeToFinish(int timeToFinish){
        this.timeToFinish = timeToFinish;
        velocity = (endPosition.sub(startPosition)).scalarMultiply(1/(float)timeToFinish);
        if(this.targetPosition.equals(startPosition))
            velocity = velocity.scalarMultiply(-1);
    }

}
