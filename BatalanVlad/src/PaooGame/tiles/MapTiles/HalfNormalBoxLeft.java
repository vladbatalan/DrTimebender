package PaooGame.tiles.MapTiles;

import PaooGame.graphics.Assets;
import PaooGame.tiles.TileCollisionType.TopHalfTile;
import PaooGame.tiles.TileType;

public class HalfNormalBoxLeft extends TopHalfTile {
    public HalfNormalBoxLeft() {
        super(Assets.halfNormalBox[0], TileType.HalfNormalBoxLeft);
    }
    public boolean IsSolid()
    {
        return true;
    }
}
