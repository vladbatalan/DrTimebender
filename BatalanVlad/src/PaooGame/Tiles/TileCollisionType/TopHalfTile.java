package PaooGame.Tiles.TileCollisionType;

import PaooGame.Tiles.Tile;
import PaooGame.Tiles.TileType;

import java.awt.*;
import java.awt.image.BufferedImage;

public class TopHalfTile extends Tile {
    public TopHalfTile(BufferedImage image, TileType tileType) {
        super(image, tileType);
        this.collisionBox = new Rectangle(0,0, TILE_WIDTH, TILE_HEIGHT/2);
    }
}
