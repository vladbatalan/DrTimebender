package PaooGame.tiles.MapTiles;

import PaooGame.graphics.Assets;
import PaooGame.tiles.TileCollisionType.WholeTile;
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
