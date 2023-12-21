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
     * @param owner ???
     * @param orientation ???
     * @param coordinates ???
     */
    public ICMonActor(Area owner, Orientation orientation, DiscreteCoordinates coordinates){
        super(owner, orientation, coordinates);
    }
    
    /**
     * ???
     */
    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        return Collections.singletonList(getCurrentMainCellCoordinates());
    }

    /**
     * ???
     * @return ???
     */
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
     * ???
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

    /**
     * ???
     * @return ???
     */
    @Override
    public boolean isCellInteractable() {
        return true;
    }

    /**
     * ???
     * @return ???
     */
    @Override
    public boolean isViewInteractable() {
        return false;
    }

    /**
     * ???
     * @param text ???
     */
    public void openDialog(String text){
        dialog = new Dialog(text);
    }
}
