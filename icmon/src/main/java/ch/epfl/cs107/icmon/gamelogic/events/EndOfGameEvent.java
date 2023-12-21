package ch.epfl.cs107.icmon.gamelogic.events;

import ch.epfl.cs107.icmon.actor.ICMonPlayer;
import ch.epfl.cs107.icmon.actor.npc.ICShopAssistant;

public class EndOfGameEvent extends ICMonEvent {
    
    /** ??? */
    private ICShopAssistant assistant;
    /** ??? */
    private boolean interacted = false;
    /** ??? */
    private ICMonPlayer player;

    /**
     * 
     * @param player
     * @param assistant
     */
    public EndOfGameEvent(ICMonPlayer player, ICShopAssistant assistant){
        this.player = player;
        this.assistant = assistant;
    }

    @Override
    public void update(float deltaTime) {
        if(interacted && isStarted()){
            complete();
        }
    }

    public void interactWith(ICShopAssistant assistant , boolean isCellInteraction){
        if(isStarted()){
            System.out.println("I heard that you were able to implement this step successfully. Congrats !");
            player.openDialog("end_of_game_event_interaction_with_icshopassistant");
            player.openDialog();
        }
    }
}
