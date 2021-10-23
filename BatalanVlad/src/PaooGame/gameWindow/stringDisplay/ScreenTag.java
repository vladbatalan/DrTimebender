package PaooGame.gameWindow.stringDisplay;

import PaooGame.Game;
import PaooGame.actionTimers.ITimerInterupter;
import PaooGame.gameObjects.ToBeUpdatedConstantly;
import PaooGame.gameWindow.camera.GameCamera;
import PaooGame.physics.PointVector;

import java.awt.*;
import java.util.ArrayList;

public class ScreenTag implements ToBeUpdatedConstantly {
    //the content of the string
    private String content;

    //Display time
    private int fadeInTime;
    private int fadeOutTime;
    private int displayTime;

    //is showing
    private boolean onShow = false;

    //working timer
    private int timer;

    //content font related
    private int fontSize;
    private int padding = 0;

    private Color color = new Color(0xFF, 0xFF, 0xFF, 0);
    private Color backgroundColor = new Color(0, 0, 0, 0);


    //if gameCamera != null => the string will stay relative to game camera
    private GameCamera camera = null;

    //interuptors
    private ArrayList<ITimerInterupter> timerInterupters = new ArrayList<>();


    private PointVector centerPosition;

    public ScreenTag(String content, int fontSize, int fadeInTime, int displayTime, int fadeOutTime) {
        this.content = content;
        this.fontSize = fontSize;
        this.fadeInTime = fadeInTime;
        this.displayTime = displayTime;
        this.fadeOutTime = fadeOutTime;

    }

    public ScreenTag(String content, int fontSize) {
        this.content = content;
        this.fontSize = fontSize;
        this.fadeInTime = -1;
        this.displayTime = -1;
        this.fadeOutTime = -1;
    }

    public ScreenTag(String content, int fontSize, int fadeInTime, int displayTime, int fadeOutTime, GameCamera camera) {
        this.content = content;
        this.fontSize = fontSize;
        this.fadeInTime = fadeInTime;
        this.displayTime = displayTime;
        this.fadeOutTime = fadeOutTime;
        this.camera = camera;
    }

    public ScreenTag(String content, int fontSize, GameCamera camera) {
        this.content = content;
        this.fontSize = fontSize;
        this.fadeInTime = -1;
        this.displayTime = -1;
        this.fadeOutTime = -1;
        this.camera = camera;
    }

    //x and y represents the center point of the string
    //trebuie dat update constant
    public void showScreenTag(int x, int y) {
        onShow = true;
        timer = 0;
        centerPosition = new PointVector(x, y);
        Game.updateList.add(this);
        //System.out.println("Screen tag added to game");
    }

    public void hideScreenTag() {
        //System.out.println("Ending Screen Tag at time = " + timer);
        Game.removeFromUpdateList.add(this);
        timer = 0;
        onShow = false;
    }


    @Override
    public void Update() {
        if (onShow) {
            boolean noInteruption = true;
            for (ITimerInterupter interupter : timerInterupters) {
                if (interupter.isInteruptCondition()) {
                    noInteruption = false;
                }
            }

            if (!noInteruption) {

                hideScreenTag();
                return;
            }

            if (displayTime == -1) //permanent
            {
                color = new Color(color.getRed(), color.getGreen(), color.getBlue(), 0xFF);
                return;
            }

            int newAlpha = 255;
            timer++;

            //System.out.println("Screen tag updated to time = " + timer);

            if (timer < fadeInTime) {
                newAlpha = (int) (timer / (float) fadeInTime * 255);
            } else if (timer > fadeInTime + displayTime && timer < fadeInTime + displayTime + fadeOutTime) {
                newAlpha = 0xFF - (int) ((float) (timer - fadeInTime - displayTime) / fadeOutTime * 255);
            } else if (timer > fadeInTime + displayTime + fadeOutTime - 1) {
                newAlpha = 0;
                hideScreenTag();
            }
            color = new Color(color.getRed(), color.getGreen(), color.getBlue(), newAlpha);
        }
    }

    @Override
    public void Draw(Graphics g) {
        if (onShow) {
            //calculate first the position of the text
            Font font = new Font("arial", Font.BOLD, fontSize);

            // get metrics from the graphics
            FontMetrics metrics = g.getFontMetrics(font);
            // get the height of a line of text in this
            // font and render context
            int hgt = metrics.getHeight();
            // get the advance of my text in this font
            // and render context
            int adv = metrics.stringWidth(content);
            // calculate the size of a box to hold the
            // text with some padding.
            Dimension size = new Dimension(adv, hgt);

            int fontWidth = size.width;
            int fontHeight = size.height;

            //System.out.println("Screen tag drawn at time = " + timer);
            PointVector drawPosition = new PointVector(centerPosition);
            if (camera != null) {
                drawPosition = drawPosition.add(camera.getCameraCoordinates());
            }
            g.setColor(backgroundColor);
            g.fillRect((int) (drawPosition.getX() - fontWidth / 2) - padding, (int) (drawPosition.getY()) - 2 * fontHeight / 3 - padding, fontWidth + 2 * padding, fontHeight + 2 * padding);

            g.setFont(font);
            g.setColor(color);
            g.drawString(content, (int) (drawPosition.getX() - fontWidth / 2), (int) (drawPosition.getY()));
        }
    }

    public String toString() {
        return "ScreenTag( Content: " + content + " )";
    }

    public void setColor(Color newColor) {
        color = newColor;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public void setPadding(int padding) {
        this.padding = padding;
    }

    public void addTimerIntreruptor(ITimerInterupter interupter) {
        timerInterupters.add(interupter);
    }

    public boolean getIsOnShow() {
        return onShow;
    }
}
