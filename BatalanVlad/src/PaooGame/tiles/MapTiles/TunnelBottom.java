package PaooGame.tiles.MapTiles;

import PaooGame.graphics.Assets;
import PaooGame.tiles.TileCollisionType.OnlyBottomTile;
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
