package PaooGame.Physics;

import PaooGame.Game;
import PaooGame.Tiles.Map;

import java.awt.*;
import java.lang.reflect.Array;

public class Body {
    private PVector oldPosition;
    private PVector position;
    private PVector velocity;
    private PVector jumpForce;
    private PVector externalForce;
    public static PVector gravityForce = new PVector(0, 0.098f);
    private PVector resultantForce = new PVector();
    private int BODY_WIDTH;
    private int BODY_HEIGHT;
    private float mass;
    private Color bodyColor = new Color(0,0,0,0);
    private int speed = 4;
    private final float JUMP_CONSTANT = 90f;
    private boolean[] collisionState = new boolean[5];
    private boolean isMobile = true;
    private boolean jumpPermission = false;

    private boolean[] actions = new boolean[3]; // [0] -> MoveLeft; [1] -> MoveRight; [2] -> Jump;

    public Body(PVector position, int BODY_WIDTH, int BODY_HEIGHT, float mass){
        this.position = position;
        this.BODY_WIDTH = BODY_WIDTH;
        this.BODY_HEIGHT = BODY_HEIGHT;
        this.mass = mass;
        this.velocity = new PVector();
        this.jumpForce = new PVector();
        this.externalForce = new PVector();
        this.oldPosition = position;
    }

    private int jumpTimer = 0; //masoara cand ar trebui sa puna contitia StopJumpWhenOnPlatform
    private int jumpOnCollisionTime = 0; //masoare dupa cate secunde se pune valida oprirea
    public void Update(Map currentMap){
        this.oldPosition = this.position;
        if(!isMobile) {
            Stand();
            return;
        }
        //In case of a jump, decrease jumpForce by a bit
        if(actions[2]){
            jumpTimer ++;
            jumpForce = jumpForce.add(new PVector(0, gravityForce.getY()));// * Game.CURRENT_FRAME_TIME));
            if(jumpTimer > 10 && collisionState[2]){
                jumpOnCollisionTime++;
            }
            else{
                jumpOnCollisionTime = 0;
            }

            if(jumpForce.getY() > 0 || (jumpTimer > 10 && collisionState[2] && jumpOnCollisionTime > 5)){
                actions[2] = false;
                jumpForce = new PVector();
            }
        }
        else{
            // emulating the stop of a jump by the player
            jumpForce = jumpForce.scalarMultiply(0.65f);
            if(jumpForce.abs() < 0.1)
                jumpForce = new PVector();
        }

        //Calculate next position and check for collision
        resultantForce = new PVector();
        resultantForce = resultantForce.add(velocity);
        resultantForce = resultantForce.add(gravityForce.scalarMultiply(mass));
        resultantForce = resultantForce.add(jumpForce);
        if(!externalForce.equals(new PVector(0,0))){
            resultantForce = resultantForce.add(externalForce);
            //externalForce = externalForce.scalarMultiply(0.95f);
            //if(externalForce.abs() < 0.1)
            externalForce = new PVector();
        }

        // #################################### added here scalar multiply ################################
        PVector nextPosition = new PVector(position.add(resultantForce));//.scalarMultiply(Game.CURRENT_FRAME_TIME);

        //check if is out of map bounds
        nextPosition = nextPosition.setInMapBounds(
                BODY_WIDTH,
                BODY_HEIGHT,
                currentMap.getMaxBounds()
        );
        //apply corrections to resultantForce
        resultantForce = nextPosition.sub(position);

        //0 - top collision
        //1 - right collision
        //2 - bottom collision
        //3 - left collision
        //4 - deadly collision

        // request the collision statye from the interaction with the map
        collisionState = currentMap.checkCollision(nextPosition.getX(), nextPosition.getY(), BODY_WIDTH, BODY_HEIGHT);

        // now we ajust the resultant force based on these collisionStates
        ajustPositionOnCollision();
    }

    public void Draw(Graphics g){
        g.setColor(bodyColor);
        g.fillRect((int)position.getX(),(int)position.getY(),BODY_WIDTH, BODY_HEIGHT);
    }

    // this function takes into consideration the current resultant force
    //                       and the current Collision state which is modified by interaction with the map, or base on
    //                                                  a obj obj interaction that sops the player from moving
    public void ajustPositionOnCollision(){
        //top Collision
        if(collisionState[0] && resultantForce.getY() < 0){
            resultantForce.setY(0);
        }
        //bottom Collision
        if(collisionState[2] &&  resultantForce.getY() > 0) {
            resultantForce.setY(0);
        }
        //right Collision
        if(collisionState[1] && resultantForce.getX() > 0){
            resultantForce.setX(0);
        }
        //left Collision
        if(collisionState[3] && resultantForce.getX() < 0) {
            resultantForce.setX(0);
        }

        position = oldPosition.add(resultantForce);
    }

    public void Stand(){
        //if only one command is running or none
        if(!actions[0] && !actions[1])
            velocity.setX(0);
    }

    public void MoveLeft(){
        if(isMobile) {
            if (!actions[0]) {
                velocity.setX(-speed );//* Game.CURRENT_FRAME_TIME);
                actions[0] = true;
            }
        }
    }

    public void MoveRight(){
        if(isMobile) {
            if (!actions[1]) {
                velocity.setX(+speed );// * Game.CURRENT_FRAME_TIME);
                actions[1] = true;
            }
        }
    }

    public void Jump(){
        if(isMobile) {
            //check if is on solid
            if (!actions[2] && (collisionState[2] || jumpPermission)) {
                jumpPermission = false;
                jumpTimer = 0;
                jumpForce = new PVector(gravityForce.scalarMultiply(-JUMP_CONSTANT));
                actions[2] = true;
            }
        }
    }

    // for side collision purposes, this function creates based on several parameters of an array of points
    // the returning vector of points is organised like this:
    // -> it has length/4 points for each side
    // -> the sides are in the following order:
    //      - top
    //      - right
    //      - bottom
    //      - left
    public static PVector[] getSideCollisionPoints(Rectangle rect){

        //consider the following points on the side of the square
        /*           __  -> deltaX
        __#____#____#__
        |             | -> Body
        #             # -> Point to check collision
        |             |
        #             #
        |             |
        #             # -> Point to check collision
        |             |     | -> deltaY
        |_#____#____#_|     |
        */
        float deltaX = (float)rect.width/4;
        float deltaY = (float)rect.height/8;

        int nrTestPoints = 10;
        float topStep = (rect.width - 2*deltaX)/nrTestPoints;
        float sideStep = (rect.height - 2*deltaY)/nrTestPoints;
        int totalPoints = 4*nrTestPoints + 4;

        //points to be verified
        PVector[] checkingPoints = new PVector[totalPoints];
        for(int index = 0; index < nrTestPoints + 1; index ++){
            //top
            checkingPoints[index] = new PVector(rect.x + deltaX + index * topStep, rect.y);
            //right
            checkingPoints[index + nrTestPoints + 1] = new PVector(rect.x + rect.width, rect.y + deltaY + index * sideStep);
            //bottom
            checkingPoints[index + 2*(nrTestPoints + 1)] = new PVector(rect.x + deltaX + index * topStep,rect.y+rect.height);
            //left
            checkingPoints[index + 3*(nrTestPoints + 1)] = new PVector(rect.x, rect.y+ deltaY + index * sideStep);
        }

        return checkingPoints;
    }



    public void collisionModifier(Body body){
        if(position.add(resultantForce).distanceTo(body.getPosition()) < position.distanceTo(body.getPosition())){
            //this object get s close to the other strictly
            //this object will modify the extern force of the other if possible
            if(body.getMobility())
                body.setExternalForce(resultantForce);
        }
    }




    public void setPosition(PVector position) {
        //this.oldPosition = this.position;
        this.position = position;
    }
    public void setExternalForce(PVector externalForce){
        this.externalForce = externalForce;
    }
    public PVector getPosition(){
        return position;
    }
    public PVector getVelocity(){
        return velocity;
    }

    public boolean[] getActions(){
        return actions;
    }
    public boolean getMobility(){ return isMobile;}
    public void setMobility(boolean isMobile){
        this.isMobile = isMobile;
    }
    public void setBodyColor(Color color){
        this.bodyColor = color;
    }

    public int getBodyWidth() {
        return BODY_WIDTH;
    }
    public int getBodyHeight(){
        return BODY_HEIGHT;
    }
    public PVector getResultantForce(){
        return resultantForce;
    }
    public Rectangle getHitBox(){
        return new Rectangle((int)position.getX(), (int)position.getY(), BODY_WIDTH, BODY_HEIGHT);
    }

    public boolean[] getCollisionState() {
        return collisionState;
    }
    public float getMass() {
        return mass;
    }
    public PVector getOldPosition(){
        return oldPosition;
    }
    public void setCollisionState(boolean[] collisionState){
        this.collisionState = collisionState;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setOldPosition(PVector oldPosition) {
        this.oldPosition = oldPosition;
    }

    public void setResultantForce(PVector resultantForce) {
        this.resultantForce = resultantForce;
    }

    // s ar putea ca asta fa sie inutil ...
    public void setJumpPermission(boolean value){
        jumpPermission = value;
    }
}
