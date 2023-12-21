1. Comment lancer le programme

2. Contrôles

    - intéragir à distance:  "L"
    - redémarrer le jeu:     "R"

3. quel est le comportement que vous attribuez à chacun des composants introduits 
    (si le composant n'est pas demandé ou s'il l'est mais que son comportement est une petite variante de 
    celui suggéré dans l'énoncé);

    - line 34 in ICMonPlayer.java gives type of movement from player and its default value is AllowedWalkingType.FEET

    - takeCellSpace(Interactable entity) : says if an entity is of type player and if it can go into the cell or not
    - in ICMonEvent methods start, complete, suspend, resume I used the condition as !a instead of a
    - method executePart in ICMonEvent for better encapsulation
    
    -added method setCurrentArea() to ICMonGameState to use it in method process from PassDoorMessage to switch areas

4. quels sont les éventuels niveaux mis en oeuvre et que faire pour réaliser le scénario de jeu voulu. 
    Nous devons pouvoir tester votre jeu sans avoir à lire votre code (ou deviner les règles ou les 
    niveaux mis en place ;-))