package PaooGame.ActionTimers.Actions;

import PaooGame.Game;
import PaooGame.GameStates;
import PaooGame.GameWindow.Timer.Timer;

import java.awt.*;

public class LevelTransitionAction implements IAction {
    public LevelTransitionAction(){
    }

    @Override
    public void executeUpdate() {
        Timer theTimer = Game.currentLevel.getGameTimer();
        theTimer.hideTimer();

        // set the time obtained by completing the level
        Game.winMenu.setScore(theTimer.getCurrentTime());

        // set the highscore for the current user
        Game.winMenu.setHighScore(Game.database.GetHighScore(Game.currentLevel.getLevelCode(), Game.user_id));

        // add level completion to the table
        Game.database.InsertCompletedLevel(Game.currentLevel.getLevelCode(), Game.user_id, theTimer.getCurrentTime());

        //System.out.println("You win the level with the score: " + theTimer.getCurrentTime());
        Game.gameState = GameStates.WIN_MENU;
    }

    @Override
    public void executeDraw(Graphics g) {
        //do nothing
    }


    public String toString(){
        return "Level Trasition";
    }
}
