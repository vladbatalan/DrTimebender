package PaooGame.Physics.Enums;

public enum ColliusionTypes {
    TOP(0),
    RIGHT(1),
    BOTTOM(2),
    LEFT(3),
    DEADLY(4);

    int value;
    ColliusionTypes(int value){
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
