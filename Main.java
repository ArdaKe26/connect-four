public class Main {
    public static void main(String[] args) {
        Board board = Container.BoardContainer.board;
    
        System.out.println();
        board.dropToken(5, "x");
        board.printBoard();
        
    }
}
