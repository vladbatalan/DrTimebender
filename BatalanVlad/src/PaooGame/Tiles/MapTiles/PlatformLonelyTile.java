package PaooGame.Tiles.MapTiles;

import PaooGame.Graphics.Assets;
import PaooGame.Tiles.Tile;
import PaooGame.Tiles.TileCollisionType.TopHalfTile;
import PaooGame.Tiles.TileType;

import java.awt.image.BufferedImage;

public class PlatformLonelyTile extends TopHalfTile {
    public PlatformLonelyTile() {
        super(Assets.platformBox[3], TileType.PlatformLonelyTile);
    }
    public boolean IsSolid()
    {
        return true;
    }
}

