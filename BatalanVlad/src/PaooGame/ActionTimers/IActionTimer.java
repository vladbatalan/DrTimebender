package PaooGame.ActionTimers;

import PaooGame.ActionTimers.Actions.IAction;

public interface IActionTimer {
    void startTimer();
    void addTimerIntreruptor(ITimerInterupter timerInterupter);
    IAction getAction();
}
