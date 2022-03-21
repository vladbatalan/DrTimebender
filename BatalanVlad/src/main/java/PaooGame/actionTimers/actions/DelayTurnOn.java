package PaooGame.actionTimers.actions;

import PaooGame.gameObjects.ISwitchable;

import java.awt.*;

public class DelayTurnOn implements IAction {
    private final ISwitchable turnedObj;
    private final String myCommand;

    public DelayTurnOn(ISwitchable turnedObj){
        this.turnedObj = turnedObj;
        myCommand = "";
    }
    public DelayTurnOn(ISwitchable turnedObj, String command){
        this.turnedObj = turnedObj;
        myCommand = command;
    }

    @Override
    public void executeUpdate(){
        turnedObj.turnOn(myCommand);
    }

    @Override
    public void executeDraw(Graphics g) {
        //do nothing
    }


    public String toString(){
        return "Delay Turn On";
    }
}
