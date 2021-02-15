package PaooGame.ActionTimers.Actions;

import PaooGame.ActionTimers.ITimerInterupter;
import PaooGame.Game;
import PaooGame.GameWindow.Camera.GameCamera;

import java.awt.*;

public class FadeInColorAction implements IAction {
    private Color fadeInColor;
    private int numberOfUpdates = 0;
    private int currentUpdate = 0;
    private GameCamera camera;

    public FadeInColorAction(Color fadeColor, int periodicUpdateTicks, int durationTicks, GameCamera camera){
        this.fadeInColor = fadeColor;
        this.numberOfUpdates = durationTicks/periodicUpdateTicks;
        this.camera = camera;
        this.currentUpdate = 0;
    }

    public FadeInColorAction(Color fadeColor, int periodicUpdateTicks, int durationTicks){
        this.fadeInColor = fadeColor;
        this.numberOfUpdates = durationTicks/periodicUpdateTicks;
        this.camera = null;
        this.currentUpdate = 0;
    }

    public void executeUpdate() {
        currentUpdate ++;
        int alpha = 255;
        alpha = 255 - (int)(alpha * (float)currentUpdate/numberOfUpdates);
        fadeInColor = new Color(fadeInColor.getRed(), fadeInColor.getGreen(), fadeInColor.getBlue(), alpha);
        //System.out.println("A fade in update has been called!");
    }

    public void executeDraw(Graphics g) {
        int coordX = 0;
        int coordY = 0;
        if(camera != null){
            coordX += (int)camera.getCameraCoordonates().getX();
            coordY += (int)camera.getCameraCoordonates().getY();
        }

        g.setColor(fadeInColor);
        g.fillRect(coordX, coordY, Game.GAME_WINDOW_WIDTH, Game.GAME_WINDOW_HEIGHT);
        //System.out.println("A fade in effect has been drawn!");
    }


    public String toString(){
        return "Fade In";
    }
}
