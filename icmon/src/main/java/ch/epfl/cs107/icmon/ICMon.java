package ch.epfl.cs107.icmon;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import ch.epfl.cs107.icmon.actor.ICMonPlayer;
import ch.epfl.cs107.icmon.actor.items.ICBall;
import ch.epfl.cs107.icmon.actor.npc.ICShopAssistant;
import ch.epfl.cs107.icmon.area.ICMonArea;
import ch.epfl.cs107.icmon.area.maps.Arena;
import ch.epfl.cs107.icmon.area.maps.Lab;
import ch.epfl.cs107.icmon.area.maps.Town;
import ch.epfl.cs107.icmon.gamelogic.actions.LogAction;
import ch.epfl.cs107.icmon.gamelogic.actions.RegisterEventAction;
import ch.epfl.cs107.icmon.gamelogic.actions.RegisterInAreaAction;
import ch.epfl.cs107.icmon.gamelogic.actions.StartEventAction;
import ch.epfl.cs107.icmon.gamelogic.actions.UnRegisterEventAction;
import ch.epfl.cs107.icmon.gamelogic.events.CollectItemEvent;
import ch.epfl.cs107.icmon.gamelogic.events.EndOfGameEvent;
import ch.epfl.cs107.icmon.gamelogic.events.ICMonEvent;
import ch.epfl.cs107.icmon.gamelogic.events.PokemonFightEvent;
import ch.epfl.cs107.icmon.gamelogic.messages.GamePlayMessage;
import ch.epfl.cs107.icmon.gamelogic.messages.SuspendWithEvent;
import ch.epfl.cs107.play.areagame.AreaGame;
import ch.epfl.cs107.play.areagame.actor.Interactable;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.window.Window;

public class ICMon extends AreaGame {

    /** ??? */
    public final static float CAMERA_SCALE_FACTOR = 20.f;
    /** ??? */
    private final String[] areas = {"town","town"};
    /** ??? */
    private ICMonPlayer player;
    /** ??? */
    private int areaIndex;
    /** ??? */
    private ICBall ball;
    /** ??? */
    private List<ICMonEvent> events = new ArrayList<ICMonEvent>();
    /** ??? */
    private List<ICMonEvent> activeEvents = new ArrayList<ICMonEvent>();
    /** ??? */
    private List<ICMonEvent> completedEvents = new ArrayList<ICMonEvent>();
    /** ??? */
    private final ICMonGameState state = new ICMonGameState();
    /** ??? */
    private final ICMonEventManager EVENT_MANAGER = new ICMonEventManager();
    /** ??? */
    private List<GamePlayMessage> mailBox = new LinkedList<>();

    /**
     * ???
     */
    private void createAreas(){
        addArea(new Town());
        addArea(new Lab());
        addArea(new Arena());
    }

    /**
     * ???
     * @param window (Window): display context. Not null
     * @param fileSystem (FileSystem): given file system. Not null
     * @return ???
     */
    @Override
    public boolean begin(Window window, FileSystem fileSystem){
        if(super.begin(window, fileSystem)){
            createAreas();
            areaIndex = 0;
            initArea(areas[areaIndex]);
            ball = new ICBall(getCurrentArea(), ((ICMonArea)getCurrentArea()).getBallSpawnPosition(), "items/icball");
            ICMonEvent ballCollection = new CollectItemEvent(ball, player);
            ballCollection.onStart(new RegisterEventAction(EVENT_MANAGER, ballCollection));
            ballCollection.onStart(new LogAction("CollectItemEvent started !"));
            ballCollection.onStart(new RegisterInAreaAction(ball, getCurrentArea()));
            ballCollection.onComplete(new LogAction("CollectItemEvent completed !"));
            ballCollection.onComplete(new UnRegisterEventAction(EVENT_MANAGER, ballCollection));
            events.add(ballCollection);
            ICShopAssistant assistant = new ICShopAssistant(getCurrentArea(), Orientation.DOWN, new DiscreteCoordinates(8, 8));
            ICMonEvent end = new EndOfGameEvent(player, assistant);
            end.onStart(new RegisterEventAction(EVENT_MANAGER, end));
            end.onStart(new LogAction("The second event has started !"));
            end.onComplete(new UnRegisterEventAction(EVENT_MANAGER, end));
            events.add(end);
            ballCollection.onComplete(new StartEventAction(end));
            ballCollection.start();
            return true;
        }
        return false;
    }

    /**
     * ???
     * @param deltaTime elapsed time since last update, in seconds, non-negative
     */
    @Override
    public void update(float deltaTime){
        super.update(deltaTime);

        Keyboard keyboard = getCurrentArea().getKeyboard();
        if(keyboard.get(Keyboard.R).isDown()) reset(getWindow(), getFileSystem());

        for(ICMonEvent event : events){
            event.update(deltaTime);

            if(event instanceof PokemonFightEvent){
                if(event.isCompleted()){
                    requestResume();
                } else if(event.isStarted()){
                    setPauseMenu(((PokemonFightEvent) event).getFight());
                    requestPause();
                }
            }
        }

        if(mailBox.size() > 0) {
            GamePlayMessage message = mailBox.get(0);
            if(message instanceof SuspendWithEvent){
                if(((SuspendWithEvent)message).hasPauseMenu()){
                    PokemonFightEvent event = ((SuspendWithEvent)message).getEvent();
                    events.add(event);
                    event.onStart(new RegisterEventAction(EVENT_MANAGER, event));
                    event.onComplete(new UnRegisterEventAction(EVENT_MANAGER, event));
                }
            }
            mailBox.get(0).process(player);
            mailBox.clear();
        }
    }

    /**
     * ???
     */
    @Override
    public void end(){

    }

    /**
     * ???
     * @return ???
     */
    @Override
    public String getTitle(){ return "ICMon";}

    /**
     * ???
     * @param areaKey ???
     */
    private void initArea(String areaKey) {
        ICMonArea area = (ICMonArea) setCurrentArea(areaKey, true);
        DiscreteCoordinates coords = area.getPlayerSpawnPosition();
        player = new ICMonPlayer(area, Orientation.DOWN, coords, "actors/player", state);
        player.enterArea(area, coords);
        player.centerCamera();
    }

    /**
     * ???
     * @param window (Window): Display context. Not null
     * @param fileSystem (FileSystem): Given File System. Not null
     */
    private void reset(Window window, FileSystem fileSystem){
        begin(window, fileSystem);

    }

    public class ICMonGameState {

        private ICMonGameState(){}

        public void acceptInteraction(Interactable interactable , boolean isCellInteraction){
            for(var event : ICMon.this.events)
            interactable.acceptInteraction(event , isCellInteraction);
        }

        public void send(GamePlayMessage message){
            ICMon.this.mailBox.add(message);
        }

        public Area setCurrentArea(String areaKey, boolean forceBegin){
            return ICMon.this.setCurrentArea(areaKey, forceBegin);
        }
    }

    public final class ICMonEventManager {

        private ICMonEventManager(){}

        /**
         * ???
         * @param event
         */
        public void activateEvent(ICMonEvent event){
            ICMon.this.activeEvents.add(event);
        }

        /**
         * ???
         * @param event
         */
        public void deActivateEvent(ICMonEvent event){
            ICMon.this.activeEvents.remove(event);
        }

        /**
         * ???a
         * @param event
         */
        public void completeEvent(ICMonEvent event){
            ICMon.this.completedEvents.add(event);
            ICMon.this.activeEvents.remove(event);
            ICMon.this.events.remove(event);
        }
    }
}
