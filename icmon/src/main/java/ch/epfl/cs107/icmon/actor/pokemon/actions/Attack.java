package ch.epfl.cs107.icmon.actor.pokemon.actions;

import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;
import ch.epfl.cs107.icmon.gamelogic.actions.Action;
import ch.epfl.cs107.icmon.gamelogic.fights.ICMonFightAction;

public class Attack implements ICMonFightAction, Action {

    @Override
    public String name() {
        return "Attack";
    }

    @Override
    public boolean doAction(Pokemon active, Pokemon target) {
        target.receiveDamage(active.properties().damage());
        return true;
    }

    @Override
    public void perform() {
    }
    
}
