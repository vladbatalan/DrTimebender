package PaooGame.Tiles.MapTiles;

import PaooGame.Graphics.Assets;
import PaooGame.Tiles.Tile;
import PaooGame.Tiles.TileCollisionType.WholeTile;
import PaooGame.Tiles.TileType;

public class PlatformWholeMiddle extends WholeTile {
    public PlatformWholeMiddle() {
        super(Assets.wholePlatformBox[1], TileType.PlatformWholeMiddle);
    }
    public boolean IsSolid()
    {
        return true;
    }
}
