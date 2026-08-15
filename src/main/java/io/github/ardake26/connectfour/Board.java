package io.github.ardake26.connectfour;

public class Board {
    public Cell[][] grid;

    /**
     * This is the constructor of the object which sets rows and coloumns of the
     * board
     * 
     * @param rows    takes the amount of rows there will be in the board
     * @param columns takes the amount of columns there will be in the board
     */
    public Board(int rows, int columns) {
        this.grid = new Cell[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                this.grid[i][j] = new Cell();
            }
        }
    }

    /**
     * This function is used to print the board to the terminal(Debugging phase
     * usage)
     */
    public void printBoard() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                System.out.print(grid[i][j].getSymbol() + " ");
            }
            System.out.println();
        }
    }

    /**
     * This function is used to change the symbol of the tile when a token is
     * dropped
     * 
     * @param column takes which columnn that tile is dropped in
     * @param symbol takes which symbol is placed in that cell
     * @return returns true if that column has space for a token to be dropped
     */
    public boolean dropToken(int column, String symbol) {
        for (int i = grid.length - 1; i >= 0; i--) {
            if (grid[i][column].getSymbol().equals(".")) {
                grid[i][column].setSymbol(symbol);

                return true;
            }
        }

        return false;
    }

    /**
     * This function checks if a player has won the game after they placed their
     * token
     * 
     * @param symbol takes the symbol to be able to determine if that symbol has won
     *               the game
     * @return returns true if a player has won the game
     */
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

    /**
     * This function checks if the board is full and game is a draw
     * @param row
     * @param column
     */
    public boolean isBoardFull(){
        for (int i = 0; i < grid[0].length; i++){
            if (grid[0][i].getSymbol().equals(".")){
                return false;
            }
        }

        return true;
    }

    /**
     * This function is used to place the obstacle(wall) prop to the game board
     * 
     * @param row    takes to be able to place tha wall prop in the correct row
     * @param column takes to be able to place tha wall prop in the correct column
     */
    public void placeObstacle(int row, int column) {
        if (row >= 0 && row < grid.length && column < grid[0].length && column >= 0) {
            if (row > 0) {
                grid[row][column].setAsObstacle();
            }
        }
    }

    /**
     * This function is used to be able to change the map designs from match to
     * match
     *
     * @param mapIndex takes which map design to load (0-4)
     */
    public void loadCustomMap(int mapIndex) {
        String[] selectedMapDesign = MapDesign.emptyMapDesign;

        switch (mapIndex) {
            case 0 -> {
                selectedMapDesign = MapDesign.emptyMapDesign;
            }
            case 1 -> {
                selectedMapDesign = MapDesign.mapDesign1;
            }
            case 2 -> {
                selectedMapDesign = MapDesign.mapDesign2;
            }
            case 3 -> {
                selectedMapDesign = MapDesign.mapDesign3;
            }
            case 4 -> {
                selectedMapDesign = MapDesign.mapDesign4;
            }
            default -> {
                System.out.println("Please select a valid map design");

            }
        }

        for (int i = 0; i < selectedMapDesign.length; i++) {
            for (int j = 0; j < selectedMapDesign[i].length(); j++) {
                char cellChar = selectedMapDesign[i].charAt(j);
                if (cellChar == '#') {
                    placeObstacle(i, j);
                }
            }
        }
    }
}