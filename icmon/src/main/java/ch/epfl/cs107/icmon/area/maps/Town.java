package ch.epfl.cs107.icmon.area.maps;

import ch.epfl.cs107.icmon.actor.Door;
import ch.epfl.cs107.icmon.actor.npc.ICShopAssistant;
import ch.epfl.cs107.icmon.area.ICMonArea;
import ch.epfl.cs107.play.engine.actor.Background;
import ch.epfl.cs107.play.engine.actor.Foreground;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;

public final class Town extends ICMonArea {

    /**
     * ???
     * @return ???
     */
    @Override
    public DiscreteCoordinates getPlayerSpawnPosition(){
        return new DiscreteCoordinates(5, 5);
    }

    /**
     * ???
     * @return ???
     */
    @Override
    public DiscreteCoordinates getBallSpawnPosition(){
        return new DiscreteCoordinates(6, 6);
    }

    /**
     * ???
     */
    @Override
    protected void createArea(){
        registerActor(new Background(this));
        registerActor(new Foreground(this));
        registerActor(new ICShopAssistant(this, Orientation.DOWN, new DiscreteCoordinates(8, 8)));
        registerActor(new Door(this, new DiscreteCoordinates(15, 24), "lab", new DiscreteCoordinates(6, 2)));
        registerActor(new Door(this, new DiscreteCoordinates(20, 16), "arena", new DiscreteCoordinates(4, 2)));
    }

    /**
     * ???
     * @return ???
     */
    @Override
    public String getTitle(){return "town";}
}
