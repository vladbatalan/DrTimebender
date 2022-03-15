package PaooGame.tiles.tileCollisionType;

import PaooGame.tiles.Tile;
import PaooGame.tiles.TileType;

import java.awt.*;
import java.awt.image.BufferedImage;

public class WholeTile extends Tile {
    public WholeTile(BufferedImage image, TileType tileType) {
        super(image, tileType);
        this.collisionBox = new Rectangle(0,0,TILE_WIDTH, TILE_HEIGHT);
    }
}