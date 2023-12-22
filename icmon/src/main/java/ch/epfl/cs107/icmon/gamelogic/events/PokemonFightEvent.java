package ch.epfl.cs107.icmon.gamelogic.events;

import ch.epfl.cs107.icmon.gamelogic.fights.ICMonFight;

public class PokemonFightEvent extends ICMonEvent {
    
    /** ??? */
    private ICMonFight fight;

    public PokemonFightEvent(ICMonFight fight){
        this.fight = fight;
    }

    public ICMonFight getPauseMenu() {
        return fight;
    }

    @Override
    public void update(float deltaTime) {
        if(isStarted() && !isCompleted()){
            fight.update(deltaTime);
            if(!fight.isRunning()) complete();
        }
    }
}
