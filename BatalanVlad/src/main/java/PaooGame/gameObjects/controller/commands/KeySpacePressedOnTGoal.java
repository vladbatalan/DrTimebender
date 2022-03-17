package PaooGame.gameObjects.controller.commands;

import PaooGame.gameObjects.controller.ICommand;
import PaooGame.gameObjects.mobileObjects.MobileObject;

public class KeySpacePressedOnTGoal implements ICommand {
    private int startTime;
    private int stopTime;

    public KeySpacePressedOnTGoal(int startTime){
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

    public void execute(MobileObject mobile){
        System.out.println("Key Space pressed by OldInstance on Goal");
        System.out.println("\tProceed on evaluating the situation ... ");
        System.out.println("\tCase that shouldn t occour!");
    }

    public void executeEnd(MobileObject mobile) {
        // no end execution
    }

    public String getCommandToken() {
        return "SpaceOnGoal";
    }

    public boolean isEndCommand() {
        return false;
    }
    public boolean isSingleExecuted() {
        return true;
    }

    public String toString(){
        return "KeySpace OnGoal Command (" + startTime + ", "+ stopTime +")";
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
