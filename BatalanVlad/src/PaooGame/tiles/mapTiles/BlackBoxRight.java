package PaooGame.tiles.mapTiles;

import PaooGame.graphics.Assets;
import PaooGame.tiles.tileCollisionType.WholeTile;
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
