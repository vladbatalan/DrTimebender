package PaooGame.Tiles.MapTiles;

import PaooGame.Graphics.Assets;
import PaooGame.Tiles.Tile;
import PaooGame.Tiles.TileCollisionType.WholeTile;
import PaooGame.Tiles.TileType;

public class NormalBoxRight extends WholeTile {
    public NormalBoxRight() {
        super(Assets.normalBox[2], TileType.NormalBoxRight);
    }
    public boolean IsSolid()
    {
        return true;
    }
}
