package ch.epfl.cs107.icmon.actor.items;

import ch.epfl.cs107.play.areagame.actor.CollectableAreaEntity;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.engine.actor.RPGSprite;
import ch.epfl.cs107.play.engine.actor.Sprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;

public abstract class ICMonItem extends CollectableAreaEntity {

    //Area where the ICMonItem will exist
    protected Area owner;

    private DiscreteCoordinates position;
    //Reference for image
    protected final Sprite sprite;

    /**
     * ICMonItem Constructor with default value for orientation
     * 
     * @param owner ???
     * @param position ???
     * @param spriteName ???
     */
    public ICMonItem(Area owner, DiscreteCoordinates position, String spriteName){
        this(owner, Orientation.DOWN, position, spriteName);

    }

    /**
     * ICMonItem Constructor with orientation specified 
     * 
     * @param owner ???
     * @param orientation ???
     * @param position ???
     * @param spriteName ???
     */
    public ICMonItem(Area owner, Orientation orientation, DiscreteCoordinates position, String spriteName){
        super(owner, orientation, position);
        this.owner = owner;
        this.position = position;
        sprite = new RPGSprite(spriteName , 1f, 1f, this);

    }

    /**
     * non-transversable
     * @return ???
     */
    @Override
    public boolean takeCellSpace(){
        return true;
    }

    /**
     * ???
     * @return ???
     */
    @Override
     public boolean isCellInteractable(){
        return true;
    }

    /**
     * ???
     * @return ???
     */
    @Override
    public boolean isViewInteractable(){
        return false;
    }
    
}
