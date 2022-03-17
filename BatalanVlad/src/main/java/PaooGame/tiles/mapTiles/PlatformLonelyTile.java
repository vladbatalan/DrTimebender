package PaooGame.tiles.mapTiles;

import PaooGame.graphics.Assets;
import PaooGame.tiles.tileCollisionType.TopHalfTile;
import PaooGame.tiles.TileType;

public class PlatformLonelyTile extends TopHalfTile {
    public PlatformLonelyTile() {
        super(Assets.platformBox[3], TileType.PlatformLonelyTile);
    }
    public boolean IsSolid()
    {
        return true;
    }
}

