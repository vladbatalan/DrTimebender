package PaooGame.ActionTimers.TimeInterupters;

import PaooGame.ActionTimers.ITimerInterupter;
import PaooGame.Levels.LevelFlagsSystem;

public class PlayerStopsInteractingWithObjective implements ITimerInterupter {
    @Override
    public boolean isInteruptCondition() {
        return !LevelFlagsSystem.playerOnGoal;
    }
}
