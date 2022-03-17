package PaooGame.graphics.animations.animationList.player;

import PaooGame.graphics.ImageLoader;
import PaooGame.graphics.SpriteSheet;
import PaooGame.graphics.animations.animationList.IAnimation;

public class PlayerJumpLeftAnimation extends IAnimation {

    public PlayerJumpLeftAnimation(){
        //init assets
        SpriteSheet playerSpriteSheet = new SpriteSheet(ImageLoader.LoadImage("/textures/Player/PlayerSprite.png"));
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
