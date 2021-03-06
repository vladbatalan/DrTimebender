package PaooGame.GameObjects.ObjectCollisionHandler;

import PaooGame.GameObjects.GameObject;
import PaooGame.GameObjects.MobileObjects.MobileObject;
import PaooGame.Physics.Body;
import PaooGame.Physics.PVector;

import java.awt.*;
import java.sql.Array;
import java.util.ArrayList;


// every GameObject has a way to comunicate with the map and decide if there is any kind of collision
// the collision between objects, though, it is much trikier and must be dealt separately
// the job of  ObjectCollisionHandler is to take care of the collsions

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
                    //                                                          in contact with obj2
                    PVector[] listOfPoints = Body.getSideCollisionPoints(mobile.getBody().getHitBox());

                    int totalPoints = listOfPoints.length;
                    int noSidePoints = totalPoints/4;

                    // foreach box of obj2
                    ArrayList<Rectangle> boxList = obj2.getHitBoxCollection();
                    for (Rectangle hitBox: boxList){

                        // foreach point of check
                        for(int index = 0; index < totalPoints; index ++){
                            PVector currentPoint = listOfPoints[index];

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
                    mobile.getBody().ajustPositionOnCollision();
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
        if (obj1.isMobile() && obj2.isMobile())
            return false;
        if(!obj1.isCollisional() || !obj2.isCollisional())
            return false;
        return true;
    }

    // checks the existing of a collision
    private static boolean  isThereCollisionBetween(GameObject obj1, GameObject obj2){
        ArrayList<Rectangle> obj1HitBoxes = obj1.getHitBoxCollection();
        ArrayList<Rectangle> obj2HitBoxes = obj2.getHitBoxCollection();

        for(int index1 = 0; index1 < obj1HitBoxes.size(); index1++){
            Rectangle r1 = obj1HitBoxes.get(index1);
            for(int index2 = 0; index2 < obj2HitBoxes.size(); index2++){
                Rectangle r2 = obj2HitBoxes.get(index2);
                if(r1.intersects(r2))
                    return true;
            }
        }

        return false;
    }

    // identify the places where the contact is made and ajust body position just to not interact
    private static void updatePosition(GameObject obj1, GameObject obj2){

    }

}
