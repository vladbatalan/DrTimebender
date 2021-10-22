package PaooGame.gameObjects.stillObjects;

import PaooGame.gameObjects.ISwitchable;
import PaooGame.gameObjects.ObjectID;
import PaooGame.physics.Body;
import PaooGame.physics.PointVector;
import PaooGame.tiles.Map;

import java.awt.*;
import java.util.ArrayList;

public class TimedGate extends StillObject implements ISwitchable {

    // tells if the door to the next level is open or not
    private boolean initialActive;
    private boolean isActive;
    private Color backColor= new Color(0x090D58);
    private int gateMovingHeight;
    private int timeToFinish = 60;
    private float speed;
    private int targetHeight;
    private int minPosition;
    private int maxPosition;

    private int currentHeight;

    public TimedGate(PointVector position, int gateMovingHeight, boolean isInitiallyActive, Color backColor){
        this.backColor = backColor;
        initTimedGate(position, gateMovingHeight, isInitiallyActive);
    }

    public TimedGate(PointVector position, int gateMovingHeight, boolean isInitiallyActive){
        initTimedGate(position, gateMovingHeight, isInitiallyActive);
    }

    private void initTimedGate(PointVector position, int gateMovingHeight, boolean isInitiallyActive){
        this.id = ObjectID.TimedGate;
        this.body = new Body(position, 20, 4, 50);
        this.body.setMobility(false);
        this.body.setBodyColor(new Color(0x9292CB));
        this.animation = null;

        this.gateMovingHeight = gateMovingHeight;
        this.minPosition = (int)position.getY();
        this.maxPosition = (int)position.getY() - this.gateMovingHeight;

        this.initialActive = isInitiallyActive;
        this.isActive = isInitiallyActive;
        if(this.isActive)
            targetHeight = getMaxTarget();
        else
            targetHeight = getMinTarget();
        this.currentHeight = targetHeight;

        this.isCollisional = true;

        this.speed = (float)gateMovingHeight/timeToFinish;

    }

    @Override
    public void Draw(Graphics g) {
        Rectangle myGate = getGateHitBox();

        // draw door
        g.setColor(backColor);
        g.fillRect(myGate.x, myGate.y, myGate.width, myGate.height);

        // draw body
        body.Draw(g);

    }

    @Override
    public void Update(Map currentMap) {
        int diff = currentHeight - targetHeight;
        int oldDif = Math.abs(diff);

        if(diff < 0){
            currentHeight += speed;
        }

        // door goes up
        if(diff > 0){
            currentHeight -= speed;
        }

        int newDiff = Math.abs(currentHeight - targetHeight);

        if(oldDif < newDiff)
            currentHeight = targetHeight;
    }

    @Override
    public void turnOn(String command) {
        targetHeight = getMinTarget();
    }

    @Override
    public void turnOff(String command) {
        targetHeight = getMaxTarget();
    }

    public PointVector getSwitchablePosition(){
        return body.getPosition();
    }
    public void setTimeToFinish(int timeToFinish) {
        this.timeToFinish = timeToFinish;
        this.speed = (float)gateMovingHeight/timeToFinish;
    }

    private Rectangle getGateHitBox(){
        int x = (int)body.getPosition().getX() + 5;
        int y = currentHeight;
        int width = 10;
        int height = (int)body.getPosition().getY() - currentHeight;

        return new Rectangle(x, y, width, height);
    }

    public void resetToInitialState() {
        if(initialActive)
            currentHeight = getMaxTarget();
        else
            currentHeight = getMinTarget();
        targetHeight = currentHeight;
    }

    private int getMaxTarget(){
        return maxPosition;
    }
    private int getMinTarget(){
        return minPosition;
    }


    // every gameoObject got a hit box or a group of hitboxes
    // this is the general type of hitbox
    public ArrayList<Rectangle> getHitBoxCollection(){
        ArrayList<Rectangle> myHitBoxCollection = new ArrayList<>();
        myHitBoxCollection.add(body.getHitBox());
        myHitBoxCollection.add(getGateHitBox());
        return myHitBoxCollection;
    }

}
