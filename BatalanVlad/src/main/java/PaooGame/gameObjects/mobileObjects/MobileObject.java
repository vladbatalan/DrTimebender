package PaooGame.gameObjects.mobileObjects;

import PaooGame.gameObjects.GameObject;
import PaooGame.physics.enums.Actions;

import java.awt.*;

public abstract class MobileObject extends GameObject {

    public MobileObject() {
        this.isMobile = true;
        this.isCollisional = true;
    }

    public void Stand() {
        body.Stand();
    }

    public void MoveLeft() {
        body.getActions()[Actions.MOVE_RIGHT.getValue()] = false;
        body.MoveLeft();
    }

    public void MoveRight() {
        body.getActions()[Actions.MOVE_LEFT.getValue()] = false;
        body.MoveRight();
    }

    public void Jump() {
        body.Jump();
    }

    public abstract void StandAnimation(Graphics g);

    public abstract void MoveLeftAnimation(Graphics g);

    public abstract void MoveRightAnimation(Graphics g);

    public abstract void JumpAnimation(Graphics g);
}
