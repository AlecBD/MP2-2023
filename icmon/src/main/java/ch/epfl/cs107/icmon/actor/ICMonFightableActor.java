package ch.epfl.cs107.icmon.actor;

public interface ICMonFightableActor {

    public default void fight(ICMonFightableActor enemy){}
    
}
