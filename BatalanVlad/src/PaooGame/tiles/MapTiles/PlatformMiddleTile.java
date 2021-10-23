package PaooGame.tiles.MapTiles;

import PaooGame.graphics.Assets;
import PaooGame.tiles.TileCollisionType.TopHalfTile;
import PaooGame.tiles.TileType;

public class PlatformMiddleTile extends TopHalfTile {
    public PlatformMiddleTile() {
        super(Assets.platformBox[1], TileType.PlatformMiddleTile);
    }
    public boolean IsSolid()
    {
        return true;
    }
}
