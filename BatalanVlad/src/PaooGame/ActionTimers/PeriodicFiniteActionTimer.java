package PaooGame.ActionTimers;

import PaooGame.ActionTimers.Actions.IAction;
import PaooGame.Game;
import PaooGame.GameObjects.ToBeUpdatedConstantly;

import java.awt.*;
import java.util.ArrayList;

public class PeriodicFiniteActionTimer implements ToBeUpdatedConstantly, IActionTimer {
    private IAction myAction;
    private int periodTicks;
    private int deadline;
    private int internTimer = 0;
    private Boolean canExecuteUpdate = false;
    private Boolean canExecuteDraw = false;
    private Boolean timerOn = false;

    //interupters
    private ArrayList<ITimerInterupter> interupters = new ArrayList<ITimerInterupter>();

    public PeriodicFiniteActionTimer(IAction myAction, int periodTicks, int deadline){
        this.myAction = myAction;
        this.periodTicks = periodTicks;
        this.deadline = deadline;
    }

    @Override
    public void Update() {
        if(timerOn) {
            //check if interruption appered
            boolean noInteruption = true;
            for(ITimerInterupter interupter:interupters)
                if(interupter.isInteruptCondition())
                    noInteruption = false;
            if(noInteruption == false){
                //System.out.println("Periodic tick removel called! Contained action = " + myAction.toString());
                Game.removeFromUpdateList.add(this);
                internTimer = 0;
                canExecuteDraw = false;
                canExecuteUpdate = false;
                timerOn = false;
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
                Game.removeFromUpdateList.add(this);
                timerOn = false;
                internTimer = 0;
                canExecuteDraw = false;
                canExecuteUpdate = false;
                timerOn = false;
            }

            //execute the action
            if (canExecuteUpdate == true) {
                myAction.executeUpdate();
                canExecuteUpdate = false;
            }
        }
    }

    @Override
    public void Draw(Graphics g) {
        //execute the action
        if(canExecuteDraw == true) {
            myAction.executeDraw(g);
            canExecuteDraw = false;
        }
    }

    @Override
    public void startTimer() {
        if(timerOn == false) {
            internTimer = 0;
            canExecuteDraw = false;
            canExecuteUpdate = false;
            timerOn = true;
            Game.updateList.add(this);
        }
    }

    @Override
    public void addTimerIntreruptor(ITimerInterupter timerInterupter) {
        interupters.add(timerInterupter);
    }
    @Override
    public IAction getAction() {
        return myAction;
    }
}
