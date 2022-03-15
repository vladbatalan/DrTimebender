package PaooGame.tiles.mapTiles;

import PaooGame.graphics.Assets;
import PaooGame.tiles.tileCollisionType.WholeTile;
import PaooGame.tiles.TileType;

public class PlatformWholeMarginRight extends WholeTile {
    public PlatformWholeMarginRight() {
        super(Assets.wholePlatformBox[4], TileType.PlatformWholeMarginRight);
    }
    public boolean IsSolid()
    {
        return true;
    }
}
