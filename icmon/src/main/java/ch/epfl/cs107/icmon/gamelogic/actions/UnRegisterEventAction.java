package ch.epfl.cs107.icmon.gamelogic.actions;

import ch.epfl.cs107.icmon.ICMon.ICMonEventManager;
import ch.epfl.cs107.icmon.gamelogic.events.ICMonEvent;

public class UnRegisterEventAction implements Action {

    /** ??? */
    private ICMonEventManager handler;
    /** ??? */
    private ICMonEvent event;

    public UnRegisterEventAction(ICMonEventManager handler, ICMonEvent event){
        this.handler = handler;
        this.event = event;
    }
    
    @Override
    public void perform() {
        if(event.isCompleted()) handler.completeEvent(event);
        else handler.deActivateEvent(event);
    }
    
}
