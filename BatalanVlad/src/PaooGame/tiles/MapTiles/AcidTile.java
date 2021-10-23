package PaooGame.tiles.MapTiles;

import PaooGame.graphics.Assets;
import PaooGame.tiles.TileCollisionType.WholeTile;
import PaooGame.tiles.TileType;

public class AcidTile extends WholeTile {
    public AcidTile() {
        super(Assets.acid[1], TileType.AcidTile);
    }
    public boolean IsSolid()
    {
        return false;
    }
    public boolean IsDeadly()
    {
        return true;
    }
}
