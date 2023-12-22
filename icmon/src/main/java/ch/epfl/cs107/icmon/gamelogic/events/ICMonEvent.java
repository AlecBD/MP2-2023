package ch.epfl.cs107.icmon.gamelogic.events;

import java.util.LinkedList;
import java.util.List;

import ch.epfl.cs107.icmon.actor.handler.ICMonInteractionVisitor;
import ch.epfl.cs107.icmon.gamelogic.actions.Action;
import ch.epfl.cs107.play.engine.Updatable;

public abstract class ICMonEvent implements Updatable, ICMonInteractionVisitor {

    /** ??? */
    private boolean started = false;
    /** ??? */
    private boolean ended = false;
    /** ??? */
    private boolean paused = false;
    /** ??? */
    private boolean resumed = false;
    /** ??? */
    private List<Action> startActions = new LinkedList<Action>();
    /** ??? */
    private List<Action> endActions = new LinkedList<Action>();
    /** ??? */
    private List<Action> pauseActions = new LinkedList<Action>();
    /** ??? */
    private List<Action> resumeActions = new LinkedList<Action>();

    /**
     * ???
     */
    public void start(){
        if(!started){
            executePart(startActions);
            started = true;
        }
    }

    /**
     * ???
     */
    public void complete(){
        if(started && !ended){
            executePart(endActions);
            ended = true;
            started = false;
            paused = false;
            resumed = false;
        }
    }

    /**
     * 
     */
    public void suspend(){
        if(!ended && !paused && started){
            executePart(pauseActions);
            paused = true;
            if(resumed) resumed = false;
        }
    }

    /**
     * 
     */
    public void resume(){
        if(!ended && paused && started){
            executePart(resumeActions);
            resumed = true;
            paused = false;
        }
    }

    /**
     * ???
     * @param actions
     */
    private void executePart(List<Action> actions){
        for(Action action : actions){
            action.perform();
        }
    }

    /**
     * 
     * @param action ???
     */
    public void onStart(Action action){
        startActions.add(action);
    }

    /**
     * 
     * @param action ???
     */
    public void onComplete(Action action){
        endActions.add(action);
    }

    /**
     * 
     * @param action ???
     */
    public void onSuspension(Action action){
        pauseActions.add(action);
    }

    /**
     * 
     * @param action ???
     */
    public void onResume(Action action){
        resumeActions.add(action);
    }

    /**
     * ???
     * @return ???
     */
    public boolean isStarted(){return started;}

    /**
     * ???
     * @return ???
     */
    public boolean isCompleted(){return ended;}
    
    /**
     * ???
     * @return ???
     */
    public boolean isPaused(){return paused;}
    
    /**
     * ???
     * @return ???
     */
    public boolean isResumed(){return resumed;}

    public boolean pausesGame(){
        return false;
    }
}
