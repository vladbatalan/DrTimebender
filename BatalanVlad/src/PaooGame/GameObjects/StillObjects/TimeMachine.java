package PaooGame.GameObjects.StillObjects;

import PaooGame.ActionTimers.TimeInterupters.GameStateGameIntrerupter;
import PaooGame.ActionTimers.TimeInterupters.PlayerStopsInteractingWithTimeMachine;
import PaooGame.GameObjects.ObjectID;
import PaooGame.GameWindow.StringDisplay.ScreenTag;
import PaooGame.Graphics.Animations.AnimationCollections.TimeMachineAnimationCollection;
import PaooGame.Levels.LevelFlagsSystem;
import PaooGame.Physics.Body;
import PaooGame.Physics.PVector;
import PaooGame.Tiles.Map;

import java.awt.*;

public class TimeMachine extends StillObject {
    private ScreenTag helpOnInteractionTop;
    private ScreenTag helpOnInteractionBottom;

    private ScreenTag helpOnInteractionShadowTop;
    private ScreenTag helpOnInteractionShadowBottom;

    public TimeMachine(PVector position, int BODY_WIDTH, int BODY_HEIGHT, float mass){
        this.id = ObjectID.TimeMachine;
        this.body = new Body(position, BODY_WIDTH, BODY_HEIGHT, mass);
        this.body.setMobility(false);
        this.body.setBodyColor(new Color(0,0,0,0));
        this.animation = new TimeMachineAnimationCollection();
        helpStringDisplayInitiate();
    }

    public TimeMachine(PVector position){
        this.id = ObjectID.TimeMachine;
        this.body = new Body(position, 50, 100, 50);
        this.body.setMobility(false);
        this.body.setBodyColor(new Color(0,0,0,0));
        this.animation = new TimeMachineAnimationCollection();
        helpStringDisplayInitiate();
    }

    private void helpStringDisplayInitiate(){
        helpOnInteractionTop = new ScreenTag("Press \"Space\"", 12);
        helpOnInteractionBottom = new ScreenTag("to time travel.", 12);
        helpOnInteractionShadowTop = new ScreenTag("Press \"Space\"", 12);
        helpOnInteractionShadowBottom = new ScreenTag("to time travel.", 12);
        helpOnInteractionShadowBottom.setColor(Color.BLACK);
        helpOnInteractionShadowTop.setColor(Color.BLACK);

        // if player stops interracting, the string stops displaying
        helpOnInteractionShadowTop.addTimerIntreruptor(new PlayerStopsInteractingWithTimeMachine());
        helpOnInteractionShadowBottom.addTimerIntreruptor(new PlayerStopsInteractingWithTimeMachine());
        helpOnInteractionTop.addTimerIntreruptor(new PlayerStopsInteractingWithTimeMachine());
        helpOnInteractionBottom.addTimerIntreruptor(new PlayerStopsInteractingWithTimeMachine());
        helpOnInteractionShadowTop.addTimerIntreruptor(new GameStateGameIntrerupter());
        helpOnInteractionShadowBottom.addTimerIntreruptor(new GameStateGameIntrerupter());
        helpOnInteractionTop.addTimerIntreruptor(new GameStateGameIntrerupter());
        helpOnInteractionBottom.addTimerIntreruptor(new GameStateGameIntrerupter());
    }

    public void displayHelpString() {
        if (!helpOnInteractionTop.getIsOnShow() && !LevelFlagsSystem.isOnReset) {
            helpOnInteractionShadowTop.showScreenTag((int) (body.getPosition().getX() + body.getBodyWidth() / 2 + 2), (int) body.getPosition().getY() - 30 + 2);
            helpOnInteractionTop.showScreenTag((int) (body.getPosition().getX() + body.getBodyWidth() / 2), (int) body.getPosition().getY() - 30);

            helpOnInteractionShadowBottom.showScreenTag((int) (body.getPosition().getX() + body.getBodyWidth() / 2 + 2), (int) body.getPosition().getY() - 10 + 2);
            helpOnInteractionBottom.showScreenTag((int) (body.getPosition().getX() + body.getBodyWidth() / 2), (int) body.getPosition().getY() - 10);
        }
    }

    @Override
    public void Draw(Graphics g) {
        animation.displayAnimation("StandAnimation", body, g);
    }

    @Override
    public void Update(Map currentMap) {

    }
}
