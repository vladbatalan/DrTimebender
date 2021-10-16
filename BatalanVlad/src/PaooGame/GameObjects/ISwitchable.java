package PaooGame.GameObjects;

import PaooGame.Physics.PointVector;

public interface ISwitchable {
    void turnOn(String command);
    void turnOff(String command);
    PointVector getSwitchablePosition();
}
