package PaooGame.tiles.mapTiles;

import PaooGame.graphics.Assets;
import PaooGame.tiles.tileCollisionType.OnlyTopTile;
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
