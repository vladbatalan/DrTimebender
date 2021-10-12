package PaooGame.Tiles;

import PaooGame.Game;
import PaooGame.GameWindow.Camera.GameCamera;
import PaooGame.Graphics.ImageLoader;
import PaooGame.Physics.Body;
import PaooGame.Physics.PVector;
import PaooGame.Tiles.Factory.TileFactory;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class Map {
    private int MIN_WIDTH = 28;
    private int MIN_HEIGHT = 13;

    private int map_width;
    private int map_height;

    private LinkedList<LinkedList<Tile>> tileMatrix = new LinkedList<>();
    private BufferedImage background;
    private GameCamera camera;

    public Map(String fileName){

        try {
            File myObj = new File(fileName);
            Scanner myReader = new Scanner(myObj);

            //read the size of the map Height and Width
            String[] first_line =  myReader.nextLine().split(" ");
            int height = Integer.parseInt(first_line[0]);
            int width = Integer.parseInt(first_line[1]);

            map_width = width;
            map_height = height;

            //execut crearea hartii
            for( int index = 0; index < height; index ++) {
               if (myReader.hasNextLine()) {

                    //creez o noua lista care contine date despre harta mea
                    LinkedList<Tile> temp = new LinkedList<>();

                    //citesc prima linie
                    String data = myReader.nextLine();
                    String[] numbers = data.split(" ");

                    for (String s : numbers) {
                        if (!s.isEmpty()) {
                            if (temp.size() < width) //limitez la numarul de elemente specificate in antet
                                temp.add(TileFactory.createTile(Integer.parseInt(s)));
                            else
                                throw new Exception("Wrong map format: width specified does not match with the given number of tiles on row "+index+"!");
                        }
                    }
                    if(temp.size() < width)
                        throw new Exception("Wrong map format: width specified does not match with the given number of tiles on row "+index+"!");
                    tileMatrix.add(temp);
               }
               else{
                   throw new Exception("Wrong map format: height specified does not match with the given number of tiles!");
               }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Map invalid format exception occured.");
            e.printStackTrace();
        }
    }

    public void Update(){

    }

    private PVector[] testP;

    public void Draw(Graphics g){
        if(background != null) {
            int backgroundX = 0;
            int backgroundY = 0;
            if (camera != null) {
                backgroundX = (int) camera.getCameraCoordonates().getX();
                backgroundY = (int) camera.getCameraCoordonates().getY();
            }
            g.drawImage(background, backgroundX, backgroundY, Game.GAME_WINDOW_WIDTH, Game.GAME_WINDOW_HEIGHT, null);
        }

        Rectangle cameraSquare = new Rectangle((int)camera.getCameraCoordonates().getX(), (int)camera.getCameraCoordonates().getY(), Game.GAME_WINDOW_WIDTH, Game.GAME_WINDOW_HEIGHT);

        for(int x = 0; x < tileMatrix.size(); x ++){
            for(int y = 0; y < tileMatrix.get(x).size(); y ++){
                if( (y+1) * Tile.TILE_WIDTH >= cameraSquare.getX() && y*Tile.TILE_WIDTH <= cameraSquare.getX() + cameraSquare.getWidth() &&
                        (x+1) * Tile.TILE_HEIGHT >= cameraSquare.getY() && x*Tile.TILE_HEIGHT <= cameraSquare.getY() + cameraSquare.getHeight())
                    tileMatrix.get(x).get(y).Draw(g, y * Tile.TILE_HEIGHT, x * Tile.TILE_WIDTH);
            }
        }
        /*
        if(testP!=null){
            for(PVector pv:testP)
            {
                g.setColor(Color.green);
                g.fillRect((int)pv.getX()-1, (int)pv.getY()-1,2,2);
            }
        }
         */
    }

    public void mapExtend(){
        if(map_height < MIN_HEIGHT){
            LinkedList<Tile> lastRow = tileMatrix.getLast();
            for(int heightIndex = tileMatrix.size(); heightIndex < MIN_HEIGHT; heightIndex++) {
                tileMatrix.add((LinkedList<Tile>)lastRow.clone());
            }
            map_height = MIN_HEIGHT;
        }

        if(map_width < MIN_WIDTH) {
            for (int heightIndex = 0; heightIndex < map_height; heightIndex++) {
                Tile last = tileMatrix.get(heightIndex).getLast();
                for (int index = map_width; index < MIN_WIDTH; index++) {
                    tileMatrix.get(heightIndex).add(TileFactory.createTile(last.GetId()));
                }
            }
            map_width = MIN_WIDTH;
        }
    }

    public boolean[] checkCollision(float x, float y, int width, int height){
        //0 - top collision
        //1 - right collision
        //2 - bottom collision
        //3 - left collision
        //4 - deadly collision
        boolean[] collision = new boolean[5];

        // points where we will check the collisions with the tiles on the map
        PVector[] checkingPoints = Body.getSideCollisionPoints(new Rectangle((int)x, (int)y, width, height));
        int totalPoints = checkingPoints.length;
        int nrTestPoints = totalPoints/4;

        Tile[] checkingTiles = new Tile[totalPoints];
        for(int index = 0; index < checkingPoints.length; index ++){
           PVector coords = getMatrixIndexes(checkingPoints[index].getX(), checkingPoints[index].getY());
            checkingTiles[index] = getTileByIndex((int)coords.getX(), (int)coords.getY());
        }

        //System.out.println("Collision test: " + new PVector(x, y).toString());
        for(int index = 0; index < checkingTiles.length; index ++){
            //este un pas in plus pe care l pot integra sigur nu am nev de relative poz mai incolo?
            PVector relativePosition = getPointRelativeToTile(checkingPoints[index]);
            if(checkingTiles[index].onCollision(relativePosition.getX(), relativePosition.getY())) {
                if (checkingTiles[index].IsDeadly())
                    collision[4] = true;
                if (checkingTiles[index].IsSolid())
                    collision[index / nrTestPoints] = true;
            }
        }
        return collision;
    }

    // gets a location on map
    // returns the indexes of the tile located in the matrix that contains x and y
    public PVector getMatrixIndexes(float x, float y){
        return new PVector((int)(x / Tile.TILE_WIDTH), (int)(y / Tile.TILE_HEIGHT));
    }

    public Tile getTileByIndex(int x, int y){
        return tileMatrix.get(y).get(x);
    }

    public PVector getMaxBounds(){
        PVector bounds = new PVector();
        bounds.setX(tileMatrix.get(0).size() * Tile.TILE_HEIGHT);
        bounds.setY(tileMatrix.size() * Tile.TILE_WIDTH);
        return bounds;
    }

    public PVector getPointRelativeToTile(PVector point){
        float x, y;
        PVector indexes = getMatrixIndexes(point.getX(), point.getY());
        x = point.getX() - indexes.getX() * Tile.TILE_WIDTH;
        y = point.getY() - indexes.getY() * Tile.TILE_HEIGHT;
        return new PVector(x, y);
    }

    public void setCamera(GameCamera camera) {
        this.camera = camera;
    }
    public void setBackground(BufferedImage background){
        this.background = background;
    }
    public void setBackground(String imagePath){
        this.background = ImageLoader.LoadImage(imagePath);
    }
}
