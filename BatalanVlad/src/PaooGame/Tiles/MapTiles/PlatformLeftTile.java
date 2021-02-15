package PaooGame.Tiles.MapTiles;

import PaooGame.Graphics.Assets;
import PaooGame.Tiles.Tile;
import PaooGame.Tiles.TileCollisionType.TopHalfTile;
import PaooGame.Tiles.TileType;

import java.awt.image.BufferedImage;

public class PlatformLeftTile extends TopHalfTile {
    public PlatformLeftTile() {
        super(Assets.platformBox[0], TileType.PlatformLeftTile);
    }
    public boolean IsSolid()
    {
        return true;
    }
}
