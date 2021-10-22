package PaooGame.tiles;

import PaooGame.Game;
import PaooGame.gameWindow.camera.GameCamera;
import PaooGame.graphics.ImageLoader;
import PaooGame.physics.PointVector;
import PaooGame.tiles.Factory.TileFactory;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import static PaooGame.physics.utils.CollisionUtils.getSideCollisionPoints;

/**
 * Class responsible for handling the creation of the map and handling the collisions with the tiles
 */

public class Map {

    private final int MIN_WIDTH = 28;
    private final int MIN_HEIGHT = 13;
    private final ArrayList<ArrayList<Tile>> tileMatrix = new ArrayList<>();

    private int mapWidth;
    private int mapHeight;

    private BufferedImage backgroundImage;
    private GameCamera camera;

    public Map(String fileName) {

        try {

            File myObj = new File(fileName);
            Scanner myReader = new Scanner(myObj);

            String[] firstLine = myReader.nextLine().split(" ");
            mapHeight = Integer.parseInt(firstLine[0]);
            mapWidth = Integer.parseInt(firstLine[1]);

            for (int i = 0; i < mapHeight; i++) {

                if (myReader.hasNextLine()) {

                    //creez o noua lista care contine date despre harta mea
                    ArrayList<Tile> temp = new ArrayList<>();

                    //citesc prima linie
                    String data = myReader.nextLine();
                    String[] numbers = data.split(" ");

                    for (String s : numbers) {
                        if (!s.isEmpty()) {
                            if (temp.size() < mapWidth) //limitez la numarul de elemente specificate in antet
                                temp.add(TileFactory.createTile(Integer.parseInt(s)));
                            else
                                throw new Exception("Wrong map format: width specified does not match with the given number of tiles on row " + i + "!");
                        }
                    }
                    if (temp.size() < mapWidth)
                        throw new Exception("Wrong map format: width specified does not match with the given number of tiles on row " + i + "!");
                    tileMatrix.add(temp);
                } else {
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

    public void Update() {

    }

    public void Draw(Graphics g) {
        if (backgroundImage != null) {
            int backgroundX = 0;
            int backgroundY = 0;
            if (camera != null) {
                backgroundX = (int) camera.getCameraCoordonates().getX();
                backgroundY = (int) camera.getCameraCoordonates().getY();
            }
            g.drawImage(backgroundImage, backgroundX, backgroundY, Game.GAME_WINDOW_WIDTH, Game.GAME_WINDOW_HEIGHT, null);
        }

        Rectangle cameraSquare = new Rectangle((int) camera.getCameraCoordonates().getX(), (int) camera.getCameraCoordonates().getY(), Game.GAME_WINDOW_WIDTH, Game.GAME_WINDOW_HEIGHT);

        for (int x = 0; x < tileMatrix.size(); x++) {
            for (int y = 0; y < tileMatrix.get(x).size(); y++) {
                if ((y + 1) * Tile.TILE_WIDTH >= cameraSquare.getX() && y * Tile.TILE_WIDTH <= cameraSquare.getX() + cameraSquare.getWidth() &&
                        (x + 1) * Tile.TILE_HEIGHT >= cameraSquare.getY() && x * Tile.TILE_HEIGHT <= cameraSquare.getY() + cameraSquare.getHeight())
                    tileMatrix.get(x).get(y).Draw(g, y * Tile.TILE_HEIGHT, x * Tile.TILE_WIDTH);
            }
        }
        /*
        if(testP!=null){
            for(PointVector pv:testP)
            {
                g.setColor(Color.green);
                g.fillRect((int)pv.getX()-1, (int)pv.getY()-1,2,2);
            }
        }
         */
    }

    public void mapExtend() {
        if (mapHeight < MIN_HEIGHT) {
            ArrayList<Tile> lastRow = tileMatrix.get(tileMatrix.size() - 1);
            for (int heightIndex = tileMatrix.size(); heightIndex < MIN_HEIGHT; heightIndex++) {
                tileMatrix.add((ArrayList<Tile>) lastRow.clone());
            }
            mapHeight = MIN_HEIGHT;
        }

        if (mapWidth < MIN_WIDTH) {
            for (int heightIndex = 0; heightIndex < mapHeight; heightIndex++) {
                Tile last = tileMatrix.get(heightIndex).get(tileMatrix.get(heightIndex).size() - 1);
                for (int index = mapWidth; index < MIN_WIDTH; index++) {
                    tileMatrix.get(heightIndex).add(TileFactory.createTile(last.GetId()));
                }
            }
            mapWidth = MIN_WIDTH;
        }
    }

    public boolean[] checkCollision(Rectangle rect) {
        //0 - top collision
        //1 - right collision
        //2 - bottom collision
        //3 - left collision
        //4 - deadly collision
        boolean[] collision = new boolean[5];

        // points where we will check the collisions with the tiles on the map
        PointVector[] checkingPoints = getSideCollisionPoints(rect);

        int totalPoints = checkingPoints.length;
        int nrTestPoints = totalPoints / 4;

        Tile[] checkingTiles = new Tile[totalPoints];
        for (int index = 0; index < checkingPoints.length; index++) {
            PointVector coords = getMatrixIndexes(checkingPoints[index].getX(), checkingPoints[index].getY());
            checkingTiles[index] = getTileByIndex((int) coords.getX(), (int) coords.getY());
        }

        //System.out.println("Collision test: " + new PointVector(x, y).toString());
        for (int index = 0; index < checkingTiles.length; index++) {
            //este un pas in plus pe care l pot integra sigur nu am nev de relative poz mai incolo?
            PointVector relativePosition = getPointRelativeToTile(checkingPoints[index]);
            if (checkingTiles[index].onCollision(relativePosition.getX(), relativePosition.getY())) {
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
    public PointVector getMatrixIndexes(float x, float y) {
        return new PointVector((int) (x / Tile.TILE_WIDTH), (int) (y / Tile.TILE_HEIGHT));
    }

    public Tile getTileByIndex(int x, int y) {
        return tileMatrix.get(y).get(x);
    }

    public PointVector getMaxBounds() {
        PointVector bounds = new PointVector();
        bounds.setX(tileMatrix.get(0).size() * Tile.TILE_HEIGHT);
        bounds.setY(tileMatrix.size() * Tile.TILE_WIDTH);
        return bounds;
    }

    public PointVector getPointRelativeToTile(PointVector point) {
        float x, y;
        PointVector indexes = getMatrixIndexes(point.getX(), point.getY());
        x = point.getX() - indexes.getX() * Tile.TILE_WIDTH;
        y = point.getY() - indexes.getY() * Tile.TILE_HEIGHT;
        return new PointVector(x, y);
    }

    public void setCamera(GameCamera camera) {
        this.camera = camera;
    }

    public void setBackgroundImage(BufferedImage backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    public void setBackground(String imagePath) {
        this.backgroundImage = ImageLoader.LoadImage(imagePath);
    }
}
