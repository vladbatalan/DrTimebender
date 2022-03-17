package PaooGame.tiles.mapTiles;

import PaooGame.graphics.Assets;
import PaooGame.tiles.tileCollisionType.WholeTile;
import PaooGame.tiles.TileType;

public class NormalBoxMiddle extends WholeTile {
    public NormalBoxMiddle() {
        super(Assets.normalBox[1], TileType.NormalBoxMiddle);
    }
    public boolean IsSolid()
    {
        return true;
    }
}
