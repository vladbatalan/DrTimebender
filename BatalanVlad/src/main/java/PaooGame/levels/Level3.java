package PaooGame.levels;

import PaooGame.gameObjects.stillObjects.*;
import PaooGame.physics.PointVector;
import PaooGame.tiles.Map;
import PaooGame.tiles.Tile;

import java.awt.*;

public class Level3 extends Level {
    @Override
    public void InitLevel() {
        // set the next level
        nextLevel = null;

        // ###################### Here we display first the specifics of the Level ############################
        // The name of the level
        levelName = "Level 3: Flying to the top!";

        // The current Map
        currentMap = new Map("res/maps/map3.txt");
        currentMap.setBackground("/backgrounds/nightBackground.jpg");

        // Create the existing objects except the Player that is loaded at the start of a level Phase
        startTimeMachine = new TimeMachine(new PointVector(Tile.TILE_WIDTH * 10, Tile.TILE_HEIGHT * 47 - 100));
        gameObjective = new Objective(new PointVector(Tile.TILE_WIDTH * 36, Tile.TILE_HEIGHT * 7 - 80), true);

        MovingPlatformControlled upDownPlatform = new MovingPlatformControlled(new PointVector(Tile.TILE_WIDTH * 50, Tile.TILE_HEIGHT * 46 - 15), new Color(0xFF0002));
        upDownPlatform.setSpeed(6);

        MovingPlatformControlled spacePlatform = new MovingPlatformControlled(new PointVector(Tile.TILE_WIDTH * 17, Tile.TILE_HEIGHT * 47 - 15), new Color(0x00D679));
        spacePlatform.setSpeed(5);

        PushButton upRightPlatformButton = new PushButton(new PointVector(Tile.TILE_WIDTH * 40, Tile.TILE_HEIGHT * 47 - 15));
        upRightPlatformButton.setDescriptionString("Up");
        upRightPlatformButton.addAffectedObject(upDownPlatform, true, "Up");

        PushButton downRightPlatformButton = new PushButton(new PointVector(Tile.TILE_WIDTH * 43, Tile.TILE_HEIGHT * 47 - 15));
        downRightPlatformButton.addAffectedObject(upDownPlatform, true, "Down");
        downRightPlatformButton.setDescriptionString("Down");
        downRightPlatformButton.changeButtonType(0, 2);

        PushButton upSpaceButton = new PushButton(new PointVector(Tile.TILE_WIDTH * 44, Tile.TILE_HEIGHT * 32 - 15));
        upSpaceButton.setDescriptionString("Up");
        upSpaceButton.addAffectedObject(spacePlatform, true, "Up");

        PushButton downSpaceButton = new PushButton(new PointVector(Tile.TILE_WIDTH * 44, Tile.TILE_HEIGHT * 26 - 15));
        downSpaceButton.setDescriptionString("Down");
        downSpaceButton.changeButtonType(0, 1);
        downSpaceButton.addAffectedObject(spacePlatform, true, "Down");

        PushButton leftSpaceButton = new PushButton(new PointVector(Tile.TILE_WIDTH * 44, Tile.TILE_HEIGHT * 20 - 15));
        leftSpaceButton.setDescriptionString("Left");
        leftSpaceButton.addAffectedObject(spacePlatform, true, "Left");

        PushButton rightSpaceButton = new PushButton(new PointVector(Tile.TILE_WIDTH * 44, Tile.TILE_HEIGHT * 14 - 15));
        rightSpaceButton.setDescriptionString("Right");
        rightSpaceButton.changeButtonType(0, 3);
        rightSpaceButton.addAffectedObject(spacePlatform, true, "Right");


        TimedGate topGate = new TimedGate(new PointVector(Tile.TILE_WIDTH * 30, Tile.TILE_HEIGHT * 7 - 4), 4*Tile.TILE_HEIGHT - 4, true, new Color(0xDC5040));

        Lever topGateLever = new Lever(new PointVector(Tile.TILE_WIDTH * 44, Tile.TILE_HEIGHT * 38 - 60));
        topGateLever.addAffectedObject(topGate);

        TimedGate downGate = new TimedGate(new PointVector(Tile.TILE_WIDTH * 28, Tile.TILE_HEIGHT * 20 - 4), 4*Tile.TILE_HEIGHT - 4, true, new Color(0x57C5D6));

        Lever downGateLever = new Lever(new PointVector(Tile.TILE_WIDTH * 44, Tile.TILE_HEIGHT * 8 - 60));
        downGateLever.addAffectedObject(downGate);

        // Adding Objects to Handler
        handler.addStillObject(startTimeMachine);
        handler.addStillObject(gameObjective);
        handler.addStillObject(upDownPlatform);
        handler.addStillObject(upRightPlatformButton);
        handler.addStillObject(downRightPlatformButton);
        handler.addStillObject(spacePlatform);
        handler.addStillObject(upSpaceButton);
        handler.addStillObject(downSpaceButton);
        handler.addStillObject(leftSpaceButton);
        handler.addStillObject(rightSpaceButton);
        handler.addStillObject(topGate);
        handler.addStillObject(topGateLever);
        handler.addStillObject(downGate);
        handler.addStillObject(downGateLever);
       // handler.addStillObject(myGate);
        //handler.addStillObject(gateLever);

        // ##################### Here we make the standard Initialisation of the level ############################
        InitGenericLevel();

        resetLevel();
    }
    @Override
    public int getLevelCode() {
        return 3;
    }

}
