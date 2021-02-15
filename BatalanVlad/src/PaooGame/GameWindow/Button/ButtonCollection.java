package PaooGame.GameWindow.Button;

import java.awt.*;
import java.util.ArrayList;

public class ButtonCollection {
    private ArrayList<MyButton> buttonCollection = new ArrayList<>();


    public void Draw(Graphics g){
        for(MyButton btn : buttonCollection){
            btn.Draw(g);
        }
    }

    public boolean CheckMousePress(Point p){
        boolean ok = false;
        for(MyButton btn : buttonCollection){
            if(btn.isOnHover(p)){
                btn.ButtonPressed();
                ok = true;
            }
        }
        return ok;
    }

    public void ClearButtons(){
        buttonCollection.clear();
    }
    public void AddElement(MyButton btn){
        buttonCollection.add(btn);
    }
    public void RemoveElement(MyButton btn){
        buttonCollection.remove(btn);
    }
    public ArrayList<MyButton> getButtonCollection(){
        return buttonCollection;
    }

}
