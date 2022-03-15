package PaooGame.physics;

import PaooGame.physics.enums.Actions;
import PaooGame.physics.enums.CollisionTypes;
import PaooGame.tiles.Map;

import java.awt.*;

public class Body {

    private PointVector oldPosition;
    private PointVector position;
    private PointVector velocity = new PointVector();
    private PointVector jumpForce = new PointVector();
    public static PointVector gravityForce = new PointVector(0, 0.098f);
    private PointVector resultantForce = new PointVector();
    private final int bodyWidth;
    private final int bodyHeight;
    private final float mass;
    private Color bodyColor = new Color(0, 0, 0, 0);
    private int speed = 4;
    private final float JUMP_CONSTANT = 90f;
    private boolean[] collisionState = new boolean[5];
    private boolean isMobile = true;
    private boolean canJump = false;


    //masoara cand ar trebui sa puna contitia StopJumpWhenOnPlatform
    private int jumpTimer = 0;

    //masoare dupa cate secunde se pune valida oprirea
    private int jumpOnCollisionTime = 0;


    private final boolean[] actions = new boolean[3]; // [0] -> MoveLeft; [1] -> MoveRight; [2] -> Jump;

    public Body(PointVector position, int bodyWidth, int bodyHeight, float mass) {

        this.position = position;
        this.bodyWidth = bodyWidth;
        this.bodyHeight = bodyHeight;
        this.mass = mass;
        this.oldPosition = position;
    }

    /**
     * Method resposible for updating the state of the object
     *
     * @param currentMap The map for providing environmental details.
     */
    public void Update(Map currentMap) {

        this.oldPosition = this.position;
        if (!isMobile) {

            Stand();
            return;

        }

        //In case of a jump, decrease jumpForce by a bit
        if (actions[Actions.JUMP.getValue()]) {

            jumpTimer++;
            jumpForce = jumpForce.add(new PointVector(0, gravityForce.getY()));

            if (jumpTimer > 10 && collisionState[CollisionTypes.BOTTOM.getValue()]) {
                jumpOnCollisionTime++;
            } else {
                jumpOnCollisionTime = 0;
            }

            if (jumpForce.getY() > 0 || (jumpTimer > 10 && collisionState[CollisionTypes.BOTTOM.getValue()] && jumpOnCollisionTime > 5)) {
                actions[Actions.JUMP.getValue()] = false;
                jumpForce = new PointVector();
            }
        } else {
            // emulating the stop of a jump by the player
            jumpForce = jumpForce.scalarMultiply(0.65f);
            if (jumpForce.abs() < 0.1)
                jumpForce = new PointVector();
        }

        //Calculate next position and check for collision
        resultantForce = new PointVector();
        resultantForce = resultantForce.add(velocity);
        resultantForce = resultantForce.add(gravityForce.scalarMultiply(mass));
        resultantForce = resultantForce.add(jumpForce);

        PointVector nextPosition = new PointVector(position.add(resultantForce));

        //check if is out of map bounds
        nextPosition = nextPosition.setInMapBounds(
                bodyWidth,
                bodyHeight,
                currentMap.getMaxBounds()
        );

        //apply corrections to resultantForce
        resultantForce = nextPosition.sub(position);

        //0 - top collision
        //1 - right collision
        //2 - bottom collision
        //3 - left collision
        //4 - deadly collision

        // request the collision state from the interaction with the map
        collisionState = currentMap.checkCollision(
                new Rectangle((int) nextPosition.getX(), (int) nextPosition.getY(), bodyWidth, bodyHeight));

        // now we ajust the resultant force based on these collisionStates
        adjustPositionOnCollision();
    }

    /**
     * Method responsible for drawing the Body of the character.
     *
     * @param g The graphic element used for drawing.
     */
    public void Draw(Graphics g) {
        g.setColor(bodyColor);
        g.fillRect((int) position.getX(), (int) position.getY(), bodyWidth, bodyHeight);
    }


    /**
     * Method responsible for changing the coordinates according to the interaction with other objects.
     */
    public void adjustPositionOnCollision() {

        // This function takes into consideration the current resultant force
        // and the current Collision state which is modified by interaction with the map, or base on
        // a obj-obj interaction that stops the player from moving

        //top Collision
        if (collisionState[CollisionTypes.TOP.getValue()]) {
            if (resultantForce.getY() < 0) {
                resultantForce.setY(0);
            }
        }
        //bottom Collision
        if (collisionState[CollisionTypes.BOTTOM.getValue()]) {
            if (resultantForce.getY() > 0) {
                resultantForce.setY(0);
            }
        }
        //right Collision
        if (collisionState[CollisionTypes.RIGHT.getValue()]) {
            if (resultantForce.getX() > 0) {
                resultantForce.setX(0);
            }
        }
        //left Collision
        if (collisionState[CollisionTypes.LEFT.getValue()]) {
            if (resultantForce.getX() < 0) {
                resultantForce.setX(0);
            }
        }

        // Adjust new position
        position = oldPosition.add(resultantForce);
    }

    /**
     * Method responsible for the standing state of the body.
     */
    public void Stand() {

        //if only one command is running or none
        if (!actions[Actions.MOVE_RIGHT.getValue()]) {

            if (!actions[Actions.MOVE_LEFT.getValue()]) {
                velocity.setX(0);
            }
        }

    }

    /**
     * Method responsible for the left move action of the body.
     */
    public void MoveLeft() {
        if (isMobile) {
            if (!actions[Actions.MOVE_LEFT.getValue()]) {

                velocity.setX(-speed);
                actions[Actions.MOVE_LEFT.getValue()] = true;

            }
        }
    }

    /**
     * Method responsible for the right move action of the body.
     */
    public void MoveRight() {
        if (isMobile) {
            if (!actions[Actions.MOVE_RIGHT.getValue()]) {

                velocity.setX(+speed);
                actions[Actions.MOVE_RIGHT.getValue()] = true;

            }
        }
    }


    /**
     * Method responsible for the jump action of the body.
     */
    public void Jump() {
        if (isMobile) {
            // Check if it is not jumping
            if (!actions[Actions.JUMP.getValue()]) {

                // Check if it is colliding with the floor or it has jump permission
                if (collisionState[CollisionTypes.BOTTOM.getValue()] || canJump) {

                    canJump = false;
                    jumpTimer = 0;
                    jumpForce = new PointVector(gravityForce.scalarMultiply(-JUMP_CONSTANT));
                    actions[Actions.JUMP.getValue()] = true;
                }

            }
        }
    }

    public void setPosition(PointVector position) {
        //this.oldPosition = this.position;
        this.position = position;
    }

    public PointVector getPosition() {
        return position;
    }

    public PointVector getVelocity() {
        return velocity;
    }

    public boolean[] getActions() {
        return actions;
    }

    public boolean getMobility() {
        return isMobile;
    }

    public void setMobility(boolean isMobile) {
        this.isMobile = isMobile;
    }

    public void setBodyColor(Color color) {
        this.bodyColor = color;
    }

    public int getBodyWidth() {
        return bodyWidth;
    }

    public int getBodyHeight() {
        return bodyHeight;
    }

    public PointVector getResultantForce() {
        return resultantForce;
    }

    public Rectangle getHitBox() {
        return new Rectangle((int) position.getX(), (int) position.getY(), bodyWidth, bodyHeight);
    }

    public boolean[] getCollisionState() {
        return collisionState;
    }

    public float getMass() {
        return mass;
    }

    public PointVector getOldPosition() {
        return oldPosition;
    }

    public void setCollisionState(boolean[] collisionState) {
        this.collisionState = collisionState;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setOldPosition(PointVector oldPosition) {
        this.oldPosition = oldPosition;
    }

    public void setResultantForce(PointVector resultantForce) {
        this.resultantForce = resultantForce;
    }

    // s ar putea ca asta fa sie inutil ...
    public void setCanJump(boolean value) {
        canJump = value;
    }
}
