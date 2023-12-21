conception conforme à l'énoncé
ajout de private List<Actor> registeredActors = new ArrayList<Actor>();
à la classe Area car sinon lance exception "java.lang.NullPointerException: Cannot invoke 
"java.util.List.add(Object)" because "this.registeredActors" is null"

Change in method moveIfPressed in ICMonPlayer so that it returns a boolean to use it to know if there 
is movement

Added Method spawnActor to make spawn of actors like ball easier

Added method addBall to ICMonPlayer to call it from CollectItemEvent