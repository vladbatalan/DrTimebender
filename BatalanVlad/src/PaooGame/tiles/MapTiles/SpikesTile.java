package PaooGame.tiles.MapTiles;

import PaooGame.graphics.Assets;
import PaooGame.tiles.TileCollisionType.BottomHalfTile;
import PaooGame.tiles.TileType;

public class SpikesTile extends BottomHalfTile {
    public SpikesTile() {
        super(Assets.spikes, TileType.SpikesTile);
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
