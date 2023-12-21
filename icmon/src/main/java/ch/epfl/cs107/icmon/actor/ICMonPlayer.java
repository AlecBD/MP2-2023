package ch.epfl.cs107.icmon.actor;

import ch.epfl.cs107.icmon.ICMon.ICMonGameState;
import ch.epfl.cs107.icmon.actor.handler.ICMonInteractionVisitor;
import ch.epfl.cs107.icmon.actor.items.ICBall;
import ch.epfl.cs107.icmon.actor.npc.ICShopAssistant;
import ch.epfl.cs107.icmon.area.ICMonbehavior.AllowedWalkingType;
import ch.epfl.cs107.icmon.area.ICMonbehavior.ICMonCell;
import ch.epfl.cs107.icmon.gamelogic.messages.PassDoorMessage;
import ch.epfl.cs107.play.areagame.actor.Interactable;
import ch.epfl.cs107.play.areagame.actor.Interactor;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.engine.actor.OrientedAnimation;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * ???
 */
public final class ICMonPlayer extends ICMonActor implements Interactor{

    /** ??? */
    private final static int ANIMATION_DURATION = 8;

    /** ??? */
    private static OrientedAnimation moveAnimation;
    /** ??? */
    private static String spriteName;

    private AllowedWalkingType walkingType = AllowedWalkingType.FEET;

    /** ??? */
    private boolean moving = false;
    /** ??? */
    private final ICMonPlayerHandler handler = new ICMonPlayerHandler();
    /** ??? */
    private static List<ICBall> balls = new ArrayList<ICBall>();
    /** ??? */
    private final ICMonGameState state;
    /** ??? */
    private boolean inDialog = false;

    /**
     * ???
     * @param owner ???
     * @param orientation ???
     * @param coordinates ???
     * @param spriteName ???
     */
    public ICMonPlayer(Area owner, Orientation orientation, DiscreteCoordinates coordinates, String spriteName, ICMonGameState state) {
        super(owner, orientation, coordinates);
        this.state = state;
        this.spriteName = spriteName;
        moveAnimation = new OrientedAnimation(spriteName , ANIMATION_DURATION/2, orientation , this);
        resetMotion();
    }

    /**
     * ???
     * @param deltaTime elapsed time since last update, in seconds, non-negative
     */
    @Override
    public void update(float deltaTime) {
        Keyboard keyboard = getOwnerArea().getKeyboard();
        if(!inDialog){
            moveIfPressed(Orientation.LEFT, keyboard.get(Keyboard.LEFT));
            moveIfPressed(Orientation.UP, keyboard.get(Keyboard.UP));
            moveIfPressed(Orientation.RIGHT, keyboard.get(Keyboard.RIGHT));
            moveIfPressed(Orientation.DOWN, keyboard.get(Keyboard.DOWN));
            if(!keyboard.get(Keyboard.LEFT).isDown() && !keyboard.get(Keyboard.RIGHT).isDown()
            && !keyboard.get(Keyboard.UP).isDown() && !keyboard.get(Keyboard.DOWN).isDown()) {moveAnimation.reset();}
            if(moving){
                moveAnimation.update(deltaTime);
            }
        } else{
            if(keyboard.get(Keyboard.SPACE).isPressed()){
                dialog.update(deltaTime);
                if(dialog.isCompleted()){
                    inDialog = false;
                }
            }
        };
        super.update(deltaTime);
    }

    /**
     * ???
     * @param canvas target, not null
     */
    @Override
    public void draw(Canvas canvas) {
        moveAnimation.draw(canvas);
        if(inDialog){
            dialog.draw(canvas);
        }
    }

    /**
     * ???
     * @return ???
     */
    @Override
    public boolean takeCellSpace() {
        return true;
    }

    /**
     * ???
     * @return ???
     */
    @Override
    public boolean isCellInteractable() {
        return true;
    }

    /**
     * ???
     * @return ???
     */
    @Override
    public boolean isViewInteractable() {
        return !inDialog;
    }

    /**
     * ???
     */
    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        return Collections.singletonList(getCurrentMainCellCoordinates());
    }

    /**
     * 
     * @return ???
     */
    @Override
    public List<DiscreteCoordinates> getFieldOfViewCells(){
        return Collections.singletonList(getCurrentMainCellCoordinates().jump(getOrientation().toVector()));
    }

    /**
     * ???
     * @param v (AreaInteractionVisitor) : the visitor
     * @param isCellInteraction ???
     */
    @Override
    public void acceptInteraction(AreaInteractionVisitor v, boolean isCellInteraction) {
        ((ICMonInteractionVisitor) v).interactWith(this , isCellInteraction);
    }

    /**
     * Orientate and Move this player in the given orientation if the given button is down
     *
     * @param orientation (Orientation): given orientation, not null
     * @param b           (Button): button corresponding to the given orientation, not null
     */
    private void moveIfPressed(Orientation orientation, Button b) {
        if (b.isDown() && !inDialog) {
            if (!isDisplacementOccurs()) {
                orientate(orientation);
                moveAnimation.orientate(orientation);
                move(ANIMATION_DURATION);
                moving = true;
            }
        }
    }

    /**
     * Center the camera on the player
     */
    public void centerCamera() {
        getOwnerArea().setViewCandidate(this);
    }

    /**
     * 
     * @return
     */
    @Override
    public boolean wantsCellInteraction(){
        return true;
    }

    /**
     * 
     * @return
     */
    @Override
    public boolean wantsViewInteraction(){
        Keyboard keyboard = getOwnerArea().getKeyboard();
        return (keyboard.get(Keyboard.L).isDown() == true) ? true : false;
    }

    /**
     * ???
     * @return ???
     */
    public AllowedWalkingType getWalkingtype(){
        return walkingType;
    }

    /**
     * ???
     * @param other ???
     * @param isCellInteraction ???
     */
    @Override
    public void interactWith(Interactable other , boolean isCellInteraction) {
        other.acceptInteraction(handler , isCellInteraction);
    }

    /**
     * ???
     */
    public void openDialog(){
        inDialog = true;
    }

    /**
     * ???
     */
    public void closeDialog(){
        inDialog= false;
    }

    public void addBall(ICBall ball){
        balls.add(ball);
    }
        

    private class ICMonPlayerHandler implements ICMonInteractionVisitor {

        /**
         * ???
         * @param cell ???
         * @param isCellInteraction ???
         */
        @Override
        public void interactWith(ICMonCell cell, boolean isCellInteraction){
            if(isCellInteraction){
                if(cell.getWalkingType() == AllowedWalkingType.FEET && spriteName.equals("actors/player_water")){
                    spriteName = "actors/player";
                    ICMonPlayer.moveAnimation = new OrientedAnimation("actors/player", ICMonPlayer.this.ANIMATION_DURATION / 2, getOrientation(), ICMonPlayer.this);
                } else if(cell.getWalkingType() == AllowedWalkingType.SURF  && spriteName.equals("actors/player")){
                    spriteName = "actors/player_water";
                    ICMonPlayer.moveAnimation = new OrientedAnimation("actors/player_water", ICMonPlayer.this.ANIMATION_DURATION / 2, getOrientation(), ICMonPlayer.this);
                }
            }
        }

        /**
         * ???
         * @param player ???
         * @param isCellInteraction ???
         */
        @Override
        public void interactWith(ICMonPlayer player, boolean isCellInteraction) {
        }

        /**
         * ???
         * @param ball ???
         * @param isCellInteraction ???
         */
        @Override
        public void interactWith(ICBall ball, boolean isCellInteraction) {
            if(!isCellInteraction && getOwnerArea().getKeyboard().get(Keyboard.L).isDown() && !ICMonPlayer.this.inDialog){
                ICMonPlayer.this.state.acceptInteraction(ball, isCellInteraction);
            }
            
        }

        /**
         * ???
         * @param player ???
         * @param isCellInteraction ???
         */
        @Override
        public void interactWith(ICShopAssistant assistant, boolean isCellInteraction) {
            if(!isCellInteraction && getOwnerArea().getKeyboard().get(Keyboard.L).isDown() && !ICMonPlayer.this.inDialog)
            ICMonPlayer.this.state.acceptInteraction(assistant, isCellInteraction);
        }

        @Override
        public void interactWith(Door door, boolean isCellInteraction) {
            if (isCellInteraction){
                PassDoorMessage message = new PassDoorMessage(door, state);
                state.send(message);
            }
        }

        /* @Override
        public void interactWith(Pokemon pokemon, boolean isCellInteraction) {
            if (isCellInteraction){
                PokemonFightEvent event = new PokemonFightEvent(state.getWindow());
                event.onComplete(new LeaveAreaAction(pokemon));
                pokemon.fight(pokemon);
                SuspendWithEvent message = new SuspendWithEvent(event);
                state.send(message);
            }
        } */



    }

}