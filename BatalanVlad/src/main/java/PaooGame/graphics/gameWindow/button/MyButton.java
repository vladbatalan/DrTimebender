package PaooGame.graphics.gameWindow.button;
//import com.sun.corba.se.impl.orbutil.graph.Graph;

import PaooGame.graphics.gameWindow.camera.GameCamera;

import java.awt.*;

public abstract class MyButton {

    protected ButtonType type;

    protected Rectangle buttonBody;
    protected String buttonText;
    protected Point textPosition;
    protected Font buttonFont;
    protected Color backgroundColor;
    protected Color edgeColor;
    protected Color textColor;
    protected Integer padding;
    protected GameCamera camera;



    public MyButton(){
        buttonBody = new Rectangle();
        buttonText = "";
        buttonFont = new Font("arial", Font.BOLD, 20);
        backgroundColor = new Color(0,0,0,0);
        edgeColor = Color.white;
        textColor = Color.white;
        padding = 10;
        textPosition = new Point(0,0);
    }

    public MyButton(Rectangle buttonBody, String buttonText){
        this.buttonBody = buttonBody;
        this.buttonText = buttonText;
        buttonFont = new Font("arial", Font.BOLD, 20);
        backgroundColor = new Color(0,0,0,0);
        edgeColor = Color.white;
        textColor = Color.white;
        padding = 10;
        textPosition = new Point(0,0);
    }

    public MyButton(Rectangle buttonBody, String buttonText, Point textPosition, Color backgroundColor, Color edgeColor, Color textColor, Integer padding, Font buttonFont){
        this.buttonBody = buttonBody;
        this.buttonText = buttonText;
        this.textPosition = textPosition;
        this.backgroundColor = backgroundColor;
        this.edgeColor = edgeColor;
        this.textColor = textColor;
        this.padding = padding;
        this.buttonFont = buttonFont;
    }


    public boolean isOnHover(Point p){
        return buttonBody.contains(p);
    }

    public void Draw(Graphics g){
        int newButtonX = buttonBody.x;
        int newButtonY = buttonBody.y;

        //the button can follow a camera
        if(camera!=null){
            newButtonX += (int) camera.getCameraCoordinates().getX();
            newButtonY += (int) camera.getCameraCoordinates().getY();
        }

        //background of a button
        g.setColor(edgeColor);
        g.drawRect(newButtonX,newButtonY, buttonBody.width, buttonBody.height);
        g.setColor(backgroundColor);
        g.fillRect(newButtonX + 2,newButtonY+2, buttonBody.width-2, buttonBody.height-2);

        //text of the button
        g.setColor(textColor);
        g.setFont(buttonFont);
        g.drawString(buttonText, newButtonX + padding, newButtonY + buttonBody.height - padding);
    }

    public abstract void ButtonPressed();


    public Rectangle getButtonBody() {
        return buttonBody;
    }
    public ButtonType getType() {
        return type;
    }
    public String getButtonText() {
        return buttonText;
    }
    public Font getButtonFont() {
        return buttonFont;
    }
    public Integer getPadding() {
        return padding;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }
    public void setButtonBody(Rectangle buttonBody) {
        this.buttonBody = buttonBody;
    }
    public void setButtonFont(Font buttonFont) {
        this.buttonFont = buttonFont;
    }
    public void setButtonText(String buttonText) {
        this.buttonText = buttonText;
    }
    public void setEdgeColor(Color edgeColor) {
        this.edgeColor = edgeColor;
    }
    public void setPadding(Integer padding) {
        this.padding = padding;
    }
    public void setTextColor(Color textColor) {
        this.textColor = textColor;
    }
    public void setType(ButtonType type) {
        this.type = type;
    }
    public void setCamera(GameCamera camera){
        this.camera = camera;
    }
}
