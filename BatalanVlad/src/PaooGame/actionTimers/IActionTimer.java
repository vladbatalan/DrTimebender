package PaooGame.actionTimers;

import PaooGame.actionTimers.Actions.IAction;

public interface IActionTimer {
    void startTimer();
    void addTimerIntreruptor(ITimerInterupter timerInterupter);
    IAction getAction();
}
