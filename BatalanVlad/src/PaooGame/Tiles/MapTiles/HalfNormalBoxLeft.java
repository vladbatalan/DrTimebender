package PaooGame.Tiles.MapTiles;

import PaooGame.Graphics.Assets;
import PaooGame.Tiles.Tile;
import PaooGame.Tiles.TileCollisionType.TopHalfTile;
import PaooGame.Tiles.TileType;

public class HalfNormalBoxLeft extends TopHalfTile {
    public HalfNormalBoxLeft() {
        super(Assets.halfNormalBox[0], TileType.HalfNormalBoxLeft);
    }
    public boolean IsSolid()
    {
        return true;
    }
}
