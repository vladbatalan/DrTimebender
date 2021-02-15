package PaooGame.Tiles.MapTiles;

import PaooGame.Graphics.Assets;
import PaooGame.Tiles.Tile;
import PaooGame.Tiles.TileCollisionType.WholeTile;
import PaooGame.Tiles.TileType;

public class BlackBoxLeft extends WholeTile {
    public BlackBoxLeft() {
        super(Assets.blackBoxes[0], TileType.BlackBoxLeft);
    }

    public boolean IsSolid()
    {
        return true;
    }
}
