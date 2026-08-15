package io.github.ardake26.connectfour;

public class GameController {
    private Board board;
    private final Player playerOne;
    private final Player playerTwo;
    private Player currentPlayer;
    private int selectedMapIndex = 0;

    public GameController() {
        this.playerOne = new Player("Player 1", Constants.BoardConstants.PLAYER_ONE_SYMBOL);
        this.playerTwo = new Player("Player 2", Constants.BoardConstants.PLAYER_TWO_SYMBOL);
        this.currentPlayer = playerOne;
        this.board = new Board(Constants.BoardConstants.NUMBER_OF_ROWS,
                Constants.BoardConstants.NUMBER_OF_COLUMNS);
    }

    /**
     * Loads a map design onto the current board
     *
     * @param mapIndex the map design to load (0-4)
     */
    public void loadMap(int mapIndex) {
        this.selectedMapIndex = mapIndex;
        board.loadCustomMap(mapIndex);
    }

    public Board getBoard() {
        return board;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * This function makes the current player change after a person makes their move
     */
    public void switchPlayer() {
        currentPlayer = (currentPlayer == playerOne) ? playerTwo : playerOne;
    }

    /**
     * Starts a fresh game on a new board, reloading the previously selected map
     */
    public void reset() {
        this.board = new Board(Constants.BoardConstants.NUMBER_OF_ROWS,
                Constants.BoardConstants.NUMBER_OF_COLUMNS);
        board.loadCustomMap(selectedMapIndex);
        this.currentPlayer = playerOne;
    }
}
