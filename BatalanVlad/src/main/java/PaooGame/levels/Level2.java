package PaooGame.levels;

import PaooGame.gameObjects.stillObjects.*;
import PaooGame.physics.PointVector;
import PaooGame.tiles.Map;
import PaooGame.tiles.Tile;

import java.awt.*;

public class Level2 extends Level {
    @Override
    public void InitLevel() {
        // set the next level
        nextLevel = new Level3();

        // ###################### Here we display first the specifics of the Level ############################
        // The name of the level
        levelName = "Level 2: Platform time!";

        // The current Map
        currentMap = new Map("res/maps/map2.txt");
        currentMap.setBackground("/backgrounds/background1.jpg");

        // Create the existing objects except the Player that is loaded at the start of a level Phase
        startTimeMachine = new TimeMachine(new PointVector(Tile.TILE_WIDTH * 3, Tile.TILE_HEIGHT * 17 - 100));
        gameObjective = new Objective(new PointVector(Tile.TILE_WIDTH * 51, Tile.TILE_HEIGHT * 20 - 80), false);

        // Balace
        ScalePan scalePan1 = new ScalePan(new PointVector(Tile.TILE_WIDTH * 15 - Tile.TILE_WIDTH/2, Tile.TILE_HEIGHT * 20 - 20), Tile.TILE_HEIGHT * 9, 31);
        ScalePan scalePan2 = new ScalePan(new PointVector(Tile.TILE_WIDTH * 9 - Tile.TILE_WIDTH/2, Tile.TILE_HEIGHT * 20 - 20), 30, 20);
        TwoPanScale twoPanScale = new TwoPanScale(scalePan1, scalePan2, new Color(0x0056D6));
        twoPanScale.setMoveCompleteTime(120);


        // adding moving platform
        PointVector startPoint1 = new PointVector(Tile.TILE_WIDTH * 17 - Tile.TILE_WIDTH/2, Tile.TILE_HEIGHT * 11 - 20);
        PointVector endPoint1 = new PointVector(Tile.TILE_WIDTH * 24 - Tile.TILE_WIDTH/2, Tile.TILE_HEIGHT * 11 - 20 );
        MovingPlatform platform1 = new MovingPlatform(startPoint1, endPoint1, new Color(0xD61C23));
        platform1.setTimeToFinish(120);

        // adding moving platform
        PointVector startPoint2 = new PointVector(Tile.TILE_WIDTH * 17, Tile.TILE_HEIGHT * 21 - 20);
        PointVector endPoint2 = new PointVector(Tile.TILE_WIDTH * 30 - Tile.TILE_WIDTH/2, Tile.TILE_HEIGHT * 21 - 20 );
        MovingPlatformSwitchable platform2 = new MovingPlatformSwitchable(startPoint2, endPoint2, new Color(0xD66A23));
        platform2.setTimeToFinish(180);

        // adding moving platform
        PointVector startPoint3 = new PointVector(Tile.TILE_WIDTH * 29, Tile.TILE_HEIGHT * 12 - 20);
        PointVector endPoint3 = new PointVector(Tile.TILE_WIDTH * 48 - Tile.TILE_WIDTH/2, Tile.TILE_HEIGHT * 6 - 20 );
        MovingPlatformSwitchable platform3 = new MovingPlatformSwitchable(startPoint3, endPoint3, new Color(0x79D632));
        platform3.setTimeToFinish(200);

        // adding moving platform
        PointVector startPoint4 = new PointVector(Tile.TILE_WIDTH * 34, Tile.TILE_HEIGHT * 21 - 20);
        PointVector endPoint4 = new PointVector(Tile.TILE_WIDTH * 47 - Tile.TILE_WIDTH/2, Tile.TILE_HEIGHT * 21 - 20 );
        MovingPlatformSwitchable platform4 = new MovingPlatformSwitchable(startPoint4, endPoint4, new Color(0x00D679));
        platform1.setTimeToFinish(180);

        // the timed gates
        TimedGate timeGate1 = new TimedGate(new PointVector(Tile.TILE_WIDTH * 49 + 6, Tile.TILE_HEIGHT * 20 - 4),3*Tile.TILE_HEIGHT - 4, true, new Color(0xDC5040));

        //Lever
        Lever myLever1 = new Lever(new PointVector(Tile.TILE_WIDTH * 27, Tile.TILE_HEIGHT * 11 - 60));
        myLever1.addAffectedObject(platform2);

        //Lever
        Lever myLever2 = new Lever(new PointVector(Tile.TILE_WIDTH * 32, Tile.TILE_HEIGHT * 20 - 60));
        myLever2.addAffectedObject(platform3);

        //Lever
        Lever myLever3 = new Lever(new PointVector(Tile.TILE_WIDTH * 51, Tile.TILE_HEIGHT * 5 - 60));
        myLever3.addAffectedObject(platform4);
        myLever3.addAffectedObject(gameObjective);
        myLever3.addAffectedObject(timeGate1);

        // Adding Objects to Handler
        handler.addStillObject(startTimeMachine);
        handler.addStillObject(gameObjective);
        handler.addStillObject(twoPanScale);
        handler.addStillObject(platform1);
        handler.addStillObject(platform2);
        handler.addStillObject(platform3);
        handler.addStillObject(platform4);
        handler.addStillObject(myLever1);
        handler.addStillObject(myLever2);
        handler.addStillObject(myLever3);
        handler.addStillObject(timeGate1);


        // ##################### Here we make the standard Initialisation of the level ############################
        InitGenericLevel();

        resetLevel();
    }
    @Override
    public int getLevelCode() {
        return 2;
    }

}
