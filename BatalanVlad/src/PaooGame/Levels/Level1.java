package PaooGame.Levels;

import PaooGame.GameObjects.StillObjects.*;
import PaooGame.Physics.PointVector;
import PaooGame.Tiles.Map;
import PaooGame.Tiles.Tile;

import java.awt.*;

public class Level1 extends Level {
    @Override
    public void InitLevel() {
        // set the next level
        nextLevel = new Level2();

        // ###################### Here we display first the specifics of the Level ############################
        // The name of the level
        levelName = "Level 1: Branching Security";

        // The current Map
        currentMap = new Map("res/maps/map1.txt");
        currentMap.setBackground("/backgrounds/plainBackground3.png");

        // Create the existing objects except the Player that is loaded at the start of a level Phase
        startTimeMachine = new TimeMachine(new PointVector(Tile.TILE_WIDTH * 4, Tile.TILE_HEIGHT * 18 - 100));
        gameObjective = new Objective(new PointVector(Tile.TILE_WIDTH * 46, Tile.TILE_HEIGHT * 18 - 80), false);

        // the timed gates
        TimedGate timeGate1 = new TimedGate(new PointVector(Tile.TILE_WIDTH * 22 + 6, Tile.TILE_HEIGHT * 18 - 4),4*Tile.TILE_HEIGHT - 4, true, new Color(0xDC5040));
        TimedGate timeGate2 = new TimedGate(new PointVector(Tile.TILE_WIDTH * 27 + 6, Tile.TILE_HEIGHT * 18 - 4),4*Tile.TILE_HEIGHT - 4, true, new Color(0xDC882E));
        TimedGate timeGate3 = new TimedGate(new PointVector(Tile.TILE_WIDTH * 33 + 6, Tile.TILE_HEIGHT * 18 - 4),4*Tile.TILE_HEIGHT - 4, true, new Color(0xDCD134));
        TimedGate timeGate4 = new TimedGate(new PointVector(Tile.TILE_WIDTH * 38 + 6, Tile.TILE_HEIGHT * 18 - 4),4*Tile.TILE_HEIGHT - 4, true, new Color(0x9CDC16));

        // a switch for opening and closing the gameObjective
        Lever left1Lever = new Lever(new PointVector(Tile.TILE_WIDTH * 8, Tile.TILE_HEIGHT * 5 - 60));
        Lever left2Lever = new Lever(new PointVector(Tile.TILE_WIDTH * 8, Tile.TILE_HEIGHT * 10 - 60));
        Lever right1Lever = new Lever(new PointVector(Tile.TILE_WIDTH * 17, Tile.TILE_HEIGHT * 5 - 60));
        Lever right2Lever = new Lever(new PointVector(Tile.TILE_WIDTH * 17, Tile.TILE_HEIGHT * 10 - 60));

        // the lever affects the objective state
        right2Lever.addAffectedObject(timeGate4);
        left1Lever.addAffectedObject(timeGate2);
        left1Lever.addAffectedObject(gameObjective);
        left2Lever.addAffectedObject(timeGate2, false);
        left2Lever.addAffectedObject(timeGate1);
        right1Lever.addAffectedObject(timeGate3);
        right1Lever.addAffectedObject(timeGate2, true);
        //objectiveLever.addAffectedObject(gameObjective);
        //objectiveLever.addAffectedObject(myGate, true);

        ScalePan scalePan1 = new ScalePan(new PointVector(Tile.TILE_WIDTH * 9 - Tile.TILE_WIDTH/2, Tile.TILE_HEIGHT * 18 - 20), 30, 25);
        ScalePan scalePan2 = new ScalePan(new PointVector(Tile.TILE_WIDTH * 13 - Tile.TILE_WIDTH/2, Tile.TILE_HEIGHT * 18 - 20), 350, 30);
        StillObject twoPanScale = new TwoPanScale(scalePan1, scalePan2, new Color(0x57C5D6));

        // Adding Objects to Handler
        handler.addStillObject(startTimeMachine);
        handler.addStillObject(gameObjective);
        handler.addStillObject(twoPanScale);
        handler.addStillObject(timeGate1);
        handler.addStillObject(timeGate2);
        handler.addStillObject(timeGate3);
        handler.addStillObject(timeGate4);
        handler.addStillObject(left1Lever);
        handler.addStillObject(left2Lever);
        handler.addStillObject(right1Lever);
        handler.addStillObject(right2Lever);

        // ##################### Here we make the standard Initialisation of the level ############################
        InitGenericLevel();

        resetLevel();
    }
    @Override
    public int getLevelCode() {
        return 1;
    }

}
