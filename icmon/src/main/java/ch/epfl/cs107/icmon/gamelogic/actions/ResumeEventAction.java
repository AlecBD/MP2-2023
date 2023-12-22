package ch.epfl.cs107.icmon.gamelogic.actions;

import java.util.ArrayList;
import java.util.List;

import ch.epfl.cs107.icmon.gamelogic.events.ICMonEvent;

public class ResumeEventAction implements Action {

    /** ??? */
    private List<ICMonEvent> events = new ArrayList<>();

    public ResumeEventAction(ICMonEvent event){
        this.events.add(event);
    }

    @Override
    public void perform() {
        if(events.size() > 0) for(ICMonEvent event : events) event.resume();
    }
    
}