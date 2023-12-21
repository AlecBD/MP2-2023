package ch.epfl.cs107.icmon.actor.handler;

import ch.epfl.cs107.icmon.actor.Door;
import ch.epfl.cs107.icmon.actor.ICMonPlayer;
import ch.epfl.cs107.icmon.actor.items.ICBall;
import ch.epfl.cs107.icmon.actor.npc.ICShopAssistant;
import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;
import ch.epfl.cs107.icmon.area.ICMonbehavior.ICMonCell;
import ch.epfl.cs107.play.areagame.handler.AreaInteractionVisitor;

public interface ICMonInteractionVisitor extends AreaInteractionVisitor {

    public default void interactWith(ICMonCell cell, boolean isCellInteraction){}

    public default void interactWith(ICMonPlayer player, boolean isCellInteraction){}

    public default void interactWith(ICBall ball, boolean isCellInteraction){}

    public default void interactWith(ICShopAssistant assistant , boolean isCellInteraction){}

    public default void interactWith(Door door, boolean isCellInteraction) {}

    public default void interactWith(Pokemon pokemon, boolean isCellInteraction) {}


    
}
