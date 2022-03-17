package PaooGame.tiles.mapTiles;

import PaooGame.graphics.Assets;
import PaooGame.tiles.tileCollisionType.TopHalfTile;
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
