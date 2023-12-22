package ch.epfl.cs107.icmon.gamelogic.messages;

import ch.epfl.cs107.icmon.actor.ICMonActor;
import ch.epfl.cs107.icmon.gamelogic.events.PokemonFightEvent;

public class SuspendWithEvent extends GamePlayMessage {

    /** ??? */
    private PokemonFightEvent event;
    /** ??? */
    private final static String text = "suspension des événements en cours à cause d'un événement combat";

    /**
     * 
     * @param event
     */
    public SuspendWithEvent(PokemonFightEvent event){
        this.event = event;
    }

    /**
     * 
     * @return
     */
    public PokemonFightEvent getEvent() {
        return event;
    }

    @Override
    public void process() {
        System.out.println(text);
        event.start();
    }
    
}
