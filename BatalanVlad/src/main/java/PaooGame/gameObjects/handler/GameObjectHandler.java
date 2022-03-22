package PaooGame.gameObjects.handler;

import PaooGame.gameObjects.ObjectID;
import PaooGame.gameObjects.mobileObjects.MobileObject;
import PaooGame.gameObjects.mobileObjects.OldPlayerInstance;
import PaooGame.gameObjects.mobileObjects.Player;
import PaooGame.gameObjects.handler.ObjectCollisionHandler;
import PaooGame.gameObjects.stillObjects.StillObject;
import PaooGame.gameObjects.stillObjects.TimeMachine;
import PaooGame.gameObjects.stillObjects.TwoPanScale;
import PaooGame.levels.LevelFlagsSystem;
import PaooGame.tiles.Map;

import java.awt.*;
import java.util.ArrayList;

public class GameObjectHandler {
    private final ArrayList<MobileObject> mobileObjects;
    private final ArrayList<StillObject> stillObjects;
    private final ArrayList<OldPlayerInstance> oldInstances;
    //The player will be on MobileObjectList

    public GameObjectHandler() {
        mobileObjects = new ArrayList<>();
        stillObjects = new ArrayList<>();
        oldInstances = new ArrayList<>();
    }

    public void Update(Map currentMap) {
        // update mobile objects
        for (int index = 0; index < mobileObjects.size(); index++) {
            MobileObject mob = mobileObjects.get(index);
            mob.Update(currentMap);
        }

        // update still objects
        for (int index = 0; index < stillObjects.size(); index++) {
            StillObject structure = stillObjects.get(index);
            structure.Update(currentMap);
        }

        // Interaction between Player and specific stationary objects
        Player player = getPlayer();
        LevelFlagsSystem.playerOnTimeMachine = false;
        LevelFlagsSystem.playerOnGoal = false;
        if (player != null) {
            for (StillObject structure : stillObjects) {
                if (player.getBody().getHitBox().intersects(structure.getBody().getHitBox())) {
                    //we got a collision
                    if (structure.id == ObjectID.TimeMachine) {
                        TimeMachine timeMachine = (TimeMachine) structure;
                        //setter for end game condition
                        LevelFlagsSystem.playerOnTimeMachine = true;
                        //display a help string above time machine
                        timeMachine.displayHelpString();
                        //System.out.println("Player on Time Machine");
                    }
                    if (structure.id == ObjectID.Objective) {
                        //setter for player - time machine collision
                        LevelFlagsSystem.playerOnGoal = true;
                        //System.out.println("Player on Goal");
                    }
                }
            }
        }
        // The generac concrete interaction that make objects interract with other objects
        // the objects that are not solid, are not taken into discussion

        for (MobileObject mob : mobileObjects) {
            for (StillObject structure : stillObjects) {

                // special case: structure is a composit Object
                if (structure.getId() == ObjectID.TwoPanScale) {

                    TwoPanScale scale = (TwoPanScale) structure;
                    ObjectCollisionHandler.manageObjectsCollision(mob, scale.getFirstPan());
                    ObjectCollisionHandler.manageObjectsCollision(mob, scale.getSecondPan());
                    continue;
                }
                // we let the collision handler to manage the interaction
                ObjectCollisionHandler.manageObjectsCollision(mob, structure);
            }
        }

    }

    public void Draw(Graphics g) {
        for (StillObject structure : stillObjects) {
            structure.Draw(g);
        }
        MobileObject player = null;
        for (int index = 0; index < mobileObjects.size(); index++) {
            MobileObject mob = mobileObjects.get(index);
            if (mob.id == ObjectID.Player)
                player = mob;
            else
                mob.Draw(g);
        }
        if (player != null)
            player.Draw(g);
    }

    public void setPlayer(Player player) {
        if (getPlayer() == null)
            mobileObjects.add(player);
        else
            for (int index = 0; index < mobileObjects.size(); index++)
                if (mobileObjects.get(index).id == ObjectID.Player)
                    mobileObjects.set(index, player);
    }

    public Player getPlayer() {
        for (int index = 0; index < mobileObjects.size(); index++)
            if (mobileObjects.get(index).id == ObjectID.Player)
                return (Player) mobileObjects.get(index);
        return null;
    }

    public void addStillObject(StillObject structure) {
        stillObjects.add(structure);
    }

    public void addMobileObject(MobileObject mobile) {
        mobileObjects.add(mobile);
    }

    public void addOldInstance(OldPlayerInstance oldPlayer) {
        oldInstances.add(oldPlayer);
    }

    public void renewOldInstances() {
        ArrayList<MobileObject> toBeRemoved = new ArrayList<>();
        for (MobileObject mobile : mobileObjects) {
            if (mobile.id == ObjectID.OldPlayerInstance) {
                toBeRemoved.add(mobile);
            }
        }

        for (MobileObject removed : toBeRemoved) {
            mobileObjects.remove(removed);
        }

        mobileObjects.addAll(oldInstances);
    }

    public void resetInitialLevelPosition() {
        // update mobile objects
        for (MobileObject mob : mobileObjects) {
            mob.resetToInitialState();
        }

        // update still objects
        for (StillObject structure : stillObjects) {
            structure.resetToInitialState();
        }
    }

    public void clearOldInstances() {
        oldInstances.clear();
    }
    public void clearAll() {
        oldInstances.clear();
        mobileObjects.clear();
        stillObjects.clear();
    }

    public ArrayList<StillObject> getStillObjects() {
        return stillObjects;
    }

    public ArrayList<MobileObject> getMobileObjects() {
        return mobileObjects;
    }

    public ArrayList<OldPlayerInstance> getOldInstances() {
        return oldInstances;
    }
}
