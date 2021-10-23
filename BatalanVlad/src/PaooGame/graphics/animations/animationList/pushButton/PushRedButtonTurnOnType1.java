package PaooGame.graphics.animations.animationList.pushButton;

import PaooGame.graphics.ImageLoader;
import PaooGame.graphics.SpriteSheet;
import PaooGame.graphics.animations.animationList.IAnimation;

public class PushRedButtonTurnOnType1 extends IAnimation {

    public PushRedButtonTurnOnType1(){
        SpriteSheet mySheet = new SpriteSheet(ImageLoader.LoadImage("/textures/Objects/buttons_spritesheet.png"));
        int width = 64;
        int height = 40;

        imagesArray.add(mySheet.crop(5,0, width, height));
    }

    @Override
    public String getAnimationName() {
        return "TurnedOn";
    }
}
