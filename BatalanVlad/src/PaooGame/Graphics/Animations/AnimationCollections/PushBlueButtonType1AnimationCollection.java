package PaooGame.Graphics.Animations.AnimationCollections;

import PaooGame.Graphics.Animations.AnimationList.PushButton.PushBlueButtonTurnOffType1;
import PaooGame.Graphics.Animations.AnimationList.PushButton.PushBlueButtonTurnOnType1;

public class PushBlueButtonType1AnimationCollection extends AnimationCollection {
    public PushBlueButtonType1AnimationCollection(){
        this.addAnimation(new PushBlueButtonTurnOnType1());
        this.addAnimation(new PushBlueButtonTurnOffType1());
    }
}
