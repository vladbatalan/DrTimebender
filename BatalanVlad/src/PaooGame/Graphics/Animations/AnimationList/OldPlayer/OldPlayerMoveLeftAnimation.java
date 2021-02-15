package PaooGame.Graphics.Animations.AnimationList.OldPlayer;

import PaooGame.Graphics.Animations.AnimationList.IAnimation;
import PaooGame.Graphics.ImageLoader;
import PaooGame.Graphics.SpriteSheet;

import java.awt.image.BufferedImage;

public class OldPlayerMoveLeftAnimation extends IAnimation {

    public OldPlayerMoveLeftAnimation(){
        //init assets
        SpriteSheet playerSpriteSheet = new SpriteSheet(ImageLoader.LoadImage("/textures/Player/OldPlayerSprite.png"));
        //the size of the tile to be cropped
        int width = 32;
        int height = 64;
        BufferedImage left[] = {
                playerSpriteSheet.crop(0, 0, width, height),
                playerSpriteSheet.crop(0, 1, width, height),
                playerSpriteSheet.crop(0 , 2, width, height)
        };
        imagesArray.add(left[1]);
        imagesArray.add(left[0]);
        imagesArray.add(left[2]);
    }

    @Override
    public String getAnimationName() {
        return "MoveLeftAnimation";
    }
}
