package PaooGame.gameObjects.controller;

import PaooGame.gameObjects.mobileObjects.MobileObject;

import java.util.ArrayList;

/**
 * Class responsible for handling the set of movements made by a player or an old instance.
 * The execution of a command depends on the internal time in which it was executed.
 */
public class Controller {
    private final ArrayList<ICommand> commandList = new ArrayList<>();
    private final ArrayList<ICommand> executing = new ArrayList<>();
    private int commandIndex = 0;

    public void addCommand(ICommand command) {
        commandList.add(command);
    }

    public void execute(int executionTime, MobileObject mobile) {

        // The execution list are the commands that need to be executed in order of their index
        while (commandIndex < commandList.size())
        {
            // Get the current command
            ICommand current = commandList.get(commandIndex);

            // Only if execution time is between current interval
            if(current.getStartExecutionTime() <= executionTime && current.getStopExecutionTime() >= executionTime) {

                // Add the command
                executing.add(current);
                commandIndex++;
            }
            else{
                break;
            }
        }

        // Execute the commands and then remove them from list
        ArrayList<ICommand> removeFromExecution = new ArrayList<>();
        for(ICommand current : executing){

            // It is a single use command: Execute and then remove
            if(current.isSingleExecuted()){
                current.execute(mobile);
                removeFromExecution.add(current);
            }
            // It is a command with start and end event action
            else{

                // Execution was not completed
                if(current.getStopExecutionTime() > executionTime){
                    current.execute(mobile);
                }

                // Execution was completed
                else{
                    current.executeEnd(mobile);
                    removeFromExecution.add(current);
                }
            }
        }

        // Remove from executing the finished events
        for(ICommand remove : removeFromExecution){
            executing.remove(remove);
        }
    }

    public void resetController(){
        commandIndex = 0;
        executing.clear();
    }

    public String toString(){
        StringBuilder ret = new StringBuilder();
        for(ICommand command : commandList){
            ret.append(command.toString()).append("\n");
        }
        return ret.toString();
    }

    public ArrayList<ICommand> getCommandList(){
        return commandList;
    }
}
