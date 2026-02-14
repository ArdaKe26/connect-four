import java.util.Scanner;

public class GameManager {
    private Board board = Container.BoardContainer.board;
    private Scanner scanner;
    private String currentSymbol;

    public GameManager() {
        this.scanner = new Scanner(System.in);
        this.currentSymbol = Constants.BoardConstants.PLAYER_ONE_SYMBOL;

        board.loadCustomMap();
    }

    public void startGame() {
        boolean isGameOver = false;

        while (!isGameOver){
            board.printBoard();
            System.out.println(currentSymbol + "s turn now. Select a column(1-7)");
            int col = scanner.nextInt();

            if(col <= 7 && col >= 1) {
                if (board.dropToken(col - 1, currentSymbol)) {
                    if(board.checkWin(currentSymbol)) {
                        board.printBoard();
                        System.out.println("Congrats! " + currentSymbol + " player have won");
                        isGameOver = true;
                    }else {
                        switchPlayer();
                    }
                }
                else {
                    System.out.println("That column is full");
                }   
            } else {
                System.out.println("Please enter valid column number");
            }
        }
    }

    private void switchPlayer(){
        if (currentSymbol.equals("X")) {
            currentSymbol = "O";
        } else {
            currentSymbol = "X";
        }
    }
}
