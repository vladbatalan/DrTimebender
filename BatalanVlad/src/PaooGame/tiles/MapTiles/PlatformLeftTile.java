package PaooGame.tiles.MapTiles;

import PaooGame.graphics.Assets;
import PaooGame.tiles.TileCollisionType.TopHalfTile;
import PaooGame.tiles.TileType;

public class PlatformLeftTile extends TopHalfTile {
    public PlatformLeftTile() {
        super(Assets.platformBox[0], TileType.PlatformLeftTile);
    }
    public boolean IsSolid()
    {
        return true;
    }
}
