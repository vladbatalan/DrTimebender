package PaooGame.tiles.mapTiles;

import PaooGame.graphics.Assets;
import PaooGame.tiles.tileCollisionType.TopHalfTile;
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
