package ch.epfl.cs107.icmon.gamelogic.events;

import ch.epfl.cs107.icmon.gamelogic.fights.ICMonFight;
import ch.epfl.cs107.play.window.Window;

public class PokemonFightEvent extends ICMonEvent {
    
    /** ??? */
    private ICMonFight fight;

    public PokemonFightEvent(Window window){
        fight = new ICMonFight();
    }

    public boolean hasPauseMenu(){
        return true;
    }

    public ICMonFight getFight() {
        return fight;
    }

    @Override
    public void update(float deltaTime) {
        fight.update(deltaTime);
        if(!fight.isRunning()){
            complete();
        }
    }
}
