# Sokoban
This is my clon of [popular game](https://en.wikipedia.org/wiki/Sokoban) written in *Java*. The goal for a player is to push boxes in a such way to get them into predetermined locations.

Game allows you to load maps in a [text format](http://www.sokobano.de/wiki/index.php?title=Level_format) pretty standard for Sokoban clones. You can also create and save your own map, because there is builtin map editor as well.

Developed using Java 9 JDK, but it should work with Java 8 JRE (or better).

This is my learning project where I tackled creating graphical interface with *JavaFx* (and *FXML*). I also created some unit tests using *JUnit*. They are not perfect but I struggled with testing something thats is mostly depending on GUI. However I tried my best to test things not related to graphics. All files are fully documented with *Javadoc* comments.

One thing that could be done better is lacking separation between logic and graphics. I'm aware that this isn't best practice but I decided to [keep it simple](https://en.wikipedia.org/wiki/KISS_principle). Better separation would require bit of work and some more code. In my opinion in that case it wouldn't be worth it.

This project will not be continued because I have achieved all the intended goals for it.
