package PaooGame.actionTimers.TimeInterupters;

import PaooGame.Game;
import PaooGame.GameStates;
import PaooGame.actionTimers.ITimerInterupter;

public class GameStateGameIntrerupter implements ITimerInterupter {
    @Override
    public boolean isInteruptCondition() {
        return Game.gameState != GameStates.GAME;
    }
}
