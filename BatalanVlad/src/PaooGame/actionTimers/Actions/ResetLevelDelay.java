package PaooGame.actionTimers.Actions;

import PaooGame.levels.Level;

import java.awt.*;

public class ResetLevelDelay implements IAction {
    private Level myLevel;

    public ResetLevelDelay(Level myLevel){
        this.myLevel = myLevel;
    }

    @Override
    public void executeUpdate() {
        myLevel.resetLevel();
    }

    @Override
    public void executeDraw(Graphics g) {
        //do nothing
    }


    public String toString(){
        return "Delay Reset Level";
    }
}
