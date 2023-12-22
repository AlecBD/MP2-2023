package ch.epfl.cs107.icmon.actor.pokemon.actions;

import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;
import ch.epfl.cs107.icmon.gamelogic.actions.Action;
import ch.epfl.cs107.icmon.gamelogic.fights.ICMonFightAction;

public class RunAway implements ICMonFightAction, Action {

    @Override
    public String name() {
        return "Run Away";
    }

    @Override
    public boolean doAction(Pokemon active, Pokemon target) {
        return false;
    }

    @Override
    public void perform() {
    }
    
}
