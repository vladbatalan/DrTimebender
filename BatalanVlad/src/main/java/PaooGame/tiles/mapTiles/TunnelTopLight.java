package PaooGame.tiles.mapTiles;

import PaooGame.graphics.Assets;
import PaooGame.tiles.tileCollisionType.OnlyTopTile;
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
