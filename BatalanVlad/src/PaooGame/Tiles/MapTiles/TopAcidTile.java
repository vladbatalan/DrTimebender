package PaooGame.Tiles.MapTiles;

import PaooGame.Graphics.Assets;
import PaooGame.Tiles.Tile;
import PaooGame.Tiles.TileCollisionType.BottomHalfTile;
import PaooGame.Tiles.TileType;

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
