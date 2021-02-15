package PaooGame.Tiles.MapTiles;

import PaooGame.Graphics.Assets;
import PaooGame.Tiles.Tile;
import PaooGame.Tiles.TileCollisionType.OnlyTopTile;
import PaooGame.Tiles.TileCollisionType.TopHalfTile;
import PaooGame.Tiles.TileType;

public class TunnelTop extends OnlyTopTile {
    public TunnelTop() {
        super(Assets.tunnelTile[1], TileType.TunnelTop);
    }
    public boolean IsSolid()
    {
        return true;
    }
}
