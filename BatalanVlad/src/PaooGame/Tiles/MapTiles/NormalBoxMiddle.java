package PaooGame.Tiles.MapTiles;

import PaooGame.Graphics.Assets;
import PaooGame.Tiles.Tile;
import PaooGame.Tiles.TileCollisionType.WholeTile;
import PaooGame.Tiles.TileType;

public class NormalBoxMiddle extends WholeTile {
    public NormalBoxMiddle() {
        super(Assets.normalBox[1], TileType.NormalBoxMiddle);
    }
    public boolean IsSolid()
    {
        return true;
    }
}
