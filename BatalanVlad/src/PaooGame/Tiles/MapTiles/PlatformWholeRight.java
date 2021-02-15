package PaooGame.Tiles.MapTiles;

import PaooGame.Graphics.Assets;
import PaooGame.Tiles.Tile;
import PaooGame.Tiles.TileCollisionType.WholeTile;
import PaooGame.Tiles.TileType;

public class PlatformWholeRight extends WholeTile {
    public PlatformWholeRight() {
        super(Assets.wholePlatformBox[2], TileType.PlatformWholeRight);
    }
    public boolean IsSolid()
    {
        return true;
    }
}
