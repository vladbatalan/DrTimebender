package PaooGame.tiles.MapTiles;

import PaooGame.graphics.Assets;
import PaooGame.tiles.TileCollisionType.WholeTile;
import PaooGame.tiles.TileType;

public class BlackBoxLeft extends WholeTile {
    public BlackBoxLeft() {
        super(Assets.blackBoxes[0], TileType.BlackBoxLeft);
    }

    public boolean IsSolid()
    {
        return true;
    }
}
