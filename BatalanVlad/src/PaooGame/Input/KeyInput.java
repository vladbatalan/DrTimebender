package PaooGame.Input;

import PaooGame.Game;
import PaooGame.GameObjects.Controller.Commands.*;
import PaooGame.GameObjects.Controller.ICommand;
import PaooGame.GameStates;
import PaooGame.Levels.LevelFlagsSystem;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
    private Game game;
    private boolean[] moves = {false, false, false}; // left, Jump, Right
    public KeyInput(Game game){
        this.game = game;
    }

    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        if(Game.gameState == GameStates.GAME && game.getPlayer() != null && LevelFlagsSystem.enablePlayerControl) {
            if(key == KeyEvent.VK_C){
                System.out.println(Game.currentLevel.getControllerBuilder());
            }
            if (key == KeyEvent.VK_UP) {
                moves[1] = true;
                // move the player
                game.getPlayer().Jump();

                // save command to controller
                ICommand newCommand = new KeyUpPressedCommand(Game.currentLevel.getGameTimer().getCurrentTime());
                Game.currentLevel.getControllerBuilder().insertCommand(newCommand);

                //System.out.println("KeyUpPressedCommand");
            }
            if (key == KeyEvent.VK_RIGHT) {
                moves[2] = true;
                game.getPlayer().MoveRight();

                // save command to controller
                ICommand newCommand = new KeyRightPressedCommand(Game.currentLevel.getGameTimer().getCurrentTime());
                Game.currentLevel.getControllerBuilder().insertCommand(newCommand);

                //System.out.println("KeyRightPressedCommand");
            }
            if (key == KeyEvent.VK_LEFT) {
                moves[0] = true;
                game.getPlayer().MoveLeft();

                // save command to controller
                ICommand newCommand = new KeyLeftPressedCommand(Game.currentLevel.getGameTimer().getCurrentTime());
                Game.currentLevel.getControllerBuilder().insertCommand(newCommand);

                //System.out.println("KeyLeftPressedCommand");
            }
            if(key == KeyEvent.VK_SPACE){
                // works only in 2 cases:
                if(LevelFlagsSystem.playerOnTimeMachine && !LevelFlagsSystem.isOnReset){
                    // save command to controller
                    ICommand newCommand = new KeySpacePressedOnTimeMachineCommand(Game.currentLevel.getGameTimer().getCurrentTime());
                    Game.currentLevel.getControllerBuilder().insertCommand(newCommand);

                    // add new copy of player
                    Game.currentLevel.addOldInstance();
                    Game.currentLevel.resetLevel();

                    //System.out.println("SpaceTimePressedCommand");
                }

                if(LevelFlagsSystem.playerOnGoal){
                    // check if the current door is open
                    if(Game.currentLevel.getGameObjective().getActiveState()){
                        // save command to controller
                        ICommand newCommand = new KeySpacePressedOnTGoal(Game.currentLevel.getGameTimer().getCurrentTime());
                        Game.currentLevel.getControllerBuilder().insertCommand(newCommand);

                        // This is considered to be the win condition of a game
                        // The level win condition is called
                        Game.currentLevel.LevelWinConditionAchieved();
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
            if (key == KeyEvent.VK_UP) {
                moves[1] = false;
                game.getPlayer().getBody().getActions()[2] = false;

                // save command to controller
                ICommand newCommand = new KeyUpReleasedCommand(Game.currentLevel.getGameTimer().getCurrentTime());
                Game.currentLevel.getControllerBuilder().insertCommand(newCommand);
                //System.out.println("KeyUpReleasedCommand");
            }
            if (key == KeyEvent.VK_RIGHT) {
                moves[2] = false;
                game.getPlayer().getBody().getActions()[1] = false;
                game.getPlayer().Stand();

                // save command to controller
                ICommand newCommand = new KeyRightReleasedCommand(Game.currentLevel.getGameTimer().getCurrentTime());
                Game.currentLevel.getControllerBuilder().insertCommand(newCommand);

                //System.out.println("KeyRightReleasedCommand");
            }
            if (key == KeyEvent.VK_LEFT) {
                moves[0] = false;
                game.getPlayer().getBody().getActions()[0] = false;
                game.getPlayer().Stand();

                // save command to controller
                ICommand newCommand = new KeyLeftReleasedCommand(Game.currentLevel.getGameTimer().getCurrentTime());
                Game.currentLevel.getControllerBuilder().insertCommand(newCommand);

                //System.out.println("KeyLeftReleasedCommand");
            }
        }
    }

}
