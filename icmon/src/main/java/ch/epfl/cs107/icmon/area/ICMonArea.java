package ch.epfl.cs107.icmon.area;

import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;

/**
 * ???
 */
public abstract class ICMonArea extends Area {

    /**
     * ???
     */
    protected abstract void createArea();

    /**
     * ???
     * @return ???
     */
    public abstract DiscreteCoordinates getPlayerSpawnPosition();

    /**
     * ???
     * @return ???
     */
    public abstract DiscreteCoordinates getBallSpawnPosition();

    @Override
    public boolean begin(Window window, FileSystem fileSystem){
        if (super.begin(window, fileSystem)) {
            setBehavior(new ICMonbehavior(window, getTitle()));
            createArea();
            return true;
        }
        return false;}

    @Override
    public final float getCameraScaleFactor(){return ICMon.CAMERA_SCALE_FACTOR;}
    
}
