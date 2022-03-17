package PaooGame.actionTimers;

import PaooGame.actionTimers.actions.IAction;

public interface IActionTimer {
    void startTimer();
    void addTimerInterupter(ITimerInterupter timerInterupter);
    IAction getAction();
}
