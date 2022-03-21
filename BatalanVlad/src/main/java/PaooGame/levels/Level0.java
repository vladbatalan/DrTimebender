package PaooGame.levels;

import PaooGame.gameObjects.stillObjects.Lever;
import PaooGame.gameObjects.stillObjects.Objective;
import PaooGame.gameObjects.stillObjects.TimeMachine;
import PaooGame.gameObjects.stillObjects.TimedGate;
import PaooGame.physics.PointVector;
import PaooGame.tiles.Map;
import PaooGame.tiles.Tile;

public class Level0 extends Level {

    public void InitLevel() {
        // set the next level
        nextLevel = new Level1();

        // ###################### Here we display first the specifics of the Level ############################
        // The name of the level
        levelName = "Level 0: Into the Laboratory";

        // The current Map
        currentMap = new Map("res/maps/map0.txt");
        currentMap.setBackground("/backgrounds/nightBackground.jpg");

        // Create the existing objects except the Player that is loaded at the start of a level Phase
        startTimeMachine = new TimeMachine(new PointVector(Tile.TILE_WIDTH * 2, Tile.TILE_HEIGHT * 13 - 100));
        gameObjective = new Objective(new PointVector(Tile.TILE_WIDTH * 7, Tile.TILE_HEIGHT * 13 - 80), false);

        //the timed gate
        TimedGate myGate = new TimedGate(new PointVector(Tile.TILE_WIDTH * 17 + 12, Tile.TILE_HEIGHT * 13 - 4),3*Tile.TILE_HEIGHT - 4, true);

        // a switch for opening and closing the gameObjective
        Lever gateLever = new Lever(new PointVector(Tile.TILE_WIDTH * 14, Tile.TILE_HEIGHT * 13 - 60));
        Lever objectiveLever = new Lever(new PointVector(Tile.TILE_WIDTH * 3, Tile.TILE_HEIGHT * 21 - 60));

        // the lever affects the objective state
        gateLever.addAffectedObject(myGate);
        objectiveLever.addAffectedObject(gameObjective);

        // Adding Objects to Handler
        handler.addStillObject(startTimeMachine);
        handler.addStillObject(gameObjective);
        handler.addStillObject(myGate);
        handler.addStillObject(gateLever);
        handler.addStillObject(objectiveLever);

        // ##################### Here we make the standard Initialisation of the level ############################
        InitGenericLevel();

        resetLevel();
    }

    @Override
    public int getLevelCode() {
        return 0;
    }

}
