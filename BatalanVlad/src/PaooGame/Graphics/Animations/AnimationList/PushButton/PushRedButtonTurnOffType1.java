package PaooGame.Graphics.Animations.AnimationList.PushButton;

import PaooGame.Graphics.Animations.AnimationList.IAnimation;
import PaooGame.Graphics.ImageLoader;
import PaooGame.Graphics.SpriteSheet;

public class PushRedButtonTurnOffType1 extends IAnimation {

    public PushRedButtonTurnOffType1(){
        SpriteSheet mySheet = new SpriteSheet(ImageLoader.LoadImage("/textures/Objects/buttons_spritesheet.png"));
        int width = 64;
        int height = 40;
        
        imagesArray.add(mySheet.crop(4,0, width, height));
    }

    @Override
    public String getAnimationName() {
        return "TurnedOff";
    }
}
