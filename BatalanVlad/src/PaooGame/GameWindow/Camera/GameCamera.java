package PaooGame.GameWindow.Camera;

import PaooGame.Game;
import PaooGame.GameObjects.GameObject;
import PaooGame.Physics.PVector;

import java.awt.*;

public class GameCamera {
    private PVector cameraCoordonates;

    private int maxCameraCoordWidth;
    private int maxCameraCoordHeight;

    private GameObject followedObject;

    // Specify if camera is on
    private boolean cameraOn = false;

    public GameCamera(int mapSizeWidth, int mapSizeHeight){
        this.maxCameraCoordWidth = Math.max(mapSizeWidth - Game.GAME_WINDOW_WIDTH, 0);
        this.maxCameraCoordHeight = Math.max(mapSizeHeight - Game.GAME_WINDOW_HEIGHT, 0);
        cameraCoordonates = new PVector();
    }


    public void UpdateCamera(Graphics g){
        //camera must be turned on
        if(!cameraOn) return;

        //daca urmareste un obiect
        if(followedObject != null){
            PVector newCameraCoords = new PVector();
            newCameraCoords.setX(followedObject.getBody().getPosition().getX() - (float)Game.GAME_WINDOW_WIDTH/2);
            newCameraCoords.setY(followedObject.getBody().getPosition().getY() - (float)Game.GAME_WINDOW_HEIGHT*2/3);

            this.setCameraCoordonates(newCameraCoords);
        }

        //nu este urmarit un obiect prorpiu zis
        //coordonatele camerei sunt setate prin intermediul setCameraCoordonates
        //do Nothing about current camara coords


        //move the camera
        g.translate(-(int)cameraCoordonates.getX(), -(int)cameraCoordonates.getY());


    }

    public void setFollowedObject(GameObject obj){
        this.followedObject = obj;
    }

    public void endFollowingObject(){
        this.followedObject = null;
    }


    public void cameraStart(){
        cameraOn = true;
        this.resetCamera();
    }

    public void cameraStop(){
        cameraOn = false;
    }

    public void setCameraCoordonates(PVector cameraPvector){
        //no negative coordonates
        if(cameraPvector.getX() < 0)
            cameraPvector.setX(0);
        if(cameraPvector.getY() < 0)
            cameraPvector.setY(0);

        //set camera to maximum coords possible
        cameraCoordonates.setX(Math.min(cameraPvector.getX(), maxCameraCoordWidth));
        cameraCoordonates.setY(Math.min(cameraPvector.getY(), maxCameraCoordHeight));
    }

    public void resetCamera(){
        cameraCoordonates = new PVector();
    }

    public PVector getCameraCoordonates() {
        return cameraCoordonates;
    }
}
