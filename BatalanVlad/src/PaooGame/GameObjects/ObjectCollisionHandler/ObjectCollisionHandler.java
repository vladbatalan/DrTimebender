package PaooGame.GameObjects.ObjectCollisionHandler;

import PaooGame.GameObjects.GameObject;
import PaooGame.GameObjects.MobileObjects.MobileObject;
import PaooGame.Physics.PointVector;

import java.awt.*;
import java.util.ArrayList;

import static PaooGame.Physics.Utils.CollisionUtils.getSideCollisionPoints;


// every GameObject has a way to comunicate with the map and decide if there is any kind of collision
// the collision between objects, though, it is much trikier and must be dealt separately
// the job of  ObjectCollisionHandler is to take care of the collsions

/**
 * Class responsible for collision between objects on a map.
 */
public class ObjectCollisionHandler {

    public static void manageObjectsCollision(GameObject obj1, GameObject obj2){
        if( isCollisionNecessary(obj1, obj2) ){

            //System.out.println("Test collision between (" + obj1.getId() + "("+obj1.isCollisional()+"), " + obj2.getId() + "("+obj2.isCollisional()+"))");

            if( isThereCollisionBetween(obj1, obj2)){
                //System.out.println("We got necessary collision between (" + obj1.getId() + ", " + obj2.getId() + ")");

                // we got saveral cases of interactions:
                // 1) mobile to still that prevents the mobile from advancing
                if(obj1 instanceof MobileObject || obj2 instanceof MobileObject){
                    if(obj2 instanceof MobileObject){
                        // swap
                        GameObject aux = obj1;
                        obj1 = obj2;
                        obj2 = aux;
                    }
                    MobileObject mobile = (MobileObject)obj1;

                    // requesting the list of points that need to be checked in order to verify which sides are
                    // in contact with obj2
                    PointVector[] listOfPoints = getSideCollisionPoints(mobile.getBody().getHitBox());

                    int totalPoints = listOfPoints.length;
                    int noSidePoints = totalPoints/4;

                    // foreach box of obj2
                    ArrayList<Rectangle> boxList = obj2.getHitBoxCollection();
                    for (Rectangle hitBox: boxList){

                        // foreach point of check
                        for(int index = 0; index < totalPoints; index ++){
                            PointVector currentPoint = listOfPoints[index];

                            // check if point is inside box
                            if(hitBox.x <= currentPoint.getX() && currentPoint.getX() <= hitBox.x + hitBox.width &&
                                hitBox.y <= currentPoint.getY() && currentPoint.getY() <= hitBox.y + hitBox.height){

                                // the point is intersecting the box
                                // set the state of the collision on true
                                mobile.getBody().getCollisionState()[index/noSidePoints] = true;

                            }
                        }
                    }
                    // update the position of the mobile, based on the previous calculated interactions
                    mobile.getBody().adjustPositionOnCollision();
                    //System.out.println(obj1.getId() + " have ajusted it's position based on the interraction with " + obj2.getId());
                    // ############################# condition if obj2 is deadly to make mobile dead ###################

                    // the mechanism that stops the instance from advancing
                }
                // 2) still to still ex: one box is on a balance -> for later improvement

            }
        }
    }

    // verify based on the type of the objects that are interacting if the collision is necesary to be handled or not
    private static boolean isCollisionNecessary(GameObject obj1, GameObject obj2){
        if (obj1.isMobile() && obj2.isMobile()) {
            return false;
        }
        return obj1.isCollisional() && obj2.isCollisional();
    }

    // checks the existing of a collision
    private static boolean  isThereCollisionBetween(GameObject obj1, GameObject obj2){
        ArrayList<Rectangle> obj1HitBoxes = obj1.getHitBoxCollection();
        ArrayList<Rectangle> obj2HitBoxes = obj2.getHitBoxCollection();

        for (Rectangle r1 : obj1HitBoxes) {

            for (Rectangle r2 : obj2HitBoxes) {

                if (r1.intersects(r2)) {
                    return true;
                }
            }
        }

        return false;
    }

    // identify the places where the contact is made and ajust body position just to not interact
    private static void updatePosition(GameObject obj1, GameObject obj2){

    }

}
