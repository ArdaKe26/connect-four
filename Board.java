public class Board {
    private Cell[][] grid;

    public Board(int rows, int columns){
        this.grid = new Cell[rows][columns];

        for (int i = 0; i < rows ; i++) {
            for (int j = 0; j < columns; j++) {
                this.grid[i][j] = new Cell();
            }
        }
    }

    public void printBoard() {
        for (int i = 0; i < grid.length ; i++) {
            for (int j = 0; j < grid[0].length ; j++) {
                System.out.print(grid[i][j].getSymbol() + " ");
            }
            System.out.println();
        }
    }

    public boolean dropToken(int column, String symbol) {
        for(int i = grid.length - 1; i >= 0; i--) {
            if (grid[i][column].getSymbol().equals(".")) {
                grid[i][column].setSymbol(symbol);

                return true;
            }
        }

        return false;
    }
}
