package PaooGame.tiles.mapTiles;

import PaooGame.graphics.Assets;
import PaooGame.tiles.tileCollisionType.WholeTile;
import PaooGame.tiles.TileType;

public class PlatformWholeMarginLeft extends WholeTile {
    public PlatformWholeMarginLeft() {
        super(Assets.wholePlatformBox[3], TileType.PlatformWholeMarginLeft);
    }
    public boolean IsSolid()
    {
        return true;
    }
}
