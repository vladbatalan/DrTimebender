package PaooGame.Tiles.MapTiles;

import PaooGame.Graphics.Assets;
import PaooGame.Tiles.TileCollisionType.WholeTile;
import PaooGame.Tiles.TileType;

import java.awt.*;

public class NoTileColor extends WholeTile {
    public NoTileColor() {
        super(Assets.noTile, TileType.NoTileColor);
        backcolor = new Color(30, 31, 41);
    }
}
