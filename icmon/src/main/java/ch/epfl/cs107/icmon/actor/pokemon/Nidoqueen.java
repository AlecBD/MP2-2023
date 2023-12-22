package ch.epfl.cs107.icmon.actor.pokemon;

import ch.epfl.cs107.icmon.actor.handler.ICMonInteractionVisitor;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;

public class Nidoqueen extends Pokemon {

    /**
     * Nidoqueen Constructor
     * 
     * @param owner
     * @param orientation
     * @param coordinates
     */
    public Nidoqueen(Area owner, Orientation orientation, DiscreteCoordinates coordinates){
        super(owner, orientation, coordinates, "nidoqueen", 1, 10);
    }

    @Override
    public void acceptInteraction(AreaInteractionVisitor v, boolean isCellInteraction) {
        ((ICMonInteractionVisitor) v).interactWith(this , isCellInteraction);
    }
    
}
