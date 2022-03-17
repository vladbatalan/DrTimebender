package PaooGame.input;

import PaooGame.Game;
import PaooGame.GameStates;
import PaooGame.menu.Menu;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseInput implements MouseListener, MouseMotionListener {
    private Game game;

    public  MouseInput(Game game){
        this.game = game;
    }
    public static boolean mouseInputPause = false;

    @Override
    public void mousePressed(MouseEvent e) {
        int mousePositionX = e.getX();
        int mousePositionY = e.getY();

        if(!mouseInputPause) {
            Menu currentMenu = Game.getCurrentMenu();
            if(currentMenu != null){
                currentMenu.getButtons().CheckMousePress(new Point(mousePositionX, mousePositionY));
            }
        }

        if(Game.gameState == GameStates.GAME){
            Game.currentLevel.getButtons().CheckMousePress(new Point(mousePositionX, mousePositionY));
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) { }

    @Override
    public void mouseMoved(MouseEvent e) {}

    @Override
    public void mouseClicked(MouseEvent e) {

    }
    @Override
    public void mouseEntered(MouseEvent e) {

    }
    @Override
    public void mouseExited(MouseEvent e) {

    }
    @Override
    public void mouseDragged(MouseEvent e) {}
}
