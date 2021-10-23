package PaooGame.tiles.MapTiles;

import PaooGame.graphics.Assets;
import PaooGame.tiles.TileCollisionType.OnlyTopTile;
import PaooGame.tiles.TileType;

public class TunnelTop extends OnlyTopTile {
    public TunnelTop() {
        super(Assets.tunnelTile[1], TileType.TunnelTop);
    }
    public boolean IsSolid()
    {
        return true;
    }
}
