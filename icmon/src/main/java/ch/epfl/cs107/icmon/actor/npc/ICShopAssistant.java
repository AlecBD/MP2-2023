package ch.epfl.cs107.icmon.actor.npc;

import ch.epfl.cs107.icmon.actor.handler.ICMonInteractionVisitor;
import ch.epfl.cs107.play.areagame.actor.Interactable;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;
import ch.epfl.cs107.play.window.Canvas;

public class ICShopAssistant extends NPCActor {

    /** ??? */
    private ICMonInteractionVisitor handler;

    /**
     * ICShopAssistant Constructor
     * 
     * @param owner
     * @param orientation
     * @param coordinates
     */
    public ICShopAssistant(Area owner, Orientation orientation, DiscreteCoordinates coordinates){
        super(owner, orientation, coordinates, "actors/assistant");
    }

    /**
     * 
     * @param v
     * @param isCellInteraction
     */
    @Override
    public void acceptInteraction(AreaInteractionVisitor v, boolean isCellInteraction) {
        ((ICMonInteractionVisitor) v).interactWith(this , isCellInteraction);
    }

    /**
     * 
     * @param canvas
     */
    @Override
    public void draw(Canvas canvas) {
        sprite.draw(canvas);
    }

    /**
     * ???
     * @param other (Interactable) with whom interact
     * @param isCellInteraction (boolean) kind of interaction
     */
    public void interactWith(Interactable other , boolean isCellInteraction) {
        other.acceptInteraction(handler , isCellInteraction);
    }
    
}
