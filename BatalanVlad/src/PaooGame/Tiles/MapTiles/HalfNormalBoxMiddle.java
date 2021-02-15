package PaooGame.Tiles.MapTiles;

import PaooGame.Graphics.Assets;
import PaooGame.Tiles.Tile;
import PaooGame.Tiles.TileCollisionType.TopHalfTile;
import PaooGame.Tiles.TileType;

public class HalfNormalBoxMiddle extends TopHalfTile {
    public HalfNormalBoxMiddle() {
        super(Assets.halfNormalBox[1], TileType.HalfNormalBoxMiddle);
    }
    public boolean IsSolid()
    {
        return true;
    }
}
