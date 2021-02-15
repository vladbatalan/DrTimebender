package PaooGame.GameObjects.MobileObjects;

import PaooGame.GameObjects.ObjectID;
import PaooGame.Graphics.Animations.AnimationCollections.PlayerAnimationCollection;
import PaooGame.Physics.Body;
import PaooGame.Physics.PVector;
import PaooGame.Tiles.Map;

import java.awt.*;

public class Player extends MobileObject {
    private int lastAction = 1; //0 = MoveLeft, 1 = MoveRight
    public static int body_width = 20;
    public static int body_height = 40;


    public Player(PVector position, int BODY_WIDTH, int BODY_HEIGHT, float mass) {
        this.id = ObjectID.Player;
        this.body = new Body(position, BODY_WIDTH, BODY_HEIGHT, mass);
        this.body.setMobility(true);
        this.body.setBodyColor(new Color(0,0,0,0));
        this.animation = new PlayerAnimationCollection();
    }

    public Player(PVector position) {
        this.id = ObjectID.Player;
        this.body = new Body(position, 20, 40, 47);
        this.body.setMobility(true);
        this.body.setBodyColor(new Color(0,0,0,0));
        this.animation = new PlayerAnimationCollection();
    }

    public void StandAnimation(Graphics g){
        if(lastAction == 0) //moved right last time
            animation.displayAnimation("StandRightAnimation", body, g);
        else
            animation.displayAnimation("StandLeftAnimation", body, g);
    }
    public void MoveLeftAnimation(Graphics g){
        animation.displayAnimation("MoveLeftAnimation", body, g);
    }
    public void MoveRightAnimation(Graphics g){
        animation.displayAnimation("MoveRightAnimation", body, g);
    }
    public void JumpAnimation(Graphics g){
        if(lastAction == 1) //moved right last time
            animation.displayAnimation("JumpLeftAnimation", body, g);
        else
            animation.displayAnimation("JumpRightAnimation", body, g);
    }

    @Override
    public void Draw(Graphics g) {
        //is on a jump
        if(body.getActions()[2]) {
            if(body.getActions()[0])
                lastAction = 1;
            if(body.getActions()[1])
                lastAction = 0;
            JumpAnimation(g);
            return;
        }
        //decide by moving
        //move left
        if(body.getActions()[0]){
            lastAction = 1;
            MoveLeftAnimation(g);
            return;
        }
        //move right
        if(body.getActions()[1]){
            lastAction = 0;
            MoveRightAnimation(g);
            return;
        }
        //There s no move
        StandAnimation(g);
    }

    @Override
    public void Update(Map currentMap) {
        body.Update(currentMap);
    }

}
