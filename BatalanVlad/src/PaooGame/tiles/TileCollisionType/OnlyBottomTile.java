package PaooGame.tiles.TileCollisionType;

import PaooGame.tiles.Tile;
import PaooGame.tiles.TileType;

import java.awt.*;
import java.awt.image.BufferedImage;

public class OnlyBottomTile extends Tile {
    public OnlyBottomTile(BufferedImage image, TileType tileType) {
        super(image, tileType);
        this.collisionBox = new Rectangle(0,3*TILE_HEIGHT/4, TILE_WIDTH, TILE_HEIGHT/4);
    }
}
