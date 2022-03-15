package PaooGame.tiles.mapTiles;

import PaooGame.graphics.Assets;
import PaooGame.tiles.tileCollisionType.WholeTile;
import PaooGame.tiles.TileType;

public class PlatformWholeRight extends WholeTile {
    public PlatformWholeRight() {
        super(Assets.wholePlatformBox[2], TileType.PlatformWholeRight);
    }
    public boolean IsSolid()
    {
        return true;
    }
}
