package ch.epfl.cs107.icmon.gamelogic.events;

import ch.epfl.cs107.icmon.actor.ICMonPlayer;
import ch.epfl.cs107.icmon.actor.items.ICBall;
import ch.epfl.cs107.icmon.actor.items.ICMonItem;
import ch.epfl.cs107.icmon.actor.npc.ICShopAssistant;

public class CollectItemEvent extends ICMonEvent {

    /** ??? */
    private ICMonItem item;
    /** ??? */
    private ICMonPlayer player;

    /**
     * 
     * @param item (ICMonItem) not null
     */
    public CollectItemEvent(ICMonItem item, ICMonPlayer player){
        this.player = player;
        this.item = item;
    }

    /**
     * ???
     * @param deltaTime elapsed time since last update, in seconds, non-negative
     */
    @Override
    public void update(float deltaTime) {
        if(item.isCollected()){
            complete();

        }
    }

    public void interactWith(ICBall ball , boolean isCellInteraction){
        player.addBall(ball);;
        ball.getCollected();
        System.out.println("Player is interacting with Ball");
    }

    public void interactWith(ICShopAssistant assistant, boolean isCellInteractable){
        if(isStarted()){
            System.out.println("This is an interaction between the player and ICShopAssistant based on events !");
            player.openDialog("collect_item_event_interaction_with_icshopassistant");
            player.openDialog();
        }
    }
    
    
}
