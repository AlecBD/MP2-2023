package ch.epfl.cs107.icmon.gamelogic.actions;

import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.engine.actor.Actor;

public class RegisterInAreaAction implements Action {
    
    /** ??? */
    private Actor actor;
    /** ?? */
    private Area area;

    public RegisterInAreaAction(Actor actor, Area area){
        this.actor = actor;
        this.area = area;
    }

    @Override
    public void perform() {
        area.registerActor(actor);
    }
}
