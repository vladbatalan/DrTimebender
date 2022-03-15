package PaooGame.tiles.mapTiles;

import PaooGame.graphics.Assets;
import PaooGame.tiles.tileCollisionType.WholeTile;
import PaooGame.tiles.TileType;

import java.awt.*;

public class NoTileColor extends WholeTile {
    public NoTileColor() {
        super(Assets.noTile, TileType.NoTileColor);
        backcolor = new Color(30, 31, 41);
    }
}
