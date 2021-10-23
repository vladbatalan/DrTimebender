package PaooGame.gameObjects.controller.commands;

import PaooGame.gameObjects.controller.ICommand;
import PaooGame.gameObjects.mobileObjects.MobileObject;

public class KeyRightReleasedCommand implements ICommand {
    private int executionTime;

    public KeyRightReleasedCommand(int executionTime){
        this.executionTime = executionTime;
    }


    public int getStartExecutionTime() {
        return executionTime;
    }
    public int getStopExecutionTime() {
        return -1;
    }

    public void setEndTime(int endTime) {
        // do nothing -> single use
    }

    public void execute(MobileObject mobile) {
        // No execution purpose
    }
    public void executeEnd(MobileObject mobile) {
        // no execution purpose
    }


    public String getCommandToken() {
        return "MoveRight";
    }
    public boolean isEndCommand() {
        return true;
    }
    public boolean isSingleExecuted() {
        return false;
    }

    public String toString() {
        return "KeyRight Command (" + executionTime + ", " + executionTime + ")";
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
