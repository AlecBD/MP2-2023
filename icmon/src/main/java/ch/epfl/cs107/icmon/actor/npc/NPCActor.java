package ch.epfl.cs107.icmon.actor.npc;

import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.icmon.actor.ICMonActor;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.engine.actor.RPGSprite;
import ch.epfl.cs107.play.engine.actor.Sprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;
import ch.epfl.cs107.play.math.RegionOfInterest;

public abstract class NPCActor extends ICMonActor {

    /** ??? */
    protected Sprite sprite;

    /**
     * NPCActor Constructor
     * 
     * @param owner
     * @param orientation
     * @param coordinates
     * @param spriteName 
     */
    public NPCActor(Area owner, Orientation orientation, DiscreteCoordinates coordinates, String spriteName){
        super(owner, orientation, coordinates);
        sprite = new RPGSprite(spriteName, 1, 1.3125f, this, new RegionOfInterest(0, 0, 16, 21));
    }

    @Override
    public boolean takeCellSpace() {
        return true;
    }

    @Override
    public boolean isCellInteractable() {
        return false;
    }

    @Override
    public boolean isViewInteractable() {
        return true;
    }

    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        return Collections.singletonList(getCurrentMainCellCoordinates());
    }
    
}
