package PaooGame.Tiles.MapTiles;

import PaooGame.Graphics.Assets;
import PaooGame.Tiles.Tile;
import PaooGame.Tiles.TileCollisionType.TopHalfTile;
import PaooGame.Tiles.TileType;

import java.awt.image.BufferedImage;

public class PlatformMiddleTile extends TopHalfTile {
    public PlatformMiddleTile() {
        super(Assets.platformBox[1], TileType.PlatformMiddleTile);
    }
    public boolean IsSolid()
    {
        return true;
    }
}
