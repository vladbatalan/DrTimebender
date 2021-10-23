package PaooGame.tiles.MapTiles;

import PaooGame.graphics.Assets;
import PaooGame.tiles.TileCollisionType.WholeTile;
import PaooGame.tiles.TileType;

public class NormalBoxLeft extends WholeTile {
    public NormalBoxLeft() {
        super(Assets.normalBox[0], TileType.NormalBoxLeft);
    }
    public boolean IsSolid()
    {
        return true;
    }
}
