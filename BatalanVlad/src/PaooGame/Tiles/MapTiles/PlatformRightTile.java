package PaooGame.Tiles.MapTiles;

import PaooGame.Graphics.Assets;
import PaooGame.Tiles.Tile;
import PaooGame.Tiles.TileCollisionType.TopHalfTile;
import PaooGame.Tiles.TileType;

import java.awt.image.BufferedImage;

public class PlatformRightTile extends TopHalfTile {
    public PlatformRightTile() {
        super(Assets.platformBox[2], TileType.PlatformRightTile);
    }
    public boolean IsSolid()
    {
        return true;
    }
}
