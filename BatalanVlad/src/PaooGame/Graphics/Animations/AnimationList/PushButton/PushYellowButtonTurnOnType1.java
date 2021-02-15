package PaooGame.Graphics.Animations.AnimationList.PushButton;

import PaooGame.Graphics.Animations.AnimationList.IAnimation;
import PaooGame.Graphics.ImageLoader;
import PaooGame.Graphics.SpriteSheet;

public class PushYellowButtonTurnOnType1 extends IAnimation {

    public PushYellowButtonTurnOnType1(){
        SpriteSheet mySheet = new SpriteSheet(ImageLoader.LoadImage("/textures/Objects/buttons_spritesheet.png"));
        int width = 64;
        int height = 40;

        imagesArray.add(mySheet.crop(3,0, width, height));
    }

    @Override
    public String getAnimationName() {
        return "TurnedOn";
    }
}
