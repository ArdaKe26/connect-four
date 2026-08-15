# Connect Four

A local two-player Connect Four game with a Swing GUI, built in Java. Supports custom board layouts with pre-placed obstacle tiles in addition to the standard empty board.

## Requirements

- JDK 21+
- Maven

## Build & run

```
mvn package
java -jar target/connect-four-1.0-SNAPSHOT.jar
```

Or, during development:

```
mvn compile exec:java -Dexec.mainClass=io.github.ardake26.connectfour.Main
```

## Run tests

```
mvn test
```

## How to play

1. On launch, pick a map (0-4) in the dialog — `0` is an empty board, `1`-`4` have pre-placed obstacle tiles.
2. Click a column to drop your token. Players alternate turns (red = Player 1, yellow = Player 2, green = obstacle).
3. Four in a row — horizontally, vertically, or diagonally — wins. A full board with no winner is a draw.
4. On win or draw, you'll be offered a rematch on a fresh board with the same map.

## Project structure

```
src/main/java/io/github/ardake26/connectfour/
├── Main.java           # entry point
├── GameController.java # turn/player/board coordination, restart
├── GameDisplay.java     # Swing UI
├── Board.java           # grid state, move validation, win/draw detection
├── Cell.java             # single board slot
├── Player.java          # player name + symbol
├── Constants.java       # board/display config
└── MapDesign.java       # obstacle layouts

src/test/java/io/github/ardake26/connectfour/
└── BoardTest.java
```

## Roadmap

See [TODO.md](TODO.md) for planned features.
