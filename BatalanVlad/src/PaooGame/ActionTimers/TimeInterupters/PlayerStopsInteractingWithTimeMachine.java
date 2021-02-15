package PaooGame.ActionTimers.TimeInterupters;

import PaooGame.ActionTimers.ITimerInterupter;
import PaooGame.Game;
import PaooGame.GameStates;
import PaooGame.Levels.LevelFlagsSystem;

public class PlayerStopsInteractingWithTimeMachine implements ITimerInterupter {
    @Override
    public boolean isInteruptCondition() {
        return !LevelFlagsSystem.playerOnTimeMachine;
    }
}
