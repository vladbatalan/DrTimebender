package PaooGame.tiles.MapTiles;

import PaooGame.graphics.Assets;
import PaooGame.tiles.TileCollisionType.WholeTile;
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
