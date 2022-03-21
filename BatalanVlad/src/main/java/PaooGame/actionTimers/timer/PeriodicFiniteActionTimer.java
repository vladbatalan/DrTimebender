package PaooGame.actionTimers.timer;

import PaooGame.Game;
import PaooGame.actionTimers.IActionTimer;
import PaooGame.actionTimers.ITimerInterupter;
import PaooGame.actionTimers.actions.IAction;
import PaooGame.gameObjects.ToBeUpdatedConstantly;

import java.awt.*;
import java.util.ArrayList;

public class PeriodicFiniteActionTimer implements ToBeUpdatedConstantly, IActionTimer {
    private IAction myAction;
    private int periodTicks;
    private int deadline;
    private int internTimer = 0;
    private Boolean canExecuteUpdate = false;
    private Boolean canExecuteDraw = false;
    private Boolean isTimerOn = false;
    private Game game;

    //interupters
    private ArrayList<ITimerInterupter> interupters = new ArrayList<>();

    public PeriodicFiniteActionTimer(IAction myAction, int periodTicks, int deadline, Game game){
        this.myAction = myAction;
        this.periodTicks = periodTicks;
        this.deadline = deadline;
    }

    @Override
    public void Update() {
        if (isTimerOn) {
            //check if interruption appered
            boolean noInteruption = true;
            for (ITimerInterupter interupter : interupters)
                if (interupter.isInteruptCondition())
                    noInteruption = false;
            if (!noInteruption) {
                //System.out.println("Periodic tick removel called! Contained action = " + myAction.toString());
                game.removeFromUpdateList.add(this);
                internTimer = 0;
                canExecuteDraw = false;
                canExecuteUpdate = false;
                isTimerOn = false;
                return;
            }

            internTimer++;
            if (internTimer % periodTicks == 0) {
                //we enable the action
                canExecuteDraw = true;
                canExecuteUpdate = true;
            }
            if (internTimer == deadline) {
                //stop the action
                //System.out.println("Periodic tick removel called! Contained action = " + myAction.toString());
                game.removeFromUpdateList.add(this);
                isTimerOn = false;
                internTimer = 0;
                canExecuteDraw = false;
                canExecuteUpdate = false;
                isTimerOn = false;
            }

            //execute the action
            if (canExecuteUpdate) {
                myAction.executeUpdate();
                canExecuteUpdate = false;
            }
        }
    }

    @Override
    public void Draw(Graphics g) {
        //execute the action
        if (canExecuteDraw) {
            myAction.executeDraw(g);
            canExecuteDraw = false;
        }
    }

    @Override
    public void startTimer() {
        if (!isTimerOn) {
            internTimer = 0;
            canExecuteDraw = false;
            canExecuteUpdate = false;
            isTimerOn = true;
            game.updateList.add(this);
        }
    }

    @Override
    public void addTimerInterupter(ITimerInterupter timerInterupter) {
        interupters.add(timerInterupter);
    }
    @Override
    public IAction getAction() {
        return myAction;
    }
}
