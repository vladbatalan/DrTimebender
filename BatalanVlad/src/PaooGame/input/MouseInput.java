package PaooGame.input;

import PaooGame.Game;
import PaooGame.GameStates;

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
            switch (Game.gameState) {
                case MENU:
                    Game.mainMenu.getButtons().CheckMousePress(new Point(mousePositionX, mousePositionY));
                    break;
                case MAP_CREATION:
                    break;
                case LEVEL_MENU:
                    Game.levelMenu.getButtons().CheckMousePress(new Point(mousePositionX, mousePositionY));
                    break;
                case WIN_MENU:
                    Game.winMenu.getButtons().CheckMousePress(new Point(mousePositionX, mousePositionY));
                    break;
                case NEW_PROFILE_MENU:
                    Game.newProfileMenu.getButtons().CheckMousePress(new Point(mousePositionX, mousePositionY));
                    break;
                case PROFILE_SELECTION_MENU:
                    Game.profileSelectionMenu.getButtons().CheckMousePress(new Point(mousePositionX, mousePositionY));
                    break;
                case HELP_MENU:
                    Game.helpMenu.getButtons().CheckMousePress(new Point(mousePositionX, mousePositionY));
                    break;
                case VICTORY_MENU:
                    Game.victoryMenu.getButtons().CheckMousePress(new Point(mousePositionX, mousePositionY));
                    break;
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
