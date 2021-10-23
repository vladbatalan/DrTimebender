package PaooGame.tiles.MapTiles;

import PaooGame.graphics.Assets;
import PaooGame.tiles.TileCollisionType.OnlyTopTile;
import PaooGame.tiles.TileType;

public class TunnelTopLight extends OnlyTopTile {
    public TunnelTopLight() {
        super(Assets.tunnelTile[2], TileType.TunnelTopLight);
    }
    public boolean IsSolid()
    {
        return true;
    }
}
