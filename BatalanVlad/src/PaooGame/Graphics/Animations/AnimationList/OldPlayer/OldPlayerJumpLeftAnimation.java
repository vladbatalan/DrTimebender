package PaooGame.Graphics.Animations.AnimationList.OldPlayer;

import PaooGame.Graphics.Animations.AnimationList.IAnimation;
import PaooGame.Graphics.ImageLoader;
import PaooGame.Graphics.SpriteSheet;

public class OldPlayerJumpLeftAnimation extends IAnimation {

    public OldPlayerJumpLeftAnimation(){
        //init assets
        SpriteSheet playerSpriteSheet = new SpriteSheet(ImageLoader.LoadImage("/textures/Player/OldPlayerSprite.png"));
        //the size of the tile to be cropped
        int width = 32;
        int height = 64;
        imagesArray.add(playerSpriteSheet.crop(0, 1, width, height));
    }

    @Override
    public String getAnimationName() {
        return "JumpLeftAnimation";
    }
}
