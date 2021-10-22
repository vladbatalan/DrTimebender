package PaooGame.actionTimers;

import PaooGame.Game;
import PaooGame.actionTimers.Actions.IAction;
import PaooGame.gameObjects.ToBeUpdatedConstantly;

import java.awt.*;
import java.util.ArrayList;

public class DelayedActionTimer implements ToBeUpdatedConstantly, IActionTimer {
    private IAction myAction;
    private int delayTicks;
    private int internTimer = 0;
    private Boolean canExecuteUpdate = false;
    private Boolean canExecuteDraw = false;
    private Boolean timerOn = false;

    //interupters
    private ArrayList<ITimerInterupter> interupters = new ArrayList<>();

    public DelayedActionTimer(IAction myAction, int delayTicks){
        this.myAction = myAction;
        this.delayTicks = delayTicks;
    }

    @Override
    public void Update() {
        //check if interruption appered
        boolean noInteruption = true;
        for(ITimerInterupter interupter:interupters)
            if(interupter.isInteruptCondition())
                noInteruption = false;
        if (!noInteruption) {
            //System.out.println("Delay tick removel called! Contained action = " + myAction.toString());
            Game.removeFromUpdateList.add(this);
            internTimer = 0;
            canExecuteDraw = false;
            canExecuteUpdate = false;
            timerOn = false;
            return;
        }

        internTimer ++;
        if(internTimer == delayTicks){
            //we enable the action
            canExecuteDraw = true;
            canExecuteUpdate = true;
        }
        if(internTimer > delayTicks){
            //System.out.println("Delay tick removel called! Contained action = " + myAction.toString());
            Game.removeFromUpdateList.add(this);
            internTimer = 0;
            canExecuteDraw = false;
            canExecuteUpdate = false;
            timerOn = false;
        }

        //execute the action
        if (canExecuteUpdate) {
            myAction.executeUpdate();
            canExecuteUpdate = false;
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
        if (!timerOn) {
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
