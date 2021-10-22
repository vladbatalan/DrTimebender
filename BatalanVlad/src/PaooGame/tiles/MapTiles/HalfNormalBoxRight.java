package PaooGame.tiles.MapTiles;

import PaooGame.graphics.Assets;
import PaooGame.tiles.TileCollisionType.TopHalfTile;
import PaooGame.tiles.TileType;

public class HalfNormalBoxRight extends TopHalfTile {
    public HalfNormalBoxRight() {
        super(Assets.halfNormalBox[2], TileType.HalfNormalBoxRight);
    }
    public boolean IsSolid()
    {
        return true;
    }
}
