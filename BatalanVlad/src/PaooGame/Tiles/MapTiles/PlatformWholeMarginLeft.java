package PaooGame.Tiles.MapTiles;

import PaooGame.Graphics.Assets;
import PaooGame.Tiles.Tile;
import PaooGame.Tiles.TileCollisionType.WholeTile;
import PaooGame.Tiles.TileType;

public class PlatformWholeMarginLeft extends WholeTile {
    public PlatformWholeMarginLeft() {
        super(Assets.wholePlatformBox[3], TileType.PlatformWholeMarginLeft);
    }
    public boolean IsSolid()
    {
        return true;
    }
}
