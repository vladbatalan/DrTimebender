package PaooGame.GameObjects.Controller;

import PaooGame.GameObjects.MobileObjects.MobileObject;

public interface ICommand extends Comparable<ICommand>{
    // timing setters and getters
    int getStartExecutionTime();
    int getStopExecutionTime();
    void setEndTime(int endTime);

    // execution code
    void execute(MobileObject mobile);
    void executeEnd(MobileObject mobile);

    // verifying the origin
    String getCommandToken();
    boolean isEndCommand();
    boolean isSingleExecuted();
}
