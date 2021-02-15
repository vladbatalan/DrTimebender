package PaooGame.ActionTimers.Actions;

import PaooGame.ActionTimers.ITimerInterupter;
import PaooGame.Levels.Level;

import java.awt.*;

public class DelaySetResetFalseFlagAction implements IAction {
    private Level myLevel;

    public DelaySetResetFalseFlagAction(Level myLevel){
        this.myLevel = myLevel;
    }

    @Override
    public void executeUpdate() {
        myLevel.setOnResetState(false);
    }

    @Override
    public void executeDraw(Graphics g) {
        //do nothing
    }


    public String toString(){
        return "Delay Reset Level";
    }
}
