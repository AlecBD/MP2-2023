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
import ch.epfl.cs107.play.window.Window;

public class ICMonFight extends PauseMenu {

    /** ??? */
    private Pokemon player;
    /** ??? */
    private Pokemon opponent;
    /** ??? */
    private float counter = 5f;
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

    public ICMonFight(Pokemon player, Pokemon opponent){
        this.player = player;
        this.opponent = opponent;

        arena = new ICMonFightArenaGraphics(CAMERA_SCALE_FACTOR , player.properties(), opponent.properties());
        keyboard = player.getOwnerArea().getKeyboard();
    }

    /**
     * ???
     * @param deltaTime
     */
    @Override
    public void update(float deltaTime){
        super.update(deltaTime);
        selectionMenu = new ICMonFightActionSelectionGraphics(CAMERA_SCALE_FACTOR, keyboard, player.properties().actions());
        Button space = keyboard.get(Keyboard.SPACE);
        switch(state){
            case INTRODUCTION:
                if(space.isDown()) state = FightState.ACTIONSELECTION;
                break;
            case ACTIONSELECTION:
                selectionMenu.update(deltaTime);
                if(selectionMenu.choice() != null){
                    action = selectionMenu.choice();
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

    /**
     * ???
     * @param c (Canvas)
     */
    @Override
    public void drawMenu(Canvas c) {
        if(state == FightState.ACTIONSELECTION){
            arena.setInteractionGraphics(selectionMenu);
        }
        else arena.setInteractionGraphics(new ICMonFightTextGraphics(CAMERA_SCALE_FACTOR, text));
        arena.draw(c);
    }

    private enum FightState {
        INTRODUCTION(0, "Welcome to the fight"),
        ACTIONSELECTION(1, null),
        OPPONENTACTION(1, null),
        ACTIONEXECUTION(3, null),
        CONCLUSION(4, "Good fight !");

        final int type;
        String text;

        private FightState(int type, String text){
            this.type = type;
            this.text = text;
        }

        private void setText(String text) {
            this.text = text;
        }

        private String getText() {
            return text;
        }

    }


    
}
