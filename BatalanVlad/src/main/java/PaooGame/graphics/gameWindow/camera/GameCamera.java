package PaooGame.graphics.gameWindow.camera;

import PaooGame.Game;
import PaooGame.gameObjects.GameObject;
import PaooGame.physics.PointVector;

import java.awt.*;

import static java.lang.Math.max;
import static java.lang.Math.min;

/**
 * Class responsible for handling the game camera
 */
public class GameCamera {

    private PointVector cameraCoordinates;
    private final int maxCameraCoordWidth;
    private final int maxCameraCoordHeight;

    private GameObject followedObject;

    private boolean cameraOn = false;

    public GameCamera(int mapSizeWidth, int mapSizeHeight) {
        this.maxCameraCoordWidth = max(mapSizeWidth - Game.GAME_WINDOW_WIDTH, 0);
        this.maxCameraCoordHeight = max(mapSizeHeight - Game.GAME_WINDOW_HEIGHT, 0);
        cameraCoordinates = new PointVector();
    }

    /**
     * Method responsible for updating the position of the camera
     *
     * @param g The graphic context needed for translating the drawing axes to camera position
     */
    public void UpdateCamera(Graphics g) {
        // Camera must be turned on
        if (!cameraOn) return;

        // If there is an object to follow
        if (followedObject != null) {

            PointVector newCameraCoords = new PointVector();

            newCameraCoords.setX(followedObject.getBody().getPosition().getX() - (float) Game.GAME_WINDOW_WIDTH / 2);
            newCameraCoords.setY(followedObject.getBody().getPosition().getY() - (float) Game.GAME_WINDOW_HEIGHT * 2 / 3);

            this.setCameraCoordinates(newCameraCoords);
        }

        //nu este urmarit un obiect prorpiu zis
        //coordonatele camerei sunt setate prin intermediul setCameraCoordonates
        //do Nothing about current camara coords


        //move the camera
        g.translate(-(int) cameraCoordinates.getX(), -(int) cameraCoordinates.getY());
    }

    /**
     * Method responsible for setting the followed object
     *
     * @param obj The instance followed by the camera
     */
    public void setFollowedObject(GameObject obj) {
        this.followedObject = obj;
    }

    /**
     * Method responsible for ending the action of following an object
     */
    public void endFollowingObject() {
        this.followedObject = null;
    }

    /**
     * Method responsible for starting the camera
     */
    public void cameraStart() {
        cameraOn = true;
        this.resetCamera();
    }

    /**
     * Method responsible for stoping the camera
     */
    public void cameraStop() {
        cameraOn = false;
    }

    /**
     * Method responsible for resetting the coordonates of the camera to initial
     */
    public void resetCamera() {
        cameraCoordinates = new PointVector();
    }

    public PointVector getCameraCoordinates() {
        return cameraCoordinates;
    }

    public void setCameraCoordinates(PointVector cameraPvector) {
        //no negative coordonates
        if (cameraPvector.getX() < 0) {
            cameraPvector.setX(0);
        }
        if (cameraPvector.getY() < 0) {
            cameraPvector.setY(0);
        }

        //set camera to maximum coords possible
        cameraCoordinates.setX(min(cameraPvector.getX(), maxCameraCoordWidth));
        cameraCoordinates.setY(min(cameraPvector.getY(), maxCameraCoordHeight));
    }
}
