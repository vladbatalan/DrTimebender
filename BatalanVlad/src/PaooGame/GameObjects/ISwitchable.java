package PaooGame.GameObjects;

import PaooGame.Physics.PVector;

public interface ISwitchable {
    void turnOn(String command);
    void turnOff(String command);
    PVector getSwitchablePosition();
}
