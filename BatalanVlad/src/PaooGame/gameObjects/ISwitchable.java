package PaooGame.gameObjects;

import PaooGame.physics.PointVector;

public interface ISwitchable {
    void turnOn(String command);
    void turnOff(String command);
    PointVector getSwitchablePosition();
}
