package PaooGame.actionTimers;

import PaooGame.actionTimers.actions.IAction;

public interface IActionTimer {
    void startTimer();
    void addTimerIntreruptor(ITimerInterupter timerInterupter);
    IAction getAction();
}
