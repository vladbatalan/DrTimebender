package PaooGame.Tiles.MapTiles;

import PaooGame.Graphics.Assets;
import PaooGame.Tiles.Tile;
import PaooGame.Tiles.TileCollisionType.BottomHalfTile;
import PaooGame.Tiles.TileCollisionType.OnlyBottomTile;
import PaooGame.Tiles.TileType;

public class TunnelBottom extends OnlyBottomTile {
    public TunnelBottom() {
        super(Assets.tunnelTile[0], TileType.TunnelBottom);
    }
    public boolean IsSolid()
    {
        return true;
    }
}
