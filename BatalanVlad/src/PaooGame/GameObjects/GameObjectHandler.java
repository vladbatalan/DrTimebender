package PaooGame.GameObjects;

import PaooGame.GameObjects.MobileObjects.MobileObject;
import PaooGame.GameObjects.MobileObjects.OldPlayerInstance;
import PaooGame.GameObjects.MobileObjects.Player;
import PaooGame.GameObjects.ObjectCollisionHandler.ObjectCollisionHandler;
import PaooGame.GameObjects.StillObjects.StillObject;
import PaooGame.GameObjects.StillObjects.TimeMachine;
import PaooGame.GameObjects.StillObjects.TwoPanScale;
import PaooGame.Levels.LevelFlagsSystem;
import PaooGame.Tiles.Map;

import java.awt.*;
import java.util.ArrayList;

public class GameObjectHandler {
    private ArrayList<MobileObject> mobileObjects;
    private ArrayList<StillObject> stillObjects;
    private ArrayList<OldPlayerInstance> oldInstances;
    //The player will be on MobileObjectList

    public GameObjectHandler(){
        mobileObjects = new ArrayList<MobileObject>();
        stillObjects = new ArrayList<StillObject>();
        oldInstances = new ArrayList<OldPlayerInstance>();
    }

    public void Update(Map currentMap){
        // update mobile objects
        for(int index = 0; index < mobileObjects.size(); index++){
            MobileObject mob = mobileObjects.get(index);
            mob.Update(currentMap);
        }

        // update still objects
        for(int index = 0; index < stillObjects.size(); index++){
            StillObject structure = stillObjects.get(index);
            structure.Update(currentMap);
        }

        // Interaction between Player and specific stationary objects
        Player player = getPlayer();
        LevelFlagsSystem.playerOnTimeMachine = false;
        LevelFlagsSystem.playerOnGoal = false;
        if(player != null){
            for(StillObject structure : stillObjects){
                if(player.getBody().getHitBox().intersects(structure.getBody().getHitBox())){
                    //we got a collision
                    if(structure.id == ObjectID.TimeMachine){
                        TimeMachine timeMachine = (TimeMachine)structure;
                        //setter for end game condition
                        LevelFlagsSystem.playerOnTimeMachine = true;
                        //display a help string above time machine
                        timeMachine.displayHelpString();
                        //System.out.println("Player on Time Machine");
                    }
                    if(structure.id == ObjectID.Objective){
                        //setter for player - time machine collision
                        LevelFlagsSystem.playerOnGoal = true;
                        //System.out.println("Player on Goal");
                    }
                }
            }
        }
        // The generac concrete interaction that make objects interract with other objects
        // the objects that are not solid, are not taken into discussion

        for(int index = 0; index < mobileObjects.size(); index++){
            MobileObject mob = mobileObjects.get(index);
            for(int index2 = 0; index2 < stillObjects.size(); index2++) {
                StillObject structure = stillObjects.get(index2);

                // special case: structure is a composit Object
                if(structure.getId() == ObjectID.TwoPanScale){
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

    public void Draw(Graphics g){
        for(StillObject structure:stillObjects){
            structure.Draw(g);
        }
        MobileObject player = null;
        for(int index = 0; index < mobileObjects.size(); index++){
            MobileObject mob = mobileObjects.get(index);
            if(mob.id == ObjectID.Player)
                player = mob;
            else
                mob.Draw(g);
        }
        if(player != null)
            player.Draw(g);
    }

    public void setPlayer(Player player) {
        if(getPlayer() == null)
            mobileObjects.add(player);
        else
            for(int index = 0; index < mobileObjects.size(); index ++)
                if(mobileObjects.get(index).id == ObjectID.Player)
                    mobileObjects.set(index, player);
    }
    public Player getPlayer() {
        for(int index = 0; index < mobileObjects.size(); index ++)
            if(mobileObjects.get(index).id == ObjectID.Player)
                return (Player)mobileObjects.get(index);
        return null;
    }

    public void addStillObject(StillObject structure){
        stillObjects.add(structure);
    }
    public void addMobileObject(MobileObject mobile){
        mobileObjects.add(mobile);
    }
    public void addOldInstance(OldPlayerInstance oldPlayer){
        oldInstances.add(oldPlayer);
    }

    public void renewOldInstances(){
        ArrayList<MobileObject> toBeRemoved = new ArrayList<MobileObject>();
        for(MobileObject mobile : mobileObjects){
            if(mobile.id == ObjectID.OldPlayerInstance)
                toBeRemoved.add(mobile);
        }
        for(MobileObject removed:toBeRemoved){
            mobileObjects.remove(removed);
        }
        for(OldPlayerInstance oldPlayerInstance : oldInstances){
            mobileObjects.add(oldPlayerInstance);
        }
    }

    public void resetInitialLevelPosition(){
        // update mobile objects
        for(int index = 0; index < mobileObjects.size(); index++){
            MobileObject mob = mobileObjects.get(index);
            mob.resetToInitialState();
        }

        // update still objects
        for(int index = 0; index < stillObjects.size(); index++){
            StillObject structure = stillObjects.get(index);
            structure.resetToInitialState();
        }
    }

    public void clearOldInstances(){
        oldInstances.clear();
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

    public int getNumberOfOldInstances(){
        return oldInstances.size();
    }
}
