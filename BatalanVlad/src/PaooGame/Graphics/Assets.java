package PaooGame.Graphics;

import java.awt.image.BufferedImage;

/*! \class public class Assets
    \brief Clasa incarca fiecare element grafic necesar jocului.

    Game assets include tot ce este folosit intr-un joc: imagini, sunete, harti etc.
 */
public class Assets
{

    public static BufferedImage[] platformBox;
    public static BufferedImage[] acid;
    public static BufferedImage[] blackBoxes;
    public static BufferedImage spikes;
    public static BufferedImage[] normalBox;
    public static BufferedImage[] wholePlatformBox;
    public static BufferedImage[] halfNormalBox;
    public static BufferedImage noTile;
    public static BufferedImage[] tunnelTile;

    /*! \fn public static void Init()
        \brief Functia initializaza referintele catre elementele grafice utilizate.

        Aceasta functie poate fi rescrisa astfel incat elementele grafice incarcate/utilizate
        sa fie parametrizate. Din acest motiv referintele nu sunt finale.
     */
    public static void Init()
    {
        //Pentru jocul meu
        SpriteSheet mapSheet = new SpriteSheet(ImageLoader.LoadImage("/textures/Tiles/MapTiles.png"));
        platformBox = new BufferedImage[4];
        platformBox[0] = mapSheet.crop(1, 3, SpriteSheet.tileWidth, SpriteSheet.tileHeight);
        platformBox[1] = mapSheet.crop(2, 3, SpriteSheet.tileWidth, SpriteSheet.tileHeight);
        platformBox[2] = mapSheet.crop(3, 3, SpriteSheet.tileWidth, SpriteSheet.tileHeight);
        platformBox[3] = mapSheet.crop(4, 3, SpriteSheet.tileWidth, SpriteSheet.tileHeight);

        acid = new BufferedImage[2];
        acid[0] = mapSheet.crop(0,0, SpriteSheet.tileWidth, SpriteSheet.tileHeight);
        acid[1] = mapSheet.crop(1,0, SpriteSheet.tileWidth, SpriteSheet.tileHeight);

        blackBoxes = new BufferedImage[4];
        blackBoxes[0] = mapSheet.crop(4, 0, SpriteSheet.tileWidth, SpriteSheet.tileHeight);
        blackBoxes[1] = mapSheet.crop(1, 1, SpriteSheet.tileWidth, SpriteSheet.tileHeight);
        blackBoxes[2] = mapSheet.crop(2, 1, SpriteSheet.tileWidth, SpriteSheet.tileHeight);
        blackBoxes[3] = mapSheet.crop(3, 1, SpriteSheet.tileWidth, SpriteSheet.tileHeight);

        normalBox = new BufferedImage[3];
        normalBox[0] = mapSheet.crop(2,4, SpriteSheet.tileWidth, SpriteSheet.tileHeight);
        normalBox[1] = mapSheet.crop(3,4, SpriteSheet.tileWidth, SpriteSheet.tileHeight);
        normalBox[2] = mapSheet.crop(4,4, SpriteSheet.tileWidth, SpriteSheet.tileHeight);

        spikes = mapSheet.crop(2,2, SpriteSheet.tileWidth, SpriteSheet.tileHeight);

        wholePlatformBox = new BufferedImage[5];
        wholePlatformBox[0] = mapSheet.crop(3,2, SpriteSheet.tileWidth, SpriteSheet.tileHeight);
        wholePlatformBox[1] = mapSheet.crop(0,4, SpriteSheet.tileWidth, SpriteSheet.tileHeight);
        wholePlatformBox[2] = mapSheet.crop(1,4, SpriteSheet.tileWidth, SpriteSheet.tileHeight);
        wholePlatformBox[3] = mapSheet.crop(0,5, SpriteSheet.tileWidth, SpriteSheet.tileHeight);
        wholePlatformBox[4] = mapSheet.crop(1,5, SpriteSheet.tileWidth, SpriteSheet.tileHeight);

        halfNormalBox = new BufferedImage[3];
        halfNormalBox[0] = mapSheet.crop(2,5, SpriteSheet.tileWidth, SpriteSheet.tileHeight);
        halfNormalBox[1] = mapSheet.crop(4,2, SpriteSheet.tileWidth, SpriteSheet.tileHeight);
        halfNormalBox[2] = mapSheet.crop(0,3, SpriteSheet.tileWidth, SpriteSheet.tileHeight);

        noTile = mapSheet.crop(3, 5, SpriteSheet.tileWidth, SpriteSheet.tileHeight);

        tunnelTile = new BufferedImage[3];
        tunnelTile[0] = mapSheet.crop(3,1, SpriteSheet.tileWidth, SpriteSheet.tileHeight);
        tunnelTile[1] = mapSheet.crop(3,0, SpriteSheet.tileWidth, SpriteSheet.tileHeight);
        tunnelTile[2] = mapSheet.crop(2,0, SpriteSheet.tileWidth, SpriteSheet.tileHeight);
    }
}
