package ch.epfl.cs107.icmon.gamelogic.actions;

import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.play.engine.PauseMenu;

public class PauseGameAction implements Action {

    /** ??? */
    private ICMon game;
    /** ??? */
    private PauseMenu pauseMenu;

    public PauseGameAction(ICMon game, PauseMenu pauseMenu){
        this.game = game;
        this.pauseMenu = pauseMenu;
    }

    @Override
    public void perform() {
        game.setPauseMenu(pauseMenu);
        game.requestPause();
    }
    
}
