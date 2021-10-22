package PaooGame.actionTimers.TimeInterupters;

import PaooGame.actionTimers.ITimerInterupter;
import PaooGame.levels.LevelFlagsSystem;

public class PlayerStopsInteractingWithTimeMachine implements ITimerInterupter {
    @Override
    public boolean isInteruptCondition() {
        return !LevelFlagsSystem.playerOnTimeMachine;
    }
}
