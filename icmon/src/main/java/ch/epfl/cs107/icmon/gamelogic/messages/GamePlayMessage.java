package ch.epfl.cs107.icmon.gamelogic.messages;

import ch.epfl.cs107.icmon.actor.ICMonActor;

public abstract class GamePlayMessage {

    public abstract void process(ICMonActor actor);
    
}
