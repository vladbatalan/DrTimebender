package PaooGame.tiles.MapTiles;

import PaooGame.graphics.Assets;
import PaooGame.tiles.TileCollisionType.WholeTile;
import PaooGame.tiles.TileType;

public class NormalBoxRight extends WholeTile {
    public NormalBoxRight() {
        super(Assets.normalBox[2], TileType.NormalBoxRight);
    }
    public boolean IsSolid()
    {
        return true;
    }
}
