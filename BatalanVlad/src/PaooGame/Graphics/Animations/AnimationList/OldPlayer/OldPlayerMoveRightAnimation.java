package PaooGame.Graphics.Animations.AnimationList.OldPlayer;

import PaooGame.Graphics.Animations.AnimationList.IAnimation;
import PaooGame.Graphics.ImageLoader;
import PaooGame.Graphics.SpriteSheet;

import java.awt.image.BufferedImage;

public class OldPlayerMoveRightAnimation extends IAnimation {

    public OldPlayerMoveRightAnimation(){
        //init assets
        SpriteSheet playerSpriteSheet = new SpriteSheet(ImageLoader.LoadImage("/textures/Player/OldPlayerSprite.png"));
        //the size of the tile to be cropped
        int width = 32;
        int height = 64;
        BufferedImage right[] = {
                playerSpriteSheet.crop(1, 0, width, height),
                playerSpriteSheet.crop(1 , 1, width, height),
                playerSpriteSheet.crop(1 , 2, width, height)
        };
        imagesArray.add(right[1]);
        imagesArray.add(right[0]);
        imagesArray.add(right[2]);
    }

    @Override
    public String getAnimationName() {
        return "MoveRightAnimation";
    }
}
