package PaooGame.tiles.MapTiles;

import PaooGame.graphics.Assets;
import PaooGame.tiles.TileCollisionType.WholeTile;
import PaooGame.tiles.TileType;

public class BlackBoxRight extends WholeTile {
    public BlackBoxRight() {
        super(Assets.blackBoxes[1], TileType.BlackBoxRight);
    }

    public boolean IsSolid()
    {
        return true;
    }
}
