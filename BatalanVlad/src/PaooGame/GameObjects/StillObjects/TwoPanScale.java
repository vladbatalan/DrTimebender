package PaooGame.GameObjects.StillObjects;

import PaooGame.GameObjects.ISwitchable;
import PaooGame.GameObjects.ObjectID;
import PaooGame.Graphics.Animations.AnimationCollections.ObjectiveAnimationCollection;
import PaooGame.Physics.Body;
import PaooGame.Physics.PVector;
import PaooGame.Tiles.Map;
import com.sun.xml.internal.ws.api.addressing.OneWayFeature;

import java.awt.*;

public class TwoPanScale extends StillObject{
    private ScalePan firstPan;
    private ScalePan secondPan;

    //this object is abstract and got no initial position, but got 2 ScalePans he can operate with
    public TwoPanScale(ScalePan firstPan, ScalePan secondPan){
        this.id = ObjectID.TwoPanScale;
        this.body = new Body(new PVector(), 0, 0, 0);
        this.body.setMobility(false);

        this.firstPan = firstPan;
        this.secondPan = secondPan;
    }
    public TwoPanScale(ScalePan firstPan, ScalePan secondPan, Color panMatchingColors){
        this.id = ObjectID.TwoPanScale;
        this.body = new Body(new PVector(), 0, 0, 0);
        this.body.setMobility(false);

        this.firstPan = firstPan;
        this.secondPan = secondPan;

        // both have the same color
        firstPan.setBackgroundColor(panMatchingColors);
        secondPan.setBackgroundColor(panMatchingColors);
    }

    @Override
    public void Draw(Graphics g) {
        firstPan.Draw(g);
        secondPan.Draw(g);
    }

    @Override
    public void Update(Map currentMap) {

        // Calculating the target positions of the pans
        float mass1 = firstPan.getTotalMass();
        float mass2 = secondPan.getTotalMass();

        if(mass1 == mass2){
            firstPan.setTargetedHeight(firstPan.getMidTargetHeight());
            secondPan.setTargetedHeight(secondPan.getMidTargetHeight());
        }
        if(mass1 > mass2){
            firstPan.setTargetedHeight(firstPan.getMinTargetHeight());
            secondPan.setTargetedHeight(secondPan.getMaxTargetHeight());
        }
        if(mass1 < mass2){
            firstPan.setTargetedHeight(firstPan.getMaxTargetHeight());
            secondPan.setTargetedHeight(secondPan.getMinTargetHeight());
        }

        // Updating the two pans
        firstPan.Update(currentMap);
        secondPan.Update(currentMap);
    }

    public void setMoveCompleteTime(int time){
        firstPan.setTimeToPath(time);
        secondPan.setTimeToPath(time);
    }

    public void resetToInitialState(){
        // reset both pans to position
        float mass1 = firstPan.getBody().getMass();
        float mass2 = secondPan.getBody().getMass();

        PVector newFirst = firstPan.getBody().getPosition();
        PVector newSecond = secondPan.getBody().getPosition();
        if(mass1 == mass2){
            newFirst = new PVector(firstPan.getBody().getPosition().getX(), firstPan.getMidTargetHeight());
            newSecond = new PVector(secondPan.getBody().getPosition().getX(), secondPan.getMidTargetHeight());
        }else{
            if(mass2 < mass1){
                newFirst = new PVector(firstPan.getBody().getPosition().getX(), firstPan.getMinTargetHeight());
                newSecond = new PVector(secondPan.getBody().getPosition().getX(), secondPan.getMaxTargetHeight());
            }else{
                newFirst = new PVector(firstPan.getBody().getPosition().getX(), firstPan.getMaxTargetHeight());
                newSecond = new PVector(secondPan.getBody().getPosition().getX(), secondPan.getMinTargetHeight());
            }
        }

        firstPan.getBody().setPosition(newFirst);
        secondPan.getBody().setPosition(newSecond);
    }
    public ScalePan getFirstPan() {
        return firstPan;
    }

    public ScalePan getSecondPan() {
        return secondPan;
    }
}
