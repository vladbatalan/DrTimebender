package PaooGame.tiles.mapTiles;

import PaooGame.graphics.Assets;
import PaooGame.tiles.tileCollisionType.WholeTile;
import PaooGame.tiles.TileType;

public class PlatformWholeMiddle extends WholeTile {
    public PlatformWholeMiddle() {
        super(Assets.wholePlatformBox[1], TileType.PlatformWholeMiddle);
    }
    public boolean IsSolid()
    {
        return true;
    }
}
