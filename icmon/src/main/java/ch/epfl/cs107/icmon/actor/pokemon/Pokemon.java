package ch.epfl.cs107.icmon.actor.pokemon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.icmon.actor.ICMonActor;
import ch.epfl.cs107.icmon.actor.ICMonFightableActor;
import ch.epfl.cs107.icmon.gamelogic.fights.ICMonFightAction;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.engine.actor.RPGSprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;
import ch.epfl.cs107.play.window.Canvas;

/**
 * ???
 *
 * @author Hamza REMMAL (hamza.remmal@epfl.ch)
 */
public abstract class Pokemon extends ICMonActor implements  ICMonFightableActor {

    /** ??? */
    private String name;
    /** ??? */
    private int hp;
    /** ??? */
    private final int maxHp;
    /** ??? */
    private int damage;
    /** ??? */
    private boolean alive = true;
    /** ??? */
    protected List<ICMonFightAction> actions = new ArrayList<>();

    /**
     * Pokemon Constructor
     * 
     * @param owner
     * @param orientation
     * @param coordinates
     * @param name
     * @param damage
     * @param maxHp
     */
    public Pokemon(Area owner, Orientation orientation, DiscreteCoordinates coordinates, String name, int damage, int maxHp){
        super(owner, orientation, coordinates);
        this.name = name;
        this.damage = damage;
        this.maxHp = maxHp;
        hp = maxHp;
    }

    /**
     * 
     * @param canvas
     */
    @Override
    public void draw(Canvas canvas){
        new RPGSprite("pokemon/" + name, 1, 1, this).draw(canvas);

    }

    /**
     * ???
     */
    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        return Collections.singletonList(getCurrentMainCellCoordinates());
    }

    /**
     * ???
     * @return ???
     */
    @Override
    public boolean takeCellSpace() {
        return false;
    }

    /**
     * loses hp as receives damage
     * 
     * @param damage
     */
    public void receiveDamage(int damage){
        hp -= damage;
        if(hp <= 0){
            hp = 0;
            alive = false;
        }
    }

    public PokemonProperties properties(){
        return new PokemonProperties();
    }

    /**
     * @author Hamza REMMAL (hamza.remmal@epfl.ch)
     */
    public final class PokemonProperties {

        public String name(){
            return Pokemon.this.name;
        }

        public float hp(){
            return Pokemon.this.hp;
        }

        public float maxHp(){
            return Pokemon.this.maxHp;
        }

        public int damage(){
            return Pokemon.this.damage;
        }

        public boolean isAlive(){
            return Pokemon.this.alive;
        }

        public List<ICMonFightAction> actions() {
            return Pokemon.this.actions;
        }

    }

}