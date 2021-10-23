package PaooGame.tiles.MapTiles;

import PaooGame.graphics.Assets;
import PaooGame.tiles.TileCollisionType.WholeTile;
import PaooGame.tiles.TileType;

import java.awt.*;

public class NoTileColor extends WholeTile {
    public NoTileColor() {
        super(Assets.noTile, TileType.NoTileColor);
        backcolor = new Color(30, 31, 41);
    }
}
