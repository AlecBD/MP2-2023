package ch.epfl.cs107.icmon.actor.pokemon;

import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.icmon.actor.ICMonActor;
import ch.epfl.cs107.icmon.actor.ICMonFightableActor;
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
public abstract class Pokemon extends ICMonActor implements ICMonFightableActor {

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

    public Pokemon(Area owner, Orientation orientation, DiscreteCoordinates coordinates, String name, int damage, int maxHp){
        super(owner, orientation, coordinates);
        this.name = name;
        this.damage = damage;
        this.maxHp = maxHp;
        hp = maxHp;
    }

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

    public void receiveDamage(int damage){
        hp -= damage;
        if(hp <= 0){
            hp = 0;
            alive = false;
        }
    }

    /**
     * @author Hamza REMMAL (hamza.remmal@epfl.ch)
     */
    public final class PokemonProperties {

        public String name(){
            return null;
        }

        public float hp(){
            return 0f;
        }

        public float maxHp(){
            return 0f;
        }

        public int damage(){
            return 0;
        }

    }

}