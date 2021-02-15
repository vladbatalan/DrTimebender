package PaooGame.ActionTimers.Actions;

import PaooGame.ActionTimers.ITimerInterupter;
import PaooGame.Game;
import PaooGame.GameWindow.Camera.GameCamera;

import java.awt.*;

public class FadeOutColorAction implements IAction {
    private Color fadeOutColor;
    private int numberOfUpdates = 0;
    private int currentUpdate = 0;
    private GameCamera camera;

    public FadeOutColorAction(Color fadeColor, int periodicUpdateTicks, int durationTicks, GameCamera camera){
        this.fadeOutColor = fadeColor;
        this.numberOfUpdates = durationTicks/periodicUpdateTicks;
        this.camera = camera;
        this.currentUpdate = 0;
    }

    public FadeOutColorAction(Color fadeColor, int periodicUpdateTicks, int durationTicks){
        this.fadeOutColor = fadeColor;
        this.numberOfUpdates = durationTicks/periodicUpdateTicks;
        this.camera = null;
        this.currentUpdate = 0;
    }

    public void executeUpdate() {
        currentUpdate ++;
        int alpha = 255;
        alpha = (int)(alpha * (float)currentUpdate/numberOfUpdates);
        fadeOutColor = new Color(fadeOutColor.getRed(), fadeOutColor.getGreen(), fadeOutColor.getBlue(), alpha);
    }

    public void executeDraw(Graphics g) {
        int coordX = 0;
        int coordY = 0;
        if(camera != null){
            coordX += (int)camera.getCameraCoordonates().getX();
            coordY += (int)camera.getCameraCoordonates().getY();
        }

        g.setColor(fadeOutColor);
        g.fillRect(coordX, coordY, Game.GAME_WINDOW_WIDTH, Game.GAME_WINDOW_HEIGHT);
    }


    public String toString(){
        return "Fade Out";
    }
}
