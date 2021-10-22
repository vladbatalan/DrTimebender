package PaooGame.tiles.MapTiles;

import PaooGame.graphics.Assets;
import PaooGame.tiles.TileCollisionType.TopHalfTile;
import PaooGame.tiles.TileType;

public class PlatformRightTile extends TopHalfTile {
    public PlatformRightTile() {
        super(Assets.platformBox[2], TileType.PlatformRightTile);
    }
    public boolean IsSolid()
    {
        return true;
    }
}
