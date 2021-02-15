package PaooGame.Tiles;

import PaooGame.Physics.PVector;
import PaooGame.Tiles.MapTiles.NoTile;
import PaooGame.Tiles.TileCollisionType.BottomHalfTile;
import PaooGame.Tiles.TileCollisionType.TopHalfTile;

import java.awt.*;
import java.awt.image.BufferedImage;

/*! \class public class Tile
    \brief Retine toate dalele intr-un vector si ofera posibilitatea regasirii dupa un id.
 */
public class Tile
{
    public static final int TILE_WIDTH  = 30;                       /*!< Latimea unei dale.*/
    public static final int TILE_HEIGHT = 30;                       /*!< Inaltimea unei dale.*/
    protected Rectangle collisionBox = new Rectangle(0,0, TILE_WIDTH, TILE_HEIGHT);

    protected BufferedImage img;                                    /*!< Imaginea aferenta tipului de dala.*/
    protected TileType tileType;                                        /*!< Id-ul unic aferent tipului de dala.*/

    protected Color backcolor = new Color(0,0,0,0);

    public Tile(BufferedImage image, TileType tileType)
    {
        img = image;
        this.tileType = tileType;
    }

    public void Update()
    {

    }

    public void Draw(Graphics g, int x, int y)
    {
        /// Desenare dala
        g.setColor(backcolor);
        g.fillRect(x, y, TILE_WIDTH, TILE_HEIGHT);
        g.drawImage(img, x, y, TILE_WIDTH, TILE_HEIGHT, null);

        /*########## Green collision rect draw ############
        if(!(this instanceof NoTile)) {
            g.setColor(Color.green);
            g.drawRect((int) (x + collisionBox.getX()), (int) (y + collisionBox.getY()), collisionBox.width, collisionBox.height);
        }
         */
    }

    /*! \fn public boolean IsSolid()
        \brief Returneaza proprietatea de dala solida (supusa coliziunilor) sau nu.
     */
    public boolean IsSolid()
    {
        return false;
    }
    public boolean IsDeadly() {return false; }

    /*! \fn public int GetId()
        \brief Returneaza id-ul dalei.
     */
    public TileType GetId()
    {
        return tileType;
    }
    public String toString(){
        return tileType.getValue() + "";
    }
    public boolean onCollision(float relativeX, float relativeY){
       // System.out.println("\t" + tileType.toString());
        //System.out.print("\t" + new PVector(relativeX, relativeY).toString());
        //System.out.println("\t" + this.collisionBox.toString());
        if(collisionBox.getX() <= relativeX && relativeX <= collisionBox.getX() + collisionBox.getWidth())
            if(collisionBox.getY() <= relativeY && relativeY <= collisionBox.getY() + collisionBox.getHeight()) {
                //System.out.println("\ttrue");
                return true;
            }
       // System.out.println("\tfalse");
        return false;
    }

    public void setBackcolor(Color backcolor) {
        this.backcolor = backcolor;
    }
}
