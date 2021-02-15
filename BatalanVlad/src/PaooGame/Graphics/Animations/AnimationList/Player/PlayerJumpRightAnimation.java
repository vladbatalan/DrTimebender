package PaooGame.Graphics.Animations.AnimationList.Player;

import PaooGame.Graphics.Animations.AnimationList.IAnimation;
import PaooGame.Graphics.ImageLoader;
import PaooGame.Graphics.SpriteSheet;

public class PlayerJumpRightAnimation extends IAnimation {

    public PlayerJumpRightAnimation(){
        //init assets
        SpriteSheet playerSpriteSheet = new SpriteSheet(ImageLoader.LoadImage("/textures/Player/PlayerSprite.png"));
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
