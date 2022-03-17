package PaooGame.levels;

import PaooGame.gameObjects.mobileObjects.MobileObject;

public class LevelFlagsSystem {
    // Flags

    // the player can move end the map is updateing
    public static Boolean isLevelRunning = false;

    // the reset is currently running
    public static Boolean isOnReset = false;

    // we use this to check if pressing space will restart the game creating a new instance of player
    public static Boolean playerOnTimeMachine = false;

    // Player on Goal
    public static Boolean playerOnGoal = false;

    // we some kind of system to check if old player instance when press space, maybe
    public static MobileObject onParadoxMob = null;
    public static boolean isInstanceOnParadox = false;


    public static void createParadox(MobileObject mob){
        onParadoxMob = mob;
        isInstanceOnParadox = true;
    }

    public static boolean enablePlayerControl = true;

    public static void resetFlagStates(){
        isLevelRunning = false;
        isOnReset = false;
        playerOnTimeMachine = false;
        onParadoxMob = null;
        isInstanceOnParadox = false;
        enablePlayerControl = true;
    }


}
