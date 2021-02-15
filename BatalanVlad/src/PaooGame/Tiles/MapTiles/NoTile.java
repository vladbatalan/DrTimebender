package PaooGame.Tiles.MapTiles;

import PaooGame.Graphics.Assets;
import PaooGame.Tiles.Tile;
import PaooGame.Tiles.TileCollisionType.WholeTile;
import PaooGame.Tiles.TileType;

public class NoTile extends WholeTile {
    public NoTile() {
        super(Assets.noTile, TileType.NoTile);
    }
}
