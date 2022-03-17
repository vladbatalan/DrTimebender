package PaooGame.gameObjects.stillObjects;

import PaooGame.Game;
import PaooGame.actionTimers.actions.DelayTurnOff;
import PaooGame.actionTimers.actions.DelayTurnOn;
import PaooGame.actionTimers.actions.IAction;
import PaooGame.actionTimers.DelayedActionTimer;
import PaooGame.actionTimers.IActionTimer;
import PaooGame.actionTimers.timeInterupters.GameStateGameIntrerupter;
import PaooGame.gameObjects.handler.GameObjectHandler;
import PaooGame.gameObjects.ISwitch;
import PaooGame.gameObjects.ISwitchable;
import PaooGame.gameObjects.ObjectID;
import PaooGame.gameObjects.effectObjects.TurnOffObjectEffect;
import PaooGame.gameObjects.effectObjects.TurnOnObjectEffect;
import PaooGame.gameObjects.mobileObjects.MobileObject;
import PaooGame.gameWindow.utils.FontUtils;
import PaooGame.graphics.animations.animationCollections.PushBlueButtonType1AnimationCollection;
import PaooGame.graphics.animations.animationCollections.PushGreenButtonType1AnimationCollection;
import PaooGame.graphics.animations.animationCollections.PushRedButtonType1AnimationCollection;
import PaooGame.graphics.animations.animationCollections.PushYellowButtonType1AnimationCollection;
import PaooGame.physics.Body;
import PaooGame.physics.PointVector;
import PaooGame.tiles.Map;
import javafx.util.Pair;

import java.awt.*;
import java.util.ArrayList;

public class PushButton extends StillObject implements ISwitch {
    private ArrayList<SwitchCommand> affectedByThis = new ArrayList<>();
    private boolean isSwitched;
    private String descriptionString = "";

    private class SwitchCommand{
        public ISwitchable switchable;
        public String command;
        public boolean frontOfChange;

        public SwitchCommand(ISwitchable switchable, String command, boolean frontOfChange){
            this.command = command;
            this.frontOfChange = frontOfChange;
            this.switchable = switchable;
        }
    }


    public PushButton(PointVector position){
        this.id = ObjectID.PushButton;
        this.body = new Body(position, 32, 20, 50);
        this.body.setMobility(false);
        this.body.setBodyColor(new Color(0,0,0,0));
        this.animation = new PushBlueButtonType1AnimationCollection();
        isSwitched = false;
    }

    public void changeButtonType(int type, int color){
        //color = 0 => blue
        if(color == 0) animation = new PushBlueButtonType1AnimationCollection();
        //color = 1 => yellow
        if(color == 1) animation = new PushYellowButtonType1AnimationCollection();
        //color = 2 => red
        if(color == 2) animation = new PushRedButtonType1AnimationCollection();
        //color = 3 => green
        if(color == 3) animation = new PushGreenButtonType1AnimationCollection();
    }

    @Override
    public void Draw(Graphics g) {
        if(isSwitched)
            animation.displayAnimation("TurnedOn", body, g);
        else
            animation.displayAnimation("TurnedOff", body, g);

        if(!descriptionString.isEmpty()) {
            //calculate first the position of the text
            Font font = new Font("arial", Font.BOLD, 20);
            Pair<Integer, Integer> fontPair = FontUtils.getFontSize(font, g, descriptionString);
            int fontWidth = fontPair.getKey();

            PointVector centered = new PointVector(body.getPosition().getX() + body.getBodyWidth()/2 - fontWidth/3, body.getPosition().getY()+body.getBodyHeight()+20);
            g.setColor(Color.white);
            g.drawString(descriptionString, (int)centered.getX(), (int)centered.getY());
            //g.setColor(Color.black);
            //g.drawString(descriptionString, (int)centered.getX() + 1, (int)centered.getY() + 1);
        }
    }

    @Override
    public void Update(Map currentMap) {
        //body.Update(currentMap);
        GameObjectHandler handler = Game.currentLevel.getHandler();
        boolean oldSwitchState = isSwitched;
        // assume it is not switched
        isSwitched = false;
        // we need to check if there is any OldPlayerObject or PlayerObject in collision with the lever
        for(int index = 0; index < handler.getMobileObjects().size(); index ++){
            MobileObject mobile = handler.getMobileObjects().get(index);
            if(mobile.getId() == ObjectID.OldPlayerInstance || mobile.getId() == ObjectID.Player){
                if(this.getBody().getHitBox().intersects(mobile.getBody().getHitBox())){
                    isSwitched = true;
                    break;
                }
            }
        }
        // if the oldStateChanged, we need to update the ISwitchers
        if(isSwitched != oldSwitchState){
            for(SwitchCommand myCommand : affectedByThis){
                ISwitchable myAffectedObj = myCommand.switchable;
                boolean front = myCommand.frontOfChange;
                boolean createTurnOn = true;

                // the front describes the state of the door
                if(front){
                    if(!isSwitched) createTurnOn = false;
                }else{
                    if(isSwitched) createTurnOn = false;
                }

                // create effect that shows the affected object
                if(createTurnOn){
                    createTurnOnEffectOverPosition(myAffectedObj, myCommand.command);
                }else{
                    createTurnOffEffectOverPosition(myAffectedObj, myCommand.command);
                }
            }
        }
    }

    // on switch, this creates an effect that shows the affected object
    private void createTurnOnEffectOverPosition(ISwitchable affected, String myCommand){
        // create turn on effect
        TurnOnObjectEffect turnOn = new TurnOnObjectEffect(body.getPosition(), affected.getSwitchablePosition());
        Game.currentLevel.getHandler().addStillObject(turnOn);

        // produce open at delay
        IAction turnOnAction = new DelayTurnOn(affected, myCommand);
        IActionTimer turnOnActionTimer = new DelayedActionTimer(turnOnAction, turnOn.getTimeUntilDisappear());
        turnOnActionTimer.addTimerInterupter(new GameStateGameIntrerupter());
        turnOnActionTimer.startTimer();
    }

    // on switch, this creates an effect that shows the affected object
    private void createTurnOffEffectOverPosition(ISwitchable affected,String myCommand){
        TurnOffObjectEffect turnOff = new TurnOffObjectEffect(body.getPosition(), affected.getSwitchablePosition());
        Game.currentLevel.getHandler().addStillObject(turnOff);

        // produce open at delay
        IAction turnOffAction = new DelayTurnOff(affected, myCommand);
        IActionTimer turnOffActionTimer = new DelayedActionTimer(turnOffAction, turnOff.getTimeUntilDisappear());
        turnOffActionTimer.addTimerInterupter(new GameStateGameIntrerupter());
        turnOffActionTimer.startTimer();
    }


    // adding a pair to the current affectedByThis array
    // frontOfChange designs how an IShiwthable should change based on the change state'
    // for example: frontOfChange = true => it will be turned on when the switch is turned on
    //              frontOfChange = false => it will be turned off when the switch is turned on
    public void addAffectedObject(ISwitchable obj, boolean frontOfChange){
        addAffectedObject(obj, frontOfChange, "");
    }

    public void addAffectedObject(ISwitchable obj, boolean frontOfChange, String command) {
        SwitchCommand myCommand = new SwitchCommand(obj, command, frontOfChange);

        //should check if the object is in the pair, just update the state
        for(int index = 0; index < affectedByThis.size(); index ++){
            SwitchCommand crrPair = affectedByThis.get(index);
            if(crrPair.switchable == obj) {
                affectedByThis.set(index, crrPair);
                return;
            }
        }
        // if it doesn t exist in the array, just add it
        affectedByThis.add(myCommand);
    }

    // shotrtcut for implied declaration
    public void addAffectedObject(ISwitchable obj){
        addAffectedObject(obj, true, "");
    }
    public void setDescriptionString(String descriptionString){
        this.descriptionString = descriptionString;
    }


}
