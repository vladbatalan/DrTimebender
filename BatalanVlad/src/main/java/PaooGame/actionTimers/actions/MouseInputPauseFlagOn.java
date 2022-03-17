package PaooGame.actionTimers.actions;

import PaooGame.input.MouseInput;

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
