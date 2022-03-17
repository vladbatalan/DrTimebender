package PaooGame.gameObjects;

public interface ISwitch {
    void addAffectedObject(ISwitchable obj);
    void addAffectedObject(ISwitchable obj, boolean frontOfChange);
    void addAffectedObject(ISwitchable obj, boolean frontOfChange, String command);
}
