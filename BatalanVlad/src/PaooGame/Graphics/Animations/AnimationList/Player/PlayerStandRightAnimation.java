package PaooGame.Graphics.Animations.AnimationList.Player;

import PaooGame.Graphics.Animations.AnimationList.IAnimation;
import PaooGame.Graphics.ImageLoader;
import PaooGame.Graphics.SpriteSheet;

public class PlayerStandRightAnimation extends IAnimation {

    public PlayerStandRightAnimation(){
        //init assets
        SpriteSheet playerSpriteSheet = new SpriteSheet(ImageLoader.LoadImage("/textures/Player/PlayerSprite.png"));
        //the size of the tile to be cropped
        int width = 32;
        int height = 64;
        imagesArray.add( playerSpriteSheet.crop(1, 0, width, height));
    }

    @Override
    public String getAnimationName() {
        return "StandRightAnimation";
    }
}
