package ch.epfl.cs107.icmon.area.maps;

import ch.epfl.cs107.icmon.actor.Door;
import ch.epfl.cs107.icmon.area.ICMonArea;
import ch.epfl.cs107.play.engine.actor.Background;
import ch.epfl.cs107.play.engine.actor.Foreground;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public class Lab extends ICMonArea {

    @Override
    public DiscreteCoordinates getPlayerSpawnPosition(){
        return new DiscreteCoordinates(5, 5);
    }

    @Override
    public DiscreteCoordinates getBallSpawnPosition(){
        return new DiscreteCoordinates(6, 6);
    }

    @Override
    protected void createArea(){
        registerActor(new Background(this));
        registerActor(new Foreground(this));
        registerActor(new Door(this, "town", new DiscreteCoordinates(15, 23), new DiscreteCoordinates[]{new DiscreteCoordinates(6, 1), new DiscreteCoordinates(7, 1)}));
    }

    @Override
    public String getTitle(){return "lab";}
    
}
