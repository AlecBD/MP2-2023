package ch.epfl.cs107.icmon.gamelogic.actions;

import ch.epfl.cs107.icmon.gamelogic.events.ICMonEvent;

public class StartEventAction implements Action {

    /** ??? */
    private ICMonEvent event;

    /**
     * 
     * @param event
     */
    public StartEventAction(ICMonEvent event){
        this.event = event;
    }

    @Override
    public void perform() {
        event.start();
    }
    
}
