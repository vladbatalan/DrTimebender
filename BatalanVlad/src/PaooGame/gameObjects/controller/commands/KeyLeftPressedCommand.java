package PaooGame.gameObjects.controller.commands;

import PaooGame.gameObjects.controller.ICommand;
import PaooGame.gameObjects.mobileObjects.MobileObject;
import PaooGame.physics.enums.Actions;

public class KeyLeftPressedCommand implements ICommand{
    private int startTime;
    private int stopTime;

    public KeyLeftPressedCommand(int startTime){
        this.startTime = startTime;
    }


    public int getStartExecutionTime() {
        return startTime;
    }
    public int getStopExecutionTime() {
        return stopTime;
    }
    public void setEndTime(int endTime) {
        this.stopTime = endTime;
    }

    public void execute(MobileObject mobile) {
        mobile.MoveLeft();
    }
    public void executeEnd(MobileObject mobile) {
        mobile.getBody().getActions()[Actions.MOVE_LEFT.getValue()] = false;
        mobile.Stand();
    }

    public String getCommandToken() {
        return "MoveLeft";
    }
    public boolean isEndCommand() {
        return false;
    }
    public boolean isSingleExecuted() {
        return false;
    }

    public String toString(){
        return "KeyLeft Command ("+startTime+", "+stopTime+")";
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
