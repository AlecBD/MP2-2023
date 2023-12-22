package ch.epfl.cs107.icmon.gamelogic.fights;

import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;
import ch.epfl.cs107.icmon.graphics.ICMonFightArenaGraphics;
import ch.epfl.cs107.icmon.graphics.ICMonFightTextGraphics;
import ch.epfl.cs107.play.engine.PauseMenu;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Window;

public class ICMonFight extends PauseMenu {

    /** ??? */
    private Pokemon player;
    /** ??? */
    private Pokemon oppponent;
    /** ??? */
    private float counter = 5f;
    /** ??? */
    private ICMonFightArenaGraphics arena;

    public ICMonFight(Pokemon player, Pokemon opponent, Window window){
        this.player = player;
        this.oppponent = opponent;
        this.window = window;

        arena = new ICMonFightArenaGraphics(CAMERA_SCALE_FACTOR , player.properties(), opponent.properties());
        
    }

    /**
     * ???
     * @param deltaTime
     */
    @Override
    public void update(float deltaTime){
        super.update(deltaTime);
        counter -= deltaTime;
    }

    /**
     * ???
     * @return ???
     */
    public boolean isRunning(){
        return counter > 0f;
    }

    public Window getWindow(){
        return window;
    }

    /**
     * ???
     * @param c (Canvas)
     */
    @Override
    public void drawMenu(Canvas c) {
        arena.setInteractionGraphics(new ICMonFightTextGraphics(CAMERA_SCALE_FACTOR ,"hello world"));
    }


    
}
