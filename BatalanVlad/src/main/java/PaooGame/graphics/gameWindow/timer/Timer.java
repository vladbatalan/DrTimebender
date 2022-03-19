package PaooGame.graphics.gameWindow.timer;

import PaooGame.Game;
import PaooGame.gameObjects.ToBeUpdatedConstantly;
import PaooGame.graphics.gameWindow.camera.GameCamera;
import PaooGame.graphics.gameWindow.stringDisplay.ScreenTag;
import PaooGame.physics.PointVector;

import java.awt.*;

public class Timer implements ToBeUpdatedConstantly {
    private PointVector positionOnScreen;
    private String content = "Time: ";

    private ScreenTag timerDisplay;
    private int countTimeTicks = 0;
    private Color displayColor = Color.black;

    public boolean isTimerOn = false;
    public boolean isTimerVisible = false;

    public Timer(PointVector positionOnScreen, GameCamera camera){
        timerDisplay = new ScreenTag(content, 15, camera);
        this.positionOnScreen = positionOnScreen;
    }

    @Override
    public void Update() {
        if(isTimerOn) {
            countTimeTicks ++;
            updateTimeContent();
            timerDisplay.setContent(content);
        }
    }

    @Override
    public void Draw(Graphics g) {
        // DO NOTHING
    }


    public void showTimer(){

        if(!isTimerVisible) {
            timerDisplay.showScreenTag((int) positionOnScreen.getX(), (int) positionOnScreen.getY());
            timerDisplay.setColor(displayColor);
            isTimerVisible = true;
        }
    }
    public void hideTimer(){

        if(isTimerVisible)
            timerDisplay.hideScreenTag();
        isTimerVisible = false;

    }

    public void restartTimer(){

        if(isTimerOn)
            Game.removeFromUpdateList.add(this);
        countTimeTicks = 0;
        isTimerOn = true;
        Game.updateList.add(this);

    }

    public void startTimer(){

        if(isTimerOn)
            Game.removeFromUpdateList.add(this);
        isTimerOn = true;
        Game.updateList.add(this);

    }

    public void stopTimer(){

        if(isTimerOn)
            Game.removeFromUpdateList.add(this);
        isTimerOn = false;

    }

    public void updateTimeContent(){
        int seconds = countTimeTicks/60;
        int minutes = seconds/60;
        seconds = seconds % 60;
        String secondsString = "" + seconds;
        String minutesString = "" + minutes;
        if(secondsString.length() < 2)
            secondsString = "0" + secondsString;
        if(minutesString.length() < 2)
            minutesString = "0" + minutesString;

        content = "Time: " + minutesString + " : " + secondsString;
    }

    public String toString(){
        return "Timer ( " + content + " )";
    }

    public void setBackground(Color backgroundColor, int padding){
        timerDisplay.setBackgroundColor(backgroundColor);
        timerDisplay.setPadding(padding);
    }

    public void setDisplayColor(Color displayColor) {
        this.displayColor = displayColor;
        timerDisplay.setColor(displayColor);
    }

    public int getCurrentTime(){
        return countTimeTicks;
    }
}
