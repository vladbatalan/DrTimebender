package PaooGame.gameObjects.controller.commands;

import PaooGame.gameObjects.controller.ICommand;
import PaooGame.gameObjects.mobileObjects.MobileObject;

public class KeyUpPressedCommand implements ICommand {
    private int startTime;
    private int stopTime;

    public KeyUpPressedCommand(int startTime){
        this.startTime = startTime;
    }


    public int getStartExecutionTime() {
        return startTime;
    }
    public int getStopExecutionTime() {
        return stopTime;
    }
    public void setEndTime(int endTime) {
        stopTime = endTime;
    }

    public void execute(MobileObject mobile) {
        mobile.Jump();
    }
    public void executeEnd(MobileObject mobile) {
        mobile.getBody().getActions()[2] = false;
    }

    public String getCommandToken() {
        return "Jump";
    }
    public boolean isEndCommand() {
        return false;
    }
    public boolean isSingleExecuted() {
        return false;
    }

    public String toString(){
        return "KeyUp Command ("+startTime+", "+stopTime+")";
    }

    @Override
    public int compareTo(ICommand o) {
        if(this.startTime < o.getStartExecutionTime())
            return -1;
        if(this.startTime > o.getStartExecutionTime())
            return 1;
        return Integer.compare(this.stopTime, o.getStopExecutionTime());
    }
}
