package PaooGame.graphics.animations.animationList.pushButton;

import PaooGame.graphics.ImageLoader;
import PaooGame.graphics.SpriteSheet;
import PaooGame.graphics.animations.animationList.IAnimation;

public class PushBlueButtonTurnOffType1 extends IAnimation {

    public PushBlueButtonTurnOffType1(){
        SpriteSheet mySheet = new SpriteSheet(ImageLoader.LoadImage("/textures/Objects/buttons_spritesheet.png"));
        int width = 64;
        int height = 40;
        
        imagesArray.add(mySheet.crop(0,0, width, height));
    }

    @Override
    public String getAnimationName() {
        return "TurnedOff";
    }
}
