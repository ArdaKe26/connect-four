public class Board {
    private Cell[][] grid;

    public Board(int rows, int columns) {
        this.grid = new Cell[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                this.grid[i][j] = new Cell();
            }
        }
    }

    public void printBoard() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                System.out.print(grid[i][j].getSymbol() + " ");
            }
            System.out.println();
        }
    }

    public boolean dropToken(int column, String symbol) {
        for (int i = grid.length - 1; i >= 0; i--) {
            if (grid[i][column].getSymbol().equals(".")) {
                grid[i][column].setSymbol(symbol);

                return true;
            }
        }

        return false;
    }

    public boolean checkWin(String symbol) {
        // Horizontal win check
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length - 3; j++) {
                if (grid[i][j].getSymbol().equals(symbol) &&
                        grid[i][j + 1].getSymbol().equals(symbol) &&
                        grid[i][j + 2].getSymbol().equals(symbol) &&
                        grid[i][j + 3].getSymbol().equals(symbol)) {
                    return true;
                }
            }
        }

        // Vertical win check
        for (int i = 0; i < grid.length - 3; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j].getSymbol().equals(symbol) &&
                        grid[i + 1][j].getSymbol().equals(symbol) &&
                        grid[i + 2][j].getSymbol().equals(symbol) &&
                        grid[i + 3][j].getSymbol().equals(symbol)) {
                    return true;
                }
            }
        }

        // Left Diagonal win check
        for (int i = 0; i < grid.length - 3; i++) {
            for (int j = 0; j < grid[0].length - 3; j++) {
                if (grid[i][j].getSymbol().equals(symbol) &&
                        grid[i + 1][j + 1].getSymbol().equals(symbol) &&
                        grid[i + 2][j + 2].getSymbol().equals(symbol) &&
                        grid[i + 3][j + 3].getSymbol().equals(symbol)) {
                    return true;
                }
            }
        }

        // Right Diagonal win check
        for (int i = 3; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length - 3; j++) {
                if (grid[i][j].getSymbol().equals(symbol) &&
                        grid[i - 1][j + 1].getSymbol().equals(symbol) &&
                        grid[i - 2][j + 2].getSymbol().equals(symbol) &&
                        grid[i - 3][j + 3].getSymbol().equals(symbol)) {
                    return true;
                }
            }
        }

        return false;
    }

    public void placeObstacle(int row, int column) {
        if (row >= 0 && row <= grid.length && column < grid[0].length && column >= 0) {
            if (row > 0) {
                grid[row][column].setAsObstacle();
            }
        }
    }

    public void loadCustomMap() {
        String[] mapDesign1 = {
                ".......",
                ".......",
                "...#...",
                "..##...",
                "..###..",
                "#.###.."
        };

        for (int i = 0; i < mapDesign1.length; i++) {
            for (int j = 0; j < mapDesign1[i].length(); j++) {
                char cellChar = mapDesign1[i].charAt(j);
                if (cellChar == '#') {
                    placeObstacle(i, j);
                }
            }
        }
    }
}
