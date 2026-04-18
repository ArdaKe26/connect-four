import java.util.Scanner;

public class GameManager {
    private final Board board = Container.BoardContainer.board;
    //private GameDisplay display = new GameDisplay(this);
    private Scanner scanner;
    private String currentSymbol;

    public GameManager() {
        this.scanner = new Scanner(System.in);
        this.currentSymbol = Constants.BoardConstants.PLAYER_ONE_SYMBOL;

        System.out.println("Please select the map you want to play in(0-4)");
        int map = scanner.nextInt();

        Board.whichMapSelected = map;

        board.loadCustomMap();
    }

    /**
     * This function is the base of the game and makes the game run
     */
    public void startGame() {
        boolean isGameOver = false;

        while (!isGameOver) {
            board.printBoard();
            System.out.println(currentSymbol + "s turn now. Select a column(1-7)");
            int col = scanner.nextInt();

            if (col <= 7 && col >= 1) {
                if (board.dropToken(col - 1, currentSymbol)) {
                    if (board.checkWin(currentSymbol)) {
                        board.printBoard();
                        System.out.println("Congrats! " + currentSymbol + " player have won");
                        isGameOver = true;
                    } else if(board.isBoardFull()) {
                        board.printBoard();
                        System.out.println("The game was a draw due to board being full");
                        isGameOver = true;
                    }
                    else {
                        switchPlayer();
                    }
                } else {
                    System.out.println("That column is full");
                }
            } else {
                System.out.println("Please enter valid column number");
            }
        }
    }

    /**
     * This function makes the current player change after a person makes their move
     */
    public void switchPlayer() {
        if (currentSymbol.equals("X")) {
            currentSymbol = "O";
        } else {
            currentSymbol = "X";
        }
    }

    public String getCurrentSymbol() {
        return currentSymbol;
    }
}
