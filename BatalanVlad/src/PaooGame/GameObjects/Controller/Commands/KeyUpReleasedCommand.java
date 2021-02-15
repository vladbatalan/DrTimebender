package PaooGame.GameObjects.Controller.Commands;

import PaooGame.GameObjects.Controller.ICommand;
import PaooGame.GameObjects.MobileObjects.MobileObject;

public class KeyUpReleasedCommand implements ICommand {
    private int executionTime = 0;

    public KeyUpReleasedCommand(int executionTime){
        this.executionTime = executionTime;
    }

    public int getStartExecutionTime() {
        return executionTime;
    }
    public int getStopExecutionTime() {
        return -1;
    }
    public void setEndTime(int endTime) {
        // do nothing
    }

    public void execute(MobileObject mobile){
        // no exesucution for this purpose
    }
    public void executeEnd(MobileObject mobile) {
        // no execution purpose
    }

    public String getCommandToken() {
        return "Jump";
    }
    public boolean isEndCommand() {
        return true;
    }
    public boolean isSingleExecuted() {
        return false;
    }

    public String toString(){
        return "KeyLeft Command ("+executionTime+", "+executionTime+")";
    }

    @Override
    public int compareTo(ICommand o) {
        if(this.executionTime < o.getStartExecutionTime())
            return -1;
        if(this.executionTime > o.getStartExecutionTime())
            return 1;
        return Integer.compare(this.executionTime, o.getStopExecutionTime());
    }
}
