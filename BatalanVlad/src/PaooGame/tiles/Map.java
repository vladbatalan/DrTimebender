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

import static PaooGame.physics.enums.CollisionTypes.DEADLY;
import static PaooGame.physics.utils.CollisionUtils.getSideCollisionPoints;
import static PaooGame.tiles.Tile.TILE_HEIGHT;
import static PaooGame.tiles.Tile.TILE_WIDTH;

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

                            //limitez la numarul de elemente specificate in antet
                            if (temp.size() < mapWidth) {
                                temp.add(TileFactory.createTile(Integer.parseInt(s)));
                            } else {
                                throw new Exception("Wrong map format: width specified does not match with " +
                                        "the given number of tiles on row " + i + "!");
                            }
                        }
                    }
                    if (temp.size() < mapWidth) {
                        throw new Exception("Wrong map format: width specified does not match with the given number" +
                                " of tiles on row " + i + "!");
                    }
                    tileMatrix.add(temp);
                } else {
                    throw new Exception("Wrong map format: height specified does not match " +
                            "with the given number of tiles!");
                }
            }
            myReader.close();

            // extending the bounds of the map
            mapExtend();

        } catch (FileNotFoundException e) {

            System.out.println("An error occurred.");
            e.printStackTrace();
        } catch (Exception e) {

            System.out.println("Map invalid format exception occured.");
            e.printStackTrace();
        }
    }

    /**
     * Method responsible for updating the Map
     */
    public void Update() {
        // No updated needed for the map object
        // If map tiles can be changed in the future
    }

    /**
     * Method responsible for drawing the Map
     *
     * @param g The graphic context
     */
    public void Draw(Graphics g) {

        // The placement of the background considering the game camera position
        if (backgroundImage != null) {

            int backgroundX = 0;
            int backgroundY = 0;

            if (camera != null) {

                backgroundX = (int) camera.getCameraCoordinates().getX();
                backgroundY = (int) camera.getCameraCoordinates().getY();
            }

            g.drawImage(backgroundImage, backgroundX, backgroundY,
                    Game.GAME_WINDOW_WIDTH, Game.GAME_WINDOW_HEIGHT, null);
        }

        // Assume camera is not null?!
        Rectangle cameraSquare = new Rectangle(
                (int) camera.getCameraCoordinates().getX(),
                (int) camera.getCameraCoordinates().getY(),
                Game.GAME_WINDOW_WIDTH,
                Game.GAME_WINDOW_HEIGHT
        );

        /*int rowLowBound = max((int) cameraSquare.getY() / TILE_HEIGHT - 1, 0);
        int rowHighBound = min((int) (cameraSquare.getY() + cameraSquare.height) / TILE_HEIGHT, mapHeight);
        int columnLowBound = max((int) cameraSquare.getX() / TILE_WIDTH - 1, 0);
        int columnHighBound = min((int) (cameraSquare.getX() + cameraSquare.width) / TILE_WIDTH, mapWidth);

        for (int row = rowLowBound; row <= rowHighBound; row++) {
            for (int column = columnLowBound; column <= columnHighBound; column++) {
                tileMatrix.get(row).get(column).Draw(g, column * TILE_WIDTH, row * TILE_HEIGHT);
            }
        }*/

        // Not optimized enough
        // Draw only visible tiles
        for (int row = 0; row < mapHeight; row++) {
            for (int column = 0; column < mapWidth; column++) {

                int rightSide = (column + 1) * TILE_WIDTH;
                int leftSide = column * TILE_WIDTH;
                int bottomSide = (row + 1) * TILE_HEIGHT;
                int topSide = row * TILE_HEIGHT;

                if (rightSide >= cameraSquare.getX() && leftSide <= cameraSquare.getX() + cameraSquare.getWidth()) {
                    if (bottomSide >= cameraSquare.getY() && topSide <= cameraSquare.getY() + cameraSquare.getHeight()) {
                        getTileByIndex(column, row).Draw(g, column * TILE_WIDTH, row * TILE_HEIGHT);
                    }
                }
            }
        }

    }

    /**
     * Method responsible for extending the Map if the bounds are smaller than the minimum size.
     */
    private void mapExtend() {

        if (mapHeight < MIN_HEIGHT) {

            ArrayList<Tile> lastRow = tileMatrix.get(tileMatrix.size() - 1);

            for (int heightIndex = tileMatrix.size(); heightIndex < MIN_HEIGHT; heightIndex++) {
                tileMatrix.add((ArrayList<Tile>) lastRow.clone());
            }

            mapHeight = MIN_HEIGHT;
        }

        if (mapWidth < MIN_WIDTH) {

            for (int heightIndex = 0; heightIndex < mapHeight; heightIndex++) {

                Tile last = getTileByIndex(mapWidth - 1, heightIndex);
                for (int index = mapWidth; index < MIN_WIDTH; index++) {

                    Tile newTile = TileFactory.createTile(last.GetId());
                    tileMatrix.get(heightIndex).add(newTile);
                }
            }

            mapWidth = MIN_WIDTH;
        }
    }

    /**
     * Method responsible for checking the collision of a given rectangle with the solid tiles on the Map
     *
     * @param rect The coordinates of the object that interracts with the map
     * @return A list of booleans that denote the types of collision resulted
     */
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
        int totalSidePoints = totalPoints / 4;

        for (int i = 0; i < totalPoints; i++) {

            PointVector coords = getMatrixIndexes(checkingPoints[i].getX(), checkingPoints[i].getY());
            Tile currentTile = getTileByIndex((int) coords.getX(), (int) coords.getY());

            PointVector relativePosition = getPointRelativeToTile(checkingPoints[i]);
            int sideCode = i / totalSidePoints;

            if (currentTile.onCollision(relativePosition.getX(), relativePosition.getY())) {

                if (currentTile.IsDeadly()) {
                    collision[DEADLY.getValue()] = true;
                }
                if (currentTile.IsSolid()) {
                    collision[sideCode] = true;
                }
            }
        }
        return collision;
    }

    // gets a location on map
    // returns the indexes of the tile located in the matrix that contains x and y
    public PointVector getMatrixIndexes(float x, float y) {
        return new PointVector((int) (x / TILE_WIDTH), (int) (y / TILE_HEIGHT));
    }

    public Tile getTileByIndex(int column, int row) {
        return tileMatrix.get(row).get(column);
    }

    public PointVector getMaxBounds() {
        PointVector bounds = new PointVector();
        bounds.setX(tileMatrix.get(0).size() * TILE_HEIGHT);
        bounds.setY(tileMatrix.size() * TILE_WIDTH);
        return bounds;
    }

    public PointVector getPointRelativeToTile(PointVector point) {
        float x, y;
        PointVector indexes = getMatrixIndexes(point.getX(), point.getY());
        x = point.getX() - indexes.getX() * TILE_WIDTH;
        y = point.getY() - indexes.getY() * TILE_HEIGHT;
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
