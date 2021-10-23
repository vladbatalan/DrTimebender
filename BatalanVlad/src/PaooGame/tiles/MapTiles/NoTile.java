package PaooGame.tiles.MapTiles;

import PaooGame.graphics.Assets;
import PaooGame.tiles.TileCollisionType.WholeTile;
import PaooGame.tiles.TileType;

public class NoTile extends WholeTile {
    public NoTile() {
        super(Assets.noTile, TileType.NoTile);
    }
}
