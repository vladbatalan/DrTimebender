package PaooGame.Graphics.Animations.AnimationCollections;

import PaooGame.Graphics.Animations.AnimationList.PushButton.PushBlueButtonTurnOffType1;
import PaooGame.Graphics.Animations.AnimationList.PushButton.PushBlueButtonTurnOnType1;
import PaooGame.Graphics.Animations.AnimationList.PushButton.PushYellowButtonTurnOffType1;
import PaooGame.Graphics.Animations.AnimationList.PushButton.PushYellowButtonTurnOnType1;

public class PushYellowButtonType1AnimationCollection extends AnimationCollection {
    public PushYellowButtonType1AnimationCollection(){
        this.addAnimation(new PushYellowButtonTurnOnType1());
        this.addAnimation(new PushYellowButtonTurnOffType1());
    }
}
