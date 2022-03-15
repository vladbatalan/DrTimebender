package PaooGame.actionTimers.actions;

import PaooGame.gameObjects.ISwitchable;

import java.awt.*;

public class DelayTurnOff implements IAction {
    private ISwitchable turnedObj;
    private String myCommand;

    public DelayTurnOff(ISwitchable turnedObj){
        this.turnedObj = turnedObj;
        myCommand = "";
    }
    public DelayTurnOff(ISwitchable turnedObj, String command){
        this.turnedObj = turnedObj;
        myCommand = command;
    }

    @Override
    public void executeUpdate(){
        turnedObj.turnOff(myCommand);
    }

    @Override
    public void executeDraw(Graphics g) {
        //do nothing
    }


    public String toString(){
        return "Delay Turn Off";
    }
}
