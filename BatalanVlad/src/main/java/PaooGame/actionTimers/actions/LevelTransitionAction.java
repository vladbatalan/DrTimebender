package PaooGame.actionTimers.actions;

import PaooGame.Game;
import PaooGame.GameStates;
import PaooGame.graphics.gameWindow.timer.Timer;

import java.awt.*;

public class LevelTransitionAction implements IAction {
    private final Game game;

    public LevelTransitionAction(Game game){
        this.game = game;
    }

    @Override
    public void executeUpdate() {
        Timer theTimer = game.currentLevel.getGameTimer();
        theTimer.hideTimer();

        // set the time obtained by completing the level
        game.winMenu.setScore(theTimer.getCurrentTime());

        // set the highscore for the current user
        game.winMenu.setHighScore(game.database.GetHighScore(game.currentLevel.getLevelCode(), game.userId));

        // add level completion to the table
        game.database.InsertCompletedLevel(game.currentLevel.getLevelCode(), game.userId, theTimer.getCurrentTime());

        //System.out.println("You win the level with the score: " + theTimer.getCurrentTime());
        game.gameState = GameStates.WIN_MENU;
    }

    @Override
    public void executeDraw(Graphics g) {
        //do nothing
    }


    public String toString(){
        return "Level Trasition";
    }
}
