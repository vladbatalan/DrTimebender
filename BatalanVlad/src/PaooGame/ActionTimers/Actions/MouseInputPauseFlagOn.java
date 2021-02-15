package PaooGame.ActionTimers.Actions;

import PaooGame.Input.MouseInput;
import PaooGame.Levels.Level;

import java.awt.*;

public class MouseInputPauseFlagOn implements IAction {

    public MouseInputPauseFlagOn(){
    }

    @Override
    public void executeUpdate() {
        MouseInput.mouseInputPause = false;
    }

    @Override
    public void executeDraw(Graphics g) {
        //do nothing
    }


    public String toString(){
        return "Mouse Input Flag Pause";
    }
}
