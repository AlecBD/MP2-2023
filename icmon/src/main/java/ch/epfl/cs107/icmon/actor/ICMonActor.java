package ch.epfl.cs107.icmon.actor;

import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.areagame.actor.MovableAreaEntity;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.engine.actor.Dialog;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;

public abstract class ICMonActor extends MovableAreaEntity {

    /** ??? */
    protected Dialog dialog;

    /**
     * Default ICMonActor Constructor 
     * 
     * @param owner (Area): Area of spawn. Not null
     * @param orientation (Orientation): Orientation on spawn. Not null
     * @param coordinates (DiscreteCoordinates): Coordinates at spawn
     */
    public ICMonActor(Area owner, Orientation orientation, DiscreteCoordinates coordinates){
        super(owner, orientation, coordinates);
    }
    
    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        return Collections.singletonList(getCurrentMainCellCoordinates());
    }

    @Override
    public boolean takeCellSpace() {
        return false;
    }

    /**
     * Leave an area by unregister this player
     */
    public void leaveArea() {
        getOwnerArea().unregisterActor(this);
    }

    /**
     * Enter an area at a certain position
     * 
     * @param area     (Area): initial area, not null
     * @param position (DiscreteCoordinates): initial position, not null
     */
    public void enterArea(Area area, DiscreteCoordinates position) {
        area.registerActor(this);
        area.setViewCandidate(this);
        setOwnerArea(area);
        setCurrentPosition(position.toVector());
        resetMotion();
    }

    @Override
    public boolean isCellInteractable() {
        return true;
    }

    @Override
    public boolean isViewInteractable() {
        return false;
    }

    /**
     * Sets a new Dialog
     * 
     * @param text (String): text int Diaalog
     */
    public void openDialog(String text){
        dialog = new Dialog(text);
    }

    /**
     * Needed for ICMonFight but can't use getOwnerArea because it is protected
     * 
     * @return (Area): getOwnerArea()
     */
    public Area getICMonOwnerArea(){
        return super.getOwnerArea();
    }
}
