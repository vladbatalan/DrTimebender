package PaooGame.graphics.animations.animationList.oldPlayer;

import PaooGame.graphics.ImageLoader;
import PaooGame.graphics.SpriteSheet;
import PaooGame.graphics.animations.animationList.IAnimation;

public class OldPlayerJumpRightAnimation extends IAnimation {

    public OldPlayerJumpRightAnimation(){
        //init assets
        SpriteSheet playerSpriteSheet = new SpriteSheet(ImageLoader.LoadImage("/textures/Player/OldPlayerSprite.png"));
        //the size of the tile to be cropped
        int width = 32;
        int height = 64;
        imagesArray.add(playerSpriteSheet.crop(1 , 1, width, height));
    }

    @Override
    public String getAnimationName() {
        return "JumpRightAnimation";
    }
}
