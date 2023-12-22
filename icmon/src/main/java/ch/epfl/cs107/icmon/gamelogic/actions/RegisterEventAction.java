package ch.epfl.cs107.icmon.gamelogic.actions;

import ch.epfl.cs107.icmon.ICMon.ICMonEventManager;
import ch.epfl.cs107.icmon.gamelogic.events.ICMonEvent;

public class RegisterEventAction implements Action {

    /** ??? */
    private ICMonEventManager handler;
    /** ??? */
    private ICMonEvent event;

    /**
     * 
     * @param handler
     * @param event
     */
    public RegisterEventAction(ICMonEventManager handler, ICMonEvent event){
        this.handler = handler;
        this.event = event;
    }
    
    @Override
    public void perform() {
        handler.activateEvent(event);
    }
}
