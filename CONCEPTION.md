ajout de private List<Actor> registeredActors = new ArrayList<Actor>();
à la classe Area car sinon lance exception "java.lang.NullPointerException: Cannot invoke 
"java.util.List.add(Object)" because "this.registeredActors" is null"

Change in method moveIfPressed in ICMonPlayer so that it returns a boolean to use it to know if there 
is movement

Added method addBall to ICMonPlayer to call it from CollectItemEvent

changed visibility from method setPauseMenu(pauseMenu) to public to use it for action PauseGameAction

changed visibility from window in PauseMenu to protected to use it in ICMonFight

changed visibility from getOwnerArea() in AreaEntity to public to use it in ICMonFight because getKeyboard()
launched error


1. les éventuelles modifications personnelles que vous avez apportées à l'architecture proposée en les justifiant;


2. les classes/interfaces importantes ajoutées et comment elles s'insèrent dans l'architecture;


3. le comportement que vous attribuez à chacun des composants introduits (si le composant n'est pas demandé ou s'il
    l'est mais que son comportement est une petite variante de celui suggéré dans l'énoncé).