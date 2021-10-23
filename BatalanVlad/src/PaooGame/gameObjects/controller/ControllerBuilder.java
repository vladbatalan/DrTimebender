package PaooGame.gameObjects.controller;

import java.util.ArrayList;
import java.util.Collections;

public class ControllerBuilder {
    private ArrayList<ICommand> onGoingCommands;
    private Controller myController;

    public ControllerBuilder(Controller myController){
        this.myController = myController;
        onGoingCommands = new ArrayList<>();
    }

    public void insertCommand(ICommand command){
        if(command.isSingleExecuted()){
            command.setEndTime(command.getStartExecutionTime());
            myController.addCommand(command);
            return;
        }
        // check if the command's token is in our list
        ICommand myListCommand = null;

        for(ICommand inListCommand : onGoingCommands){
            // the command need to be further processed
            if (inListCommand.getCommandToken().equals(command.getCommandToken())) {
                // we found the matching command
                myListCommand = inListCommand;
            }
        }

        //if there is no such command in list and not an ending command -> we add the command in list
        if(myListCommand == null){
            if(!command.isEndCommand())
                onGoingCommands.add(command);
        }
        // there is a command in our list
        else{
            if(command.isEndCommand()){
                // we need to remove our command from the list and push it into Controller
                // set The end time of the command
                myListCommand.setEndTime(command.getStartExecutionTime());
                // add int Controller
                myController.addCommand(myListCommand);
                // remove from onGoing list
                onGoingCommands.remove(myListCommand);
            }
            else{
                // we need to update our current command's time
                myListCommand.setEndTime(command.getStartExecutionTime());
            }
        }
    }

    public void finishControllerBuild(int currentTime){
        // Show remaining actions that are introduced at the end
        for(ICommand myCommand : onGoingCommands){
            System.out.println(myCommand.toString());
        }

        // if there are elements left in onGoingList -> end all the commands and add them to controller
        for(ICommand myCommand : onGoingCommands){
            myCommand.setEndTime(currentTime);
            myController.addCommand(myCommand);
        }
        onGoingCommands.clear();
    }

    public Controller getMyController(){
        Collections.sort(myController.getCommandList());
        return myController;
    }

    public String toString(){
        return getMyController().toString();
    }
}
