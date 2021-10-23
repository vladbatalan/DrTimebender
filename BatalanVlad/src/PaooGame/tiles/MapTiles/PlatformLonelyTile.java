package PaooGame.tiles.MapTiles;

import PaooGame.graphics.Assets;
import PaooGame.tiles.TileCollisionType.TopHalfTile;
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

