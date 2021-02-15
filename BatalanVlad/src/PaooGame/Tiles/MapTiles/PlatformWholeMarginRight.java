package PaooGame.Tiles.MapTiles;

import PaooGame.Graphics.Assets;
import PaooGame.Tiles.Tile;
import PaooGame.Tiles.TileCollisionType.WholeTile;
import PaooGame.Tiles.TileType;

public class PlatformWholeMarginRight extends WholeTile {
    public PlatformWholeMarginRight() {
        super(Assets.wholePlatformBox[4], TileType.PlatformWholeMarginRight);
    }
    public boolean IsSolid()
    {
        return true;
    }
}
