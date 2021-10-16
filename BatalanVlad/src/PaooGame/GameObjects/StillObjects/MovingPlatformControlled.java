package PaooGame.GameObjects.StillObjects;

import PaooGame.Game;
import PaooGame.GameObjects.GameObjectHandler;
import PaooGame.GameObjects.ISwitchable;
import PaooGame.GameObjects.MobileObjects.MobileObject;
import PaooGame.GameObjects.ObjectID;
import PaooGame.Physics.Body;
import PaooGame.Physics.PointVector;
import PaooGame.Tiles.Map;
import javafx.util.Pair;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class MovingPlatformControlled extends StillObject implements ISwitchable {

    private Color backColor = new Color(0x090D58);
    private PointVector startPosition;
    private PointVector velocity;
    private float speed;


    private boolean[] directions;


    public MovingPlatformControlled(PointVector positionStart, Color backColor){
        this.backColor = backColor;
        initMovingPlatform(positionStart);
    }

    public MovingPlatformControlled(PointVector positionStart){
        initMovingPlatform(positionStart);
    }

    private void initMovingPlatform(PointVector positionStart){
        this.id = ObjectID.MovingPlatformControlled;
        this.body = new Body(positionStart, 60, 15, 50);
        this.body.setMobility(false);
        this.body.setBodyColor(backColor);
        this.animation = null;

        this.startPosition = positionStart;
        this.speed = 3;

        this.directions = new boolean[5];
        this.isCollisional = true;
    }

    @Override
    public void Draw(Graphics g) {
        body.Draw(g);


    }

    @Override
    public void Update(Map currentMap) {
        velocity = new PointVector();
        if(directions[0] && !directions[2]) velocity.setY(-speed); // Up
        if(directions[1] && !directions[3]) velocity.setX(speed); // Right
        if(directions[2] && !directions[0]) velocity.setY(speed); // Down
        if(directions[3] && !directions[1]) velocity.setX(-speed); // Left
        ArrayList<Pair<MobileObject, Float>> interacting = new ArrayList<>();

        int maxOnPlatformHeight = 0;

        // move all bodys toching the platform
        GameObjectHandler handler = Game.currentLevel.getHandler();
        for(int index = 0; index < handler.getMobileObjects().size(); index ++){
            MobileObject mobile = handler.getMobileObjects().get(index);

            if(mobile.getBody().getHitBox().intersects(this.getTopPlatform())){
                float widthDisplacement = mobile.getBody().getPosition().getX() - body.getPosition().getX();
                maxOnPlatformHeight = Math.max(mobile.getBody().getBodyHeight(), maxOnPlatformHeight);
                interacting.add(new Pair<MobileObject, Float>(mobile, widthDisplacement));
                // make body not move into the box depending on the direction of moving
                mobile.getBody().setJumpPermission(true);
            }
        }


       // System.out.println("Velocity = " + velocity.toString());
        PointVector nextPosition = body.getPosition().add(velocity);

        if(nextPosition.getY() < maxOnPlatformHeight)
            nextPosition.setY(maxOnPlatformHeight);

        nextPosition = nextPosition.setInMapBounds(
                body.getBodyWidth(),
                body.getBodyHeight(),
                currentMap.getMaxBounds()
        );

        PointVector resultantForce = nextPosition.sub(body.getPosition());
        body.setResultantForce(resultantForce);
        body.setOldPosition(body.getPosition());
        body.setCollisionState( currentMap.checkCollision(nextPosition.getX(), nextPosition.getY() - maxOnPlatformHeight, body.getBodyWidth(), body.getBodyHeight() + maxOnPlatformHeight) );
        body.adjustPositionOnCollision();

        for(Pair<MobileObject, Float> myPair : interacting){
            MobileObject mobile = myPair.getKey();
            float displacement = myPair.getValue();
            mobile.getBody().setPosition(new PointVector(body.getPosition().getX() + displacement, body.getPosition().getY() - mobile.getBody().getBodyHeight()));
        }
    }

    public void resetToInitialState() {
        body.setPosition(startPosition);
        Arrays.fill(directions, false);
        velocity = new PointVector();
    }

    @Override
    public void turnOn(String command) {
        switch (command){
            case "Up": directions[0] = true;
                break;
            case "Right": directions[1] = true;
                break;
            case "Down": directions[2] = true;
                break;
            case "Left": directions[3] = true;
                break;
        }
    }

    @Override
    public void turnOff(String command) {
        switch (command){
            case "Up": directions[0] = false;
                break;
            case "Right": directions[1] = false;
                break;
            case "Down": directions[2] = false;
                break;
            case "Left": directions[3] = false;
                break;
        }
    }
    private Rectangle getTopPlatform(){
        return new Rectangle((int)body.getPosition().getX()+8, (int)body.getPosition().getY(), body.getBodyWidth() - 16, body.getBodyHeight()/3);
    }

    @Override
    public PointVector getSwitchablePosition() {
        return body.getPosition();
    }
    public void setSpeed(float speed) {
        this.speed = speed;
    }
}
