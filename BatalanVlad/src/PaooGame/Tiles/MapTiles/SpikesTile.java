package PaooGame.Tiles.MapTiles;

import PaooGame.Graphics.Assets;
import PaooGame.Tiles.Tile;
import PaooGame.Tiles.TileCollisionType.BottomHalfTile;
import PaooGame.Tiles.TileType;

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
