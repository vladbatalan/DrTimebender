package PaooGame.GameObjects.Controller.Commands;

import PaooGame.Game;
import PaooGame.GameObjects.Controller.ICommand;
import PaooGame.GameObjects.MobileObjects.MobileObject;
import PaooGame.GameObjects.ObjectID;
import PaooGame.GameObjects.StillObjects.StillObject;
import PaooGame.Levels.LevelFlagsSystem;

public class KeySpacePressedOnTimeMachineCommand implements ICommand {
    private int startTime;
    private int stopTime;

    public KeySpacePressedOnTimeMachineCommand(int startTime){
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
        // verify Paradox
        boolean isParadox = true;
        for(StillObject structure : Game.currentLevel.getHandler().getStillObjects()){
            if(structure.getId() == ObjectID.TimeMachine) {
                if (mobile.getBody().getHitBox().intersects(structure.getBody().getHitBox())) {
                    isParadox = false;
                }
            }
        }

        if( isParadox ){
            LevelFlagsSystem.createParadox(mobile);
            System.out.println("Seems that we got a TIME PARADOX!");
        }else{
            // teleport in time machine
            Game.currentLevel.getHandler().getMobileObjects().remove(mobile);
        }
    }
    public void executeEnd(MobileObject mobile) {
        // do nothing
    }

    public String getCommandToken() {
        return "JumpOnTimeMachine";
    }
    public boolean isEndCommand() {
        return false;
    }
    public boolean isSingleExecuted() {
        return true;
    }

    public String toString(){
        return "KeySpace OnTimeMachine Command (" + startTime + ", "+ stopTime +")";
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
