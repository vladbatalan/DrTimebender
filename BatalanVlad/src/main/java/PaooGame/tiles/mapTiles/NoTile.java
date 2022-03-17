package PaooGame.tiles.mapTiles;

import PaooGame.graphics.Assets;
import PaooGame.tiles.tileCollisionType.WholeTile;
import PaooGame.tiles.TileType;

public class NoTile extends WholeTile {
    public NoTile() {
        super(Assets.noTile, TileType.NoTile);
    }
}
