package PaooGame.Tiles.MapTiles;

import PaooGame.Graphics.Assets;
import PaooGame.Tiles.Tile;
import PaooGame.Tiles.TileCollisionType.WholeTile;
import PaooGame.Tiles.TileType;

public class PlatformWholeLeft extends WholeTile {
    public PlatformWholeLeft() {
        super(Assets.wholePlatformBox[0], TileType.PlatformWholeLeft);
    }
    public boolean IsSolid()
    {
        return true;
    }
}
