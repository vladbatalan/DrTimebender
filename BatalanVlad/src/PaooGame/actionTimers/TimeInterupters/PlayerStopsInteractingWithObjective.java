package PaooGame.actionTimers.TimeInterupters;

import PaooGame.actionTimers.ITimerInterupter;
import PaooGame.levels.LevelFlagsSystem;

public class PlayerStopsInteractingWithObjective implements ITimerInterupter {
    @Override
    public boolean isInteruptCondition() {
        return !LevelFlagsSystem.playerOnGoal;
    }
}
