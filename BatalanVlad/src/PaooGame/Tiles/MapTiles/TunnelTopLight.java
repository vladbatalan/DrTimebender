package PaooGame.Tiles.MapTiles;

import PaooGame.Graphics.Assets;
import PaooGame.Tiles.Tile;
import PaooGame.Tiles.TileCollisionType.OnlyBottomTile;
import PaooGame.Tiles.TileCollisionType.OnlyTopTile;
import PaooGame.Tiles.TileCollisionType.TopHalfTile;
import PaooGame.Tiles.TileType;

public class TunnelTopLight extends OnlyTopTile {
    public TunnelTopLight() {
        super(Assets.tunnelTile[2], TileType.TunnelTopLight);
    }
    public boolean IsSolid()
    {
        return true;
    }
}
