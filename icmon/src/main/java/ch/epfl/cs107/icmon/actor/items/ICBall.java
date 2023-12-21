package ch.epfl.cs107.icmon.actor.items;

import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.icmon.actor.handler.ICMonInteractionVisitor;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

public class ICBall extends ICMonItem {

    /** ??? */
    private boolean collected = false;

    /**
     * ???
     * @param owner ???
     * @param coordinates ???
     * @param spriteName ???
     */
    public ICBall(Area owner, DiscreteCoordinates coordinates, String spriteName){
        super(owner, coordinates, spriteName);
    }

    /**
     * ???
     * @return ???
     */
    @Override
     public boolean isCellInteractable(){
        return false;
    }

    /**
     * ???
     * @return ???
     */
    @Override
    public boolean isViewInteractable() {
        return true;
    }

    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        return Collections.singletonList(getCurrentMainCellCoordinates());
    }

    @Override
    public void acceptInteraction(AreaInteractionVisitor v, boolean isCellInteraction) {
        ((ICMonInteractionVisitor) v).interactWith(this , isCellInteraction);

    }

    @Override
    public void draw(Canvas canvas) {
        sprite.draw(canvas);
    }

    public void getCollected(){
        owner.unregisterActor(this);
        collected = true;
    }

    public boolean isCollected(){return collected;}
    
}
