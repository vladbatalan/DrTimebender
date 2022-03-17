package PaooGame.tiles.factory;

import PaooGame.tiles.mapTiles.*;
import PaooGame.tiles.Tile;
import PaooGame.tiles.TileType;

public class TileFactory {
    public static Tile createTile(TileType tileType){
        switch (tileType) {
            case NoTile:
                return new NoTile();
            case PlatformLeftTile:
                return new PlatformLeftTile();
            case PlatformRightTile:
                return new PlatformRightTile();
            case PlatformMiddleTile:
                return new PlatformMiddleTile();
            case PlatformLonelyTile:
                return new PlatformLonelyTile();
            case TopAcidTile:
                return new TopAcidTile();
            case AcidTile:
                return new AcidTile();
            case BlackBoxLeft:
                return new BlackBoxLeft();
            case BlackBoxRight:
                return new BlackBoxRight();
            case SpikesTile:
                return new SpikesTile();
            case NormalBoxLeft:
                return new NormalBoxLeft();
            case NormalBoxRight:
                return new NormalBoxRight();
            case NormalBoxMiddle:
                return new NormalBoxMiddle();
            case PlatformWholeLeft:
                return new PlatformWholeLeft();
            case PlatformWholeMiddle:
                return new PlatformWholeMiddle();
            case PlatformWholeRight:
                return new PlatformWholeRight();
            case PlatformWholeMarginLeft:
                return new PlatformWholeMarginLeft();
            case PlatformWholeMarginRight:
                return new PlatformWholeMarginRight();
            case HalfNormalBoxLeft:
                return new HalfNormalBoxLeft();
            case HalfNormalBoxMiddle:
                return new HalfNormalBoxMiddle();
            case HalfNormalBoxRight:
                return new HalfNormalBoxRight();
            case TunnelBottom:
                return new TunnelBottom();
            case TunnelTop:
                return new TunnelTop();
            case TunnelTopLight:
                return new TunnelTopLight();
            case NoTileColor:
                return new NoTileColor();
            default:
                return null;
        }
    }

    public static Tile createTile(int tileValue){
        for(TileType type : TileType.values()){
            if(type.getValue() == tileValue){
               return TileFactory.createTile(type);
            }
        }
        return null;
    }


}
