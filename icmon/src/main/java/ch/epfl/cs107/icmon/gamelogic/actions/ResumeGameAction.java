package ch.epfl.cs107.icmon.gamelogic.actions;

import ch.epfl.cs107.icmon.ICMon;

public class ResumeGameAction implements Action {
    /** ??? */
    private ICMon game;

    public ResumeGameAction(ICMon game){
        this.game = game;
    }

    @Override
    public void perform() {
        game.requestResume();
    }
    
}
