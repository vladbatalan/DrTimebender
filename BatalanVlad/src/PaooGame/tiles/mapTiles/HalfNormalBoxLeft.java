package PaooGame.tiles.mapTiles;

import PaooGame.graphics.Assets;
import PaooGame.tiles.tileCollisionType.TopHalfTile;
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
