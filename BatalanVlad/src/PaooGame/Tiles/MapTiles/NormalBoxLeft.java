package PaooGame.Tiles.MapTiles;

import PaooGame.Graphics.Assets;
import PaooGame.Tiles.Tile;
import PaooGame.Tiles.TileCollisionType.WholeTile;
import PaooGame.Tiles.TileType;

public class NormalBoxLeft extends WholeTile {
    public NormalBoxLeft() {
        super(Assets.normalBox[0], TileType.NormalBoxLeft);
    }
    public boolean IsSolid()
    {
        return true;
    }
}
