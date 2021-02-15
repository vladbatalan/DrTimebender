package PaooGame.GameObjects.StillObjects;

import PaooGame.Game;
import PaooGame.GameObjects.GameObjectHandler;
import PaooGame.GameObjects.MobileObjects.MobileObject;
import PaooGame.GameObjects.ObjectID;
import PaooGame.Graphics.Animations.AnimationCollections.ScalePanAnimationCollection;
import PaooGame.Physics.Body;
import PaooGame.Physics.PVector;
import PaooGame.Tiles.Map;

import java.awt.*;
import java.util.ArrayList;

public class ScalePan extends StillObject {
    // the minimum position that can be taken by pan
    private int minHeight;
    private int maxHeight;
    // the difference between the minimum and the maximum position
    private int movingHeight;
    private float totalMass;
    private float speed;
    private int targetedHeight;

    private int timeToPath = 60;

    private Color backColor= new Color(0x090D58);;

    // this array is used to comunicate which object is on the pan at a specific time
    private ArrayList<MobileObject> onPanObjects = new ArrayList<>();

    public ScalePan(PVector position, int movingHeight, float panMass){
        initScalePan(position, movingHeight, panMass);
    }
    public ScalePan(PVector position, int movingHeight, float panMass, Color bkColor){
        this.backColor = bkColor;
        initScalePan(position, movingHeight, panMass);
    }

    private void initScalePan(PVector position, int movingHeight, float panMass){
        this.id = ObjectID.ScalePan;
        this.body = new Body(position, 60, 20, panMass);
        this.animation = new ScalePanAnimationCollection(backColor);

        this.minHeight = (int)position.getY();
        this.movingHeight = movingHeight;
        this.maxHeight = minHeight - movingHeight;
        this.totalMass = this.body.getMass();
        this.targetedHeight = (this.minHeight + this.maxHeight)/2;

        this.body.setPosition(new PVector(body.getPosition().getX(), targetedHeight));

        this.timeToPath = 60;
        this.speed = (float)(movingHeight)/timeToPath;

        //this object has the property that can interact with other objects
        this.isCollisional = true;
    }

    @Override
    public void Draw(Graphics g) {
        // this component is composed of 2 different parts:
        // 1) the support - a pillon that supports the pan
        // 2) the pan - the place where the instances can stay to modify the total mass

        Rectangle supportHitBox = getSupportHitBox();
        // draw the support
        g.setColor(backColor);
        g.fillRect(supportHitBox.x, supportHitBox.y, supportHitBox.width, supportHitBox.height);

        // draw the pan
        body.Draw(g);
        animation.displayAnimation("ScalePanAnimation", body, g);
    }

    @Override
    public void Update(Map currentMap) {
        // evaluate Total Mass and stop bodys from going into elem
        evaluateTotalMass();

        // depending on the sign of the difference between current position end the desired one,
        //                  we adjust the position accordingly
        float diffToTargetedHeight = body.getPosition().getY() - targetedHeight;
        float oldDistance = Math.abs(diffToTargetedHeight);
        PVector oldPosition = body.getPosition();

        // we should here move the objects on the pans on the move, expecially when rising up the object
        if(diffToTargetedHeight < 0) // body move down
        {
            body.setPosition(limitTheHeight(body.getPosition().add(new PVector(0, speed))));

            for(int index = 0; index < onPanObjects.size(); index ++)
            {
                MobileObject onPanMob = onPanObjects.get(index);
                Body mobBody = onPanMob.getBody();
                mobBody.setPosition(new PVector(mobBody.getPosition().getX(), body.getPosition().getY() - mobBody.getBodyHeight()));
            }
        }else if (diffToTargetedHeight > 0) // body move up
        {
            body.setPosition(limitTheHeight(body.getPosition().sub(new PVector(0, speed))));
            for (int index = 0; index < onPanObjects.size(); index++) {
                MobileObject onPanMob = onPanObjects.get(index);
                Body mobBody = onPanMob.getBody();

                mobBody.setPosition(new PVector(mobBody.getPosition().getX(), body.getPosition().getY() - mobBody.getBodyHeight()));
            }
        }

        float newDistance = Math.abs(body.getPosition().getY() - targetedHeight);
        if(newDistance > oldDistance) {
            body.setPosition(oldPosition);
        }
    }


    // time to path describes the number of ticks required for the pan to travel from minHeight to maxHeight
    // this function is designed to be used by a Scale to synchronize it s Pans
    public void setTimeToPath(int timeToPath){
        this.timeToPath = timeToPath;
        this.speed = (float)(movingHeight)/timeToPath;
    }

    // this function calculates the total mass that is on the Pan (the mass of different objects)
    private void evaluateTotalMass(){
        this.totalMass = body.getMass();
        this.onPanObjects.clear();

        if(Game.currentLevel.getHandler() != null) {
            GameObjectHandler handler = Game.currentLevel.getHandler();
            // foreach player instance on pan add their mass and stop them from interacting with the pan
            for (int index = 0; index < handler.getMobileObjects().size(); index++) {
                MobileObject mobile = handler.getMobileObjects().get(index);

                // intersects the top side
                if (mobile.getBody().getHitBox().intersects(getSurfaceSquare())) {
                    //System.out.println("oldPosition: "+mobile.getBody().getOldPosition() + " - newPosition: " + mobile.getBody().getPosition());
                    // add it's mass
                    this.totalMass += mobile.getBody().getMass();
                    // make body not move into the box depending on the direction of moving
                    mobile.getBody().setJumpPermission(true);
                    this.onPanObjects.add(mobile);
                }
            }
        }
    }

    public int getMinHeight() {
        return minHeight;
    }
    public float getTotalMass() {
        // evaluate Total Mass and stop bodys from going into elem
        evaluateTotalMass();
        return totalMass;
    }
    public void setMovingHeight(int movingHeight) {
        this.movingHeight = movingHeight;
        this.maxHeight = minHeight - movingHeight;
        this.speed = (float)(movingHeight)/timeToPath;
    }

    // targeted height is the hight that is designated by a Scale, to echilibrate the Pans
    public void setTargetedHeight(int targetedHeight) {
        this.targetedHeight = targetedHeight;
    }

    // limit the height to be between min and max
    private PVector limitTheHeight(PVector myPVector){
        PVector newHeight = new PVector(myPVector);
        if(myPVector.getY() > minHeight) newHeight.setY(minHeight);
        if(myPVector.getY() < maxHeight) newHeight.setY(maxHeight);
        return newHeight;
    }

    // this sets the color of similar scalepans the same
    public void setBackgroundColor(Color bkColor){
        this.backColor = bkColor;
        this.animation = new ScalePanAnimationCollection(bkColor);
    }

    public Rectangle getSupportHitBox(){
        int thickness = 5;
        PVector topPosition = new PVector();
        topPosition.setY(body.getPosition().getY() + body.getBodyHeight());
        topPosition.setX(body.getPosition().getX() + (float)body.getBodyWidth()/2 - thickness);
        return new Rectangle((int)topPosition.getX(), (int)topPosition.getY(), 2*thickness, (int)(minHeight - body.getPosition().getY()));
    }

    private Rectangle getSurfaceSquare(){
        Rectangle bodyNormalHitBox = body.getHitBox();
        int displacement = bodyNormalHitBox.width/10;
        return new Rectangle(bodyNormalHitBox.x + displacement, bodyNormalHitBox.y, bodyNormalHitBox.width-2*displacement, 10);
    }

    public int getMidTargetHeight(){
        return (maxHeight+minHeight)/2;
    }
    public int getMinTargetHeight(){
        return minHeight;
    }
    public int getMaxTargetHeight() {
        return maxHeight;
    }


    // every gameoObject got a hit box or a group of hitboxes
    // this will be used on collision
    public ArrayList<Rectangle> getHitBoxCollection(){
        //System.out.println("\tScalePan getHitBoxCollection aquired!");
        ArrayList<Rectangle> myHitBoxCollection = new ArrayList<>();
        myHitBoxCollection.add(body.getHitBox());
        myHitBoxCollection.add(getSupportHitBox());
        return myHitBoxCollection;
    }
}
