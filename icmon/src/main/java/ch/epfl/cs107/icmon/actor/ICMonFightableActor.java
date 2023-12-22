package ch.epfl.cs107.icmon.actor;

import ch.epfl.cs107.icmon.ICMon.ICMonGameState;
import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;
import ch.epfl.cs107.icmon.gamelogic.actions.LeaveAreaAction;
import ch.epfl.cs107.icmon.gamelogic.events.PokemonFightEvent;
import ch.epfl.cs107.icmon.gamelogic.fights.ICMonFight;
import ch.epfl.cs107.icmon.gamelogic.messages.SuspendWithEvent;

public interface ICMonFightableActor {

    /**
     * 
     * @param ally
     * @param enemy
     * @param state
     */
    public default void fight(Pokemon player, ICMonFightableActor opponent, ICMonGameState state){
        PokemonFightEvent event = new PokemonFightEvent(new ICMonFight(player, (Pokemon)opponent));
        state.addEvent(event);
        SuspendWithEvent message = new SuspendWithEvent(event);
        state.send(message);
        event.onComplete(new LeaveAreaAction((Pokemon)opponent));
    }
    
}
