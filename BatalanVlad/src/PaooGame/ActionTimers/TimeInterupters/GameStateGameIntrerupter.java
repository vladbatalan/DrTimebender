package PaooGame.ActionTimers.TimeInterupters;

import PaooGame.ActionTimers.ITimerInterupter;
import PaooGame.Game;
import PaooGame.GameStates;

public class GameStateGameIntrerupter implements ITimerInterupter {
    @Override
    public boolean isInteruptCondition() {
        if(Game.gameState == GameStates.GAME)
            return false;
        return true;
    }
}
