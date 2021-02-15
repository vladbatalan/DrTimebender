package PaooGame.ActionTimers.Actions;

import PaooGame.ActionTimers.ITimerInterupter;

import java.awt.*;

public interface IAction {
    void executeUpdate();
    void executeDraw(Graphics g);
}
