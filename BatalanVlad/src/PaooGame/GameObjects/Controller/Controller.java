package PaooGame.GameObjects.Controller;
import PaooGame.GameObjects.MobileObjects.MobileObject;

import java.util.ArrayList;

public class Controller{
    private ArrayList<ICommand> commandList = new ArrayList<ICommand>();
    private ArrayList<ICommand> executing = new ArrayList<ICommand>();
    private int commandIndex = 0;

    public void addCommand(ICommand command){
        commandList.add(command);
    }

    public void execute(int executionTime, MobileObject mobile){
        // add commands to execution list
        while(commandIndex < commandList.size() &&
                commandList.get(commandIndex).getStartExecutionTime() <= executionTime &&
                    commandList.get(commandIndex).getStopExecutionTime() >= executionTime){
            executing.add(commandList.get(commandIndex));
            commandIndex ++;
        }

        ArrayList<ICommand> removeFromExecution = new ArrayList<>();
        // foreach executing command, do the execution type
        for(ICommand current : executing){
            // single execution treatment
            if(current.isSingleExecuted()){
                current.execute(mobile);
                removeFromExecution.add(current);
            }
            else{
                // if it is still available execute
                if(current.getStopExecutionTime() > executionTime){
                    current.execute(mobile);
                }
                // else
                else{
                    current.executeEnd(mobile);
                    removeFromExecution.add(current);
                }
            }
        }

        // remove from executing the ones that finished
        for(ICommand remove : removeFromExecution){
            executing.remove(remove);
        }
    }

    public void resetController(){
        commandIndex = 0;
        executing.clear();
    }

    public String toString(){
        String ret = "";
        for(ICommand command : commandList){
            ret += command.toString() + "\n";
        }
        return ret;
    }

    public ArrayList<ICommand> getCommandList(){
        return commandList;
    }
}
