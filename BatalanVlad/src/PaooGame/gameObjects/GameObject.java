package PaooGame.gameObjects;

import PaooGame.graphics.animations.animationCollections.AnimationCollection;
import PaooGame.physics.Body;
import PaooGame.tiles.Map;

import java.awt.*;
import java.util.ArrayList;

public abstract class GameObject {
    protected Body body = null;
    public ObjectID id;
    protected AnimationCollection animation;
    protected boolean isMobile = false;
    protected int width;
    protected int height;
    protected boolean isCollisional;

    public GameObject() {
        isCollisional = false;
    }

    public abstract void Draw(Graphics g);
    public abstract void Update(Map currentMap);
    public Body getBody(){
        return body;
    }
    public boolean isDead(){
        return body.getCollisionState()[4];
    }
    public ObjectID getId(){
        return id;
    }
    public boolean isCollisional() {
        return isCollisional;
    }
    public boolean isMobile(){
        return isMobile;
    }

    // every gameoObject got a hit box or a group of hitboxes
    // this is the general type of hitbox
    public ArrayList<Rectangle> getHitBoxCollection(){
        ArrayList<Rectangle> myHitBoxCollection = new ArrayList<>();
        myHitBoxCollection.add(body.getHitBox());
        return myHitBoxCollection;
    }
    public void resetToInitialState(){
        // do nothing for most of the objects
    }
}
