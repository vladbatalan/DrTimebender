package PaooGame.actionTimers.actions;

import PaooGame.levels.Level;

import java.awt.*;

public class DelaySetResetFalseFlagAction implements IAction {
    private final Level myLevel;

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
