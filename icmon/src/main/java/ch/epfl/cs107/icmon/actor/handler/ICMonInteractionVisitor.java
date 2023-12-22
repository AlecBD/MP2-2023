package ch.epfl.cs107.icmon.actor.handler;

import ch.epfl.cs107.icmon.actor.Door;
import ch.epfl.cs107.icmon.actor.ICMonPlayer;
import ch.epfl.cs107.icmon.actor.items.ICBall;
import ch.epfl.cs107.icmon.actor.npc.ICShopAssistant;
import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;
import ch.epfl.cs107.icmon.area.ICMonbehavior.ICMonCell;
import ch.epfl.cs107.play.areagame.handler.AreaInteractionVisitor;

public interface ICMonInteractionVisitor extends AreaInteractionVisitor {

    /**
     * ???
     * @param cell ???
     * @param isCellInteraction (boolean)
     */
    public default void interactWith(ICMonCell cell, boolean isCellInteraction){}

    /**
     * 
     * @param player
     * @param isCellInteraction
     */
    public default void interactWith(ICMonPlayer player, boolean isCellInteraction){}

    /**
     * 
     * @param ball
     * @param isCellInteraction
     */
    public default void interactWith(ICBall ball, boolean isCellInteraction){}

    /**
     * 
     * @param assistant
     * @param isCellInteraction
     */
    public default void interactWith(ICShopAssistant assistant , boolean isCellInteraction){}

    /**
     * 
     * @param door
     * @param isCellInteraction
     */
    public default void interactWith(Door door, boolean isCellInteraction) {}

    /**
     * 
     * @param pokemon
     * @param isCellInteraction
     */
    public default void interactWith(Pokemon pokemon, boolean isCellInteraction) {}


    
}
