package PaooGame.Tiles.MapTiles;

import PaooGame.Graphics.Assets;
import PaooGame.Tiles.Tile;
import PaooGame.Tiles.TileCollisionType.TopHalfTile;
import PaooGame.Tiles.TileType;

public class HalfNormalBoxRight extends TopHalfTile {
    public HalfNormalBoxRight() {
        super(Assets.halfNormalBox[2], TileType.HalfNormalBoxRight);
    }
    public boolean IsSolid()
    {
        return true;
    }
}
