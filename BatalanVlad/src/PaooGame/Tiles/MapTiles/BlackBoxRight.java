package PaooGame.Tiles.MapTiles;

import PaooGame.Graphics.Assets;
import PaooGame.Tiles.Tile;
import PaooGame.Tiles.TileCollisionType.WholeTile;
import PaooGame.Tiles.TileType;

public class BlackBoxRight extends WholeTile {
    public BlackBoxRight() {
        super(Assets.blackBoxes[1], TileType.BlackBoxRight);
    }

    public boolean IsSolid()
    {
        return true;
    }
}
