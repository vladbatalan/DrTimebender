package PaooGame.Graphics.Animations.AnimationCollections;

import PaooGame.Graphics.Animations.AnimationList.PushButton.PushBlueButtonTurnOffType1;
import PaooGame.Graphics.Animations.AnimationList.PushButton.PushBlueButtonTurnOnType1;
import PaooGame.Graphics.Animations.AnimationList.PushButton.PushRedButtonTurnOffType1;
import PaooGame.Graphics.Animations.AnimationList.PushButton.PushRedButtonTurnOnType1;

public class PushRedButtonType1AnimationCollection extends AnimationCollection {
    public PushRedButtonType1AnimationCollection(){
        this.addAnimation(new PushRedButtonTurnOnType1());
        this.addAnimation(new PushRedButtonTurnOffType1());
    }
}
