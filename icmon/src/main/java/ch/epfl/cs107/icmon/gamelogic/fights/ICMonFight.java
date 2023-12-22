package ch.epfl.cs107.icmon.gamelogic.fights;

import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;
import ch.epfl.cs107.icmon.actor.pokemon.actions.Attack;
import ch.epfl.cs107.icmon.graphics.ICMonFightActionSelectionGraphics;
import ch.epfl.cs107.icmon.graphics.ICMonFightArenaGraphics;
import ch.epfl.cs107.icmon.graphics.ICMonFightTextGraphics;
import ch.epfl.cs107.play.engine.PauseMenu;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;

public class ICMonFight extends PauseMenu {

    /** ??? */
    private Pokemon player;
    /** ??? */
    private Pokemon opponent;
    /** ??? */
    private ICMonFightArenaGraphics arena;
    /** ??? */
    private FightState state = FightState.INTRODUCTION;
    /** ??? */
    private String text = "Hello World"; 
    /** ??? */
    private boolean running = true;
    /** ??? */
    private ICMonFightAction action;
    /** ??? */
    private ICMonFightActionSelectionGraphics selectionMenu;
    /** ??? */
    private Keyboard keyboard;

    /**
     * 
     * @param player
     * @param opponent
     */
    public ICMonFight(Pokemon player, Pokemon opponent){
        this.player = player;
        this.opponent = opponent;

        arena = new ICMonFightArenaGraphics(CAMERA_SCALE_FACTOR , player.properties(), opponent.properties());
        keyboard = player.getICMonOwnerArea().getKeyboard();
        selectionMenu = new ICMonFightActionSelectionGraphics(CAMERA_SCALE_FACTOR, keyboard, player.properties().actions());
    }

    @Override
    public void update(float deltaTime){
        super.update(deltaTime);
        Button space = keyboard.get(Keyboard.SPACE);
        switch(state){
            case INTRODUCTION:
                if(space.isDown()) state = FightState.ACTIONSELECTION;
                break;
            case ACTIONSELECTION:
                selectionMenu.update(deltaTime);
                if(selectionMenu.choice() != null){
                    action = selectionMenu.choice();
                    selectionMenu = new ICMonFightActionSelectionGraphics(CAMERA_SCALE_FACTOR, keyboard, player.properties().actions());
                    state = FightState.ACTIONEXECUTION;
                }
                break;
            case ACTIONEXECUTION:
                if(action.doAction(player, opponent)){
                    if(!opponent.properties().isAlive()){
                        state = FightState.CONCLUSION;
                        state.setText("The player has won the fight");
                    }
                    else{
                        state = FightState.OPPONENTACTION;
                    }
                }
                else{
                    state = FightState.CONCLUSION;
                    state.setText("The player decided not to continue the fight");
                }
                break;
            case OPPONENTACTION:
                for(ICMonFightAction action : opponent.properties().actions()){
                    if(action instanceof Attack){
                        if(action.doAction(opponent, player)){
                            if(!player.properties().isAlive()){
                                state = FightState.CONCLUSION;
                                state.setText("The opponent has won the fight");
                            }
                            else{
                                state = FightState.ACTIONSELECTION;
                            }
                        }
                        else{
                            state = FightState.CONCLUSION;
                            state.setText("The opponent decided not to continue the fight");
                        }
                    }
                }
                
            case CONCLUSION:
                if(space.isDown()){
                    running = false;
                    end();
                }
                break;
        }
        text = state.getText();
    }

    /**
     * ???
     * @return ???
     */
    public boolean isRunning(){
        return running;
    }

    @Override
    public void drawMenu(Canvas c) {
        if(state == FightState.ACTIONSELECTION){
            arena.setInteractionGraphics(selectionMenu);
        }
        else arena.setInteractionGraphics(new ICMonFightTextGraphics(CAMERA_SCALE_FACTOR, text));
        arena.draw(c);
    }

    private enum FightState {
        INTRODUCTION("Welcome to the fight"),
        ACTIONSELECTION(null),
        OPPONENTACTION(null),
        ACTIONEXECUTION(null),
        CONCLUSION("Good fight !");

        String text;

        /**
         * 
         * @param type
         * @param text
         */
        private FightState(String text){
            this.text = text;
        }

        /**
         * 
         * @param text
         */
        private void setText(String text) {
            this.text = text;
        }

        /**
         * 
         * @return
         */
        private String getText() {
            return text;
        }

    }


    
}
