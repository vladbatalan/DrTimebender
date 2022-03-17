package PaooGame.tiles.mapTiles;

import PaooGame.graphics.Assets;
import PaooGame.tiles.tileCollisionType.WholeTile;
import PaooGame.tiles.TileType;

public class PlatformWholeLeft extends WholeTile {
    public PlatformWholeLeft() {
        super(Assets.wholePlatformBox[0], TileType.PlatformWholeLeft);
    }
    public boolean IsSolid()
    {
        return true;
    }
}
