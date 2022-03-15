package PaooGame.tiles.mapTiles;

import PaooGame.graphics.Assets;
import PaooGame.tiles.tileCollisionType.OnlyBottomTile;
import PaooGame.tiles.TileType;

public class TunnelBottom extends OnlyBottomTile {
    public TunnelBottom() {
        super(Assets.tunnelTile[0], TileType.TunnelBottom);
    }
    public boolean IsSolid()
    {
        return true;
    }
}
