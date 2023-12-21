package ch.epfl.cs107.icmon.gamelogic.messages;

import ch.epfl.cs107.icmon.ICMon.ICMonGameState;
import ch.epfl.cs107.icmon.actor.Door;
import ch.epfl.cs107.icmon.actor.ICMonActor;
import ch.epfl.cs107.icmon.area.ICMonArea;

public class PassDoorMessage extends GamePlayMessage {

    /** ??? */
    private Door door;
    /** ??? */
    private ICMonGameState state;

    public PassDoorMessage(Door door, ICMonGameState state){
        this.door = door;
        this.state = state;
    }

    public void process(ICMonActor actor){
        actor.leaveArea();
        ICMonArea currentArea = (ICMonArea) state.setCurrentArea(door.getInsideArea(), false);
        actor.enterArea(currentArea, door.getArrivalCoordinates());
    }
    
}
