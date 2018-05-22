# JavaShipGame


## Just A ship game in Java



### Improvments
* A larger grid.

* A search for treasure with a “Win” notification if CC finds the treasure before being caught by
the pirates and/or sea monsters.

* Sea monsters swimming around one (or more) areas of the ocean.

* Improved algorithm for moving the pirate ship. Create multiple strategies for pirate ships to
move. Try to make them smarter than the current “chase” strategies.

* JUnit tests for two classes.

* Use four design patterns


### Design Patterns

* Observer: This is already in use for the Pirate ships as they observe Christopher Columbus.

* Singleton: You can create the map grid as a singleton. Advantage – you can get access to the
map grid from any class without having to pass the object around! (Will be covered in class on
Week 8).

* Factory Method: Use the factory method to create pirate ships. You could have two types of
Pirate ship. They could have different images, different origins etc. Remember the factory
method pattern (as described in class on week 7) must have an abstract creation method which
defers object creation to concrete subclasses. However, the superclass defines other shared
methods which act on the created object.

* Strategy: You could create more than one strategy for how the pirate ships move around the
map. Perhaps some of them chase Christopher Columbus (using the observer pattern), while
others just patrol the waters in different ways.

* Decorator: You could use the decorator to add special protective powers to Christopher
Columbus ship. You could use it to add functionality to the whirlpool.

* Composite: You could quite easily use the composite to create groupings of monsters/sharks.
The composite node would define the boundaries in which they could swim. Individual animals
are added to a ‘container’. All containers are added to the game (or ocean). You can then stop
or start the animals. Whirlpools could be also added as leaf nodes to the ocean. When CC’s ship
moves you can find out which container (if any) it is currently in.
