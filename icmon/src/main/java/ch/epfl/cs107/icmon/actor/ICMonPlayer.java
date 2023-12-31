package ch.epfl.cs107.icmon.actor;

import ch.epfl.cs107.icmon.ICMon.ICMonGameState;
import ch.epfl.cs107.icmon.actor.handler.ICMonInteractionVisitor;
import ch.epfl.cs107.icmon.actor.items.ICBall;
import ch.epfl.cs107.icmon.actor.npc.ICShopAssistant;
import ch.epfl.cs107.icmon.actor.pokemon.Bulbizarre;
import ch.epfl.cs107.icmon.actor.pokemon.Latios;
import ch.epfl.cs107.icmon.actor.pokemon.Nidoqueen;
import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;
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
public final class ICMonPlayer extends ICMonActor implements Interactor, ICMonFightableActor {

    /** ??? */
    private final static int ANIMATION_DURATION = 8;
    /** Animation of Movement */
    private static OrientedAnimation moveAnimation;
    /** Used to know if moving by feet or surf */
    private static String spriteName;
    /** Kind of movement (feet or surf) */
    private AllowedWalkingType walkingType = AllowedWalkingType.FEET;
    /** Defines if Player is moving */
    private boolean moving = false;
    /** ??? */
    private final ICMonPlayerHandler handler = new ICMonPlayerHandler();
    /** Collection of Pokeballs */
    private static List<ICBall> balls = new ArrayList<ICBall>();
    /** ??? */
    private final ICMonGameState state;
    /** Defines if Player is currently in a Dialog */
    private boolean inDialog = false;
    /** Collection of Pokemon */
    private ArrayList<Pokemon> pokemon = new ArrayList<>();
    /** Defines if Player is currently fighting */
    private boolean isFighting;

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
        addPokemon(new Bulbizarre(owner, orientation, coordinates));
        addPokemon(new Latios(owner, orientation, coordinates));
        addPokemon(new Nidoqueen(owner, orientation, coordinates));
    }

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

    @Override
    public void draw(Canvas canvas) {
        moveAnimation.draw(canvas);
        if(inDialog){
            dialog.draw(canvas);
        }
    }

    @Override
    public boolean takeCellSpace() {
        return true;
    }

    @Override
    public boolean isCellInteractable() {
        return true;
    }

    @Override
    public boolean isViewInteractable() {
        return !inDialog;
    }

    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        return Collections.singletonList(getCurrentMainCellCoordinates());
    }

    @Override
    public List<DiscreteCoordinates> getFieldOfViewCells(){
        return Collections.singletonList(getCurrentMainCellCoordinates().jump(getOrientation().toVector()));
    }

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

    @Override
    public boolean wantsCellInteraction(){
        return true;
    }

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

    /**
     * 
     * @param ball
     */
    public void addBall(ICBall ball){
        balls.add(ball);
    }

    /**
     * Adds a new Pokemon to the collection of the Player
     * 
     * @param pokemon (Pokemon) Not null
     */
    public void addPokemon(Pokemon pokemon){
        this.pokemon.add(pokemon);
    }
        

    private class ICMonPlayerHandler implements ICMonInteractionVisitor {

        //Redefines kind of movement when entering a Cell that only accepts the other kind of movement
        @Override
        public void interactWith(ICMonCell cell, boolean isCellInteraction){
            if(isCellInteraction){
                if(cell.getWalkingType() == AllowedWalkingType.FEET && spriteName.equals("actors/player_water")){
                    spriteName = "actors/player";
                    ICMonPlayer.moveAnimation = new OrientedAnimation("actors/player", ANIMATION_DURATION / 2, getOrientation(), ICMonPlayer.this);
                } else if(cell.getWalkingType() == AllowedWalkingType.SURF  && spriteName.equals("actors/player")){
                    spriteName = "actors/player_water";
                    ICMonPlayer.moveAnimation = new OrientedAnimation("actors/player_water", ANIMATION_DURATION / 2, getOrientation(), ICMonPlayer.this);
                }
            }
        }

        @Override
        public void interactWith(ICMonPlayer player, boolean isCellInteraction) {
        }

        @Override
        public void interactWith(ICBall ball, boolean isCellInteraction) {
            if(!isCellInteraction && getOwnerArea().getKeyboard().get(Keyboard.L).isDown() && !ICMonPlayer.this.inDialog){
                ICMonPlayer.this.state.acceptInteraction(ball, isCellInteraction);
            }
            
        }

        @Override
        public void interactWith(ICShopAssistant assistant, boolean isCellInteraction) {
            if(!isCellInteraction && getOwnerArea().getKeyboard().get(Keyboard.L).isDown() && !ICMonPlayer.this.inDialog)
            ICMonPlayer.this.state.acceptInteraction(assistant, isCellInteraction);
        }

        @Override
        public void interactWith(Door door, boolean isCellInteraction) {
            if (isCellInteraction){
                PassDoorMessage message = new PassDoorMessage(door, state, ICMonPlayer.this);
                state.send(message);
            }
        }

        @Override
        public void interactWith(Pokemon pokemon, boolean isCellInteraction) {
            if (isCellInteraction && !ICMonPlayer.this.isFighting){
                fight(ICMonPlayer.this.pokemon.get(0), pokemon, state);
            }
        }
    }

}