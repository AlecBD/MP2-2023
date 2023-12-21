package ch.epfl.cs107.icmon.gamelogic.messages;

import ch.epfl.cs107.icmon.actor.ICMonActor;
import ch.epfl.cs107.icmon.gamelogic.events.PokemonFightEvent;

public class SuspendWithEvent extends GamePlayMessage {

    /** ??? */
    private final PokemonFightEvent event;

    public SuspendWithEvent(PokemonFightEvent event){
        this.event = event;
    }

    public PokemonFightEvent getEvent() {
        return event;
    }

    public boolean hasPauseMenu(){
        return event.hasPauseMenu();
    }

    @Override
    public void process(ICMonActor actor) {
        System.out.println("suspension des événements en cours à cause d'un événement combat");
    }
    
}
