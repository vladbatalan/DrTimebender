package PaooGame.Graphics.Animations.AnimationCollections;

import PaooGame.Graphics.Animations.AnimationList.PushButton.PushBlueButtonTurnOffType1;
import PaooGame.Graphics.Animations.AnimationList.PushButton.PushBlueButtonTurnOnType1;
import PaooGame.Graphics.Animations.AnimationList.PushButton.PushGreenButtonTurnOffType1;
import PaooGame.Graphics.Animations.AnimationList.PushButton.PushGreenButtonTurnOnType1;

public class PushGreenButtonType1AnimationCollection extends AnimationCollection {
    public PushGreenButtonType1AnimationCollection(){
        this.addAnimation(new PushGreenButtonTurnOnType1());
        this.addAnimation(new PushGreenButtonTurnOffType1());
    }
}
