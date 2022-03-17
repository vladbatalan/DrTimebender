package PaooGame.input;

import PaooGame.Game;
import PaooGame.GameStates;
import PaooGame.gameObjects.controller.ICommand;
import PaooGame.gameObjects.controller.commands.*;
import PaooGame.gameObjects.mobileObjects.MobileObject;
import PaooGame.levels.Level;
import PaooGame.levels.LevelFlagsSystem;
import PaooGame.physics.enums.Actions;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
    private Game game;
    public KeyInput(Game game){
        this.game = game;
    }

    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        if(Game.gameState == GameStates.GAME && game.getPlayer() != null && LevelFlagsSystem.enablePlayerControl) {

            int gameCurrentTime = Game.currentLevel.getGameTimer().getCurrentTime();
            MobileObject player = game.getPlayer();
            Level currentLevel = Game.currentLevel;

            if(key == KeyEvent.VK_C){
                System.out.println(currentLevel.getControllerBuilder());
            }
            if (key == KeyEvent.VK_UP) {
                // move the player
                player.Jump();

                // save command to controller
                ICommand newCommand = new KeyUpPressedCommand(gameCurrentTime);
                currentLevel.getControllerBuilder().insertCommand(newCommand);

                //System.out.println("KeyUpPressedCommand");
            }
            if (key == KeyEvent.VK_RIGHT) {
                player.MoveRight();

                // save command to controller
                ICommand newCommand = new KeyRightPressedCommand(gameCurrentTime);
                currentLevel.getControllerBuilder().insertCommand(newCommand);

                //System.out.println("KeyRightPressedCommand");
            }
            if (key == KeyEvent.VK_LEFT) {
                player.MoveLeft();

                // save command to controller
                ICommand newCommand = new KeyLeftPressedCommand(gameCurrentTime);
                currentLevel.getControllerBuilder().insertCommand(newCommand);

                //System.out.println("KeyLeftPressedCommand");
            }
            if(key == KeyEvent.VK_SPACE){
                // works only in 2 cases:
                if(LevelFlagsSystem.playerOnTimeMachine && !LevelFlagsSystem.isOnReset){
                    // save command to controller
                    ICommand newCommand = new KeySpacePressedOnTimeMachineCommand(gameCurrentTime);
                    currentLevel.getControllerBuilder().insertCommand(newCommand);

                    // add new copy of player
                    currentLevel.addOldInstance();
                    currentLevel.resetLevel();

                    //System.out.println("SpaceTimePressedCommand");
                }

                if(LevelFlagsSystem.playerOnGoal){
                    // check if the current door is open
                    if(currentLevel.getGameObjective().getActiveState()){
                        // save command to controller
                        ICommand newCommand = new KeySpacePressedOnTGoal(gameCurrentTime);
                        currentLevel.getControllerBuilder().insertCommand(newCommand);

                        // This is considered to be the win condition of a game
                        // The level win condition is called
                        currentLevel.LevelWinConditionAchieved();
                    }
                }

            }
            if(key == KeyEvent.VK_F10){
                Game.currentLevel.LevelWinConditionAchieved();
            }
        }

        if(Game.gameState == GameStates.NEW_PROFILE_MENU){
            String current = Game.newProfileMenu.getProfileName();

            if( current.length() < 15 && "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ-_".contains(e.getKeyChar() + ""))
                Game.newProfileMenu.setProfileName(current + e.getKeyChar());
            else{
                if(key == KeyEvent.VK_BACK_SPACE){
                    if(current.length() > 0)
                        Game.newProfileMenu.setProfileName(current.substring(0, current.length()-1));
                }
            }
        }

    }
    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();

        if(Game.gameState == GameStates.GAME && game.getPlayer() != null && LevelFlagsSystem.enablePlayerControl) {

            int gameCurrentTime = Game.currentLevel.getGameTimer().getCurrentTime();
            MobileObject player = game.getPlayer();
            Level currentLevel = Game.currentLevel;

            if (key == KeyEvent.VK_UP) {
                player.getBody().getActions()[Actions.JUMP.getValue()] = false;

                // save command to controller
                ICommand newCommand = new KeyUpReleasedCommand(gameCurrentTime);
                currentLevel.getControllerBuilder().insertCommand(newCommand);
                //System.out.println("KeyUpReleasedCommand");
            }
            if (key == KeyEvent.VK_RIGHT) {
                player.getBody().getActions()[Actions.MOVE_RIGHT.getValue()] = false;
                player.Stand();

                // save command to controller
                ICommand newCommand = new KeyRightReleasedCommand(gameCurrentTime);
                currentLevel.getControllerBuilder().insertCommand(newCommand);

                //System.out.println("KeyRightReleasedCommand");
            }
            if (key == KeyEvent.VK_LEFT) {
                player.getBody().getActions()[Actions.MOVE_LEFT.getValue()] = false;
                player.Stand();

                // save command to controller
                ICommand newCommand = new KeyLeftReleasedCommand(gameCurrentTime);
                currentLevel.getControllerBuilder().insertCommand(newCommand);

                //System.out.println("KeyLeftReleasedCommand");
            }
        }
    }

}
