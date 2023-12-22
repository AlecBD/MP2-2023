package ch.epfl.cs107.icmon.actor;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import ch.epfl.cs107.icmon.actor.handler.ICMonInteractionVisitor;
import ch.epfl.cs107.play.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.areagame.actor.Interactable;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;
import ch.epfl.cs107.play.window.Canvas;

public final class Door extends AreaEntity {
    
    /** ??? */
    private String insideArea;
    /** ??? */
    private DiscreteCoordinates arrivalCoordinates;
    /** ??? */
    private List<DiscreteCoordinates> ocupiedCells = new ArrayList<>();
    /** ??? */
    private ICMonInteractionVisitor handler;

    public Door(Area owner, DiscreteCoordinates principalPosition, String insideArea, DiscreteCoordinates arrCds){
        this(owner, insideArea, arrCds, new DiscreteCoordinates[]{principalPosition});
    }

    public Door(Area owner, String insideArea, DiscreteCoordinates arrCds, DiscreteCoordinates... positions){
        super(owner, Orientation.UP, positions[0]);
        for(DiscreteCoordinates coordinates : positions){
            ocupiedCells.add(coordinates);
        }
        this.insideArea = insideArea;
        arrivalCoordinates = arrCds;
    }

    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        List<DiscreteCoordinates> cells = new LinkedList<>();
        for(DiscreteCoordinates coordinates : ocupiedCells){
            cells.add(coordinates);
        }
        return cells;
    }

    @Override
    public boolean takeCellSpace() {
        return false;
    }

    @Override
    public boolean isCellInteractable() {
        return true;
    }

    @Override
    public void draw(Canvas canvas) {
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
     */
    @Override
    public void acceptInteraction(AreaInteractionVisitor v, boolean isCellInteraction) {
        ((ICMonInteractionVisitor) v).interactWith(this , isCellInteraction);
    }

    /***
     * 
     * @return ???
     */
    public String getInsideArea() {
        return insideArea;
    }

    /**
     * 
     * @return ???
     */
    public DiscreteCoordinates getArrivalCoordinates() {
        return arrivalCoordinates;
    }

    /**
     * ???
     * @param other ???
     * @param isCellInteraction ???
     */
    public void interactWith(Interactable other , boolean isCellInteraction) {
        other.acceptInteraction(handler , isCellInteraction);
    }
}
