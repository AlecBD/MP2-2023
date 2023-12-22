package ch.epfl.cs107.icmon.gamelogic.messages;

import ch.epfl.cs107.icmon.ICMon.ICMonGameState;
import ch.epfl.cs107.icmon.actor.Door;
import ch.epfl.cs107.icmon.actor.ICMonPlayer;
import ch.epfl.cs107.icmon.area.ICMonArea;

public class PassDoorMessage extends GamePlayMessage {

    /** ??? */
    private Door door;
    /** ??? */
    private ICMonGameState state;
    /** ??? */
    private ICMonPlayer player;

    /**
     * 
     * @param door
     * @param state
     * @param player
     */
    public PassDoorMessage(Door door, ICMonGameState state, ICMonPlayer player){
        this.door = door;
        this.state = state;
        this.player = player;
    }

    @Override
    public void process(){
        player.leaveArea();
        ICMonArea currentArea = (ICMonArea) state.setCurrentArea(door.getInsideArea(), false);
        player.enterArea(currentArea, door.getArrivalCoordinates());
    }
    
}
