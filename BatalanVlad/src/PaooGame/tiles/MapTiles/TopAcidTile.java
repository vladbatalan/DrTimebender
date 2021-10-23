package PaooGame.tiles.MapTiles;

import PaooGame.graphics.Assets;
import PaooGame.tiles.TileCollisionType.BottomHalfTile;
import PaooGame.tiles.TileType;

public class TopAcidTile extends BottomHalfTile {
    public TopAcidTile() {
        super(Assets.acid[0], TileType.TopAcidTile);
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
