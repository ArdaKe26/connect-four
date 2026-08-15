package io.github.ardake26.connectfour;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {

    private static final int ROWS = 6;
    private static final int COLUMNS = 7;

    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board(ROWS, COLUMNS);
    }

    @Test
    void constructorCreatesGridOfCorrectDimensions() {
        assertEquals(ROWS, board.grid.length);
        assertEquals(COLUMNS, board.grid[0].length);
    }

    @Test
    void constructorFillsGridWithEmptyNonObstacleCells() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                assertEquals(".", board.grid[i][j].getSymbol());
                assertFalse(board.grid[i][j].isObstacle());
            }
        }
    }

    @Test
    void dropTokenIntoEmptyColumnLandsOnBottomRow() {
        assertTrue(board.dropToken(3, "X"));
        assertEquals("X", board.grid[ROWS - 1][3].getSymbol());
    }

    @Test
    void dropTokenStacksOnTopOfPreviousToken() {
        board.dropToken(2, "X");
        board.dropToken(2, "O");

        assertEquals("X", board.grid[ROWS - 1][2].getSymbol());
        assertEquals("O", board.grid[ROWS - 2][2].getSymbol());
    }

    @Test
    void dropTokenIntoFullColumnReturnsFalse() {
        for (int i = 0; i < ROWS; i++) {
            assertTrue(board.dropToken(0, "X"));
        }

        assertFalse(board.dropToken(0, "X"));
    }

    @Test
    void checkWinDetectsHorizontalWin() {
        for (int j = 0; j < 4; j++) {
            board.grid[5][j].setSymbol("X");
        }

        assertTrue(board.checkWin("X"));
    }

    @Test
    void checkWinDetectsVerticalWin() {
        for (int i = 2; i <= 5; i++) {
            board.grid[i][0].setSymbol("O");
        }

        assertTrue(board.checkWin("O"));
    }

    @Test
    void checkWinDetectsDownRightDiagonalWin() {
        board.grid[0][0].setSymbol("X");
        board.grid[1][1].setSymbol("X");
        board.grid[2][2].setSymbol("X");
        board.grid[3][3].setSymbol("X");

        assertTrue(board.checkWin("X"));
    }

    @Test
    void checkWinDetectsUpRightDiagonalWin() {
        board.grid[3][0].setSymbol("O");
        board.grid[2][1].setSymbol("O");
        board.grid[1][2].setSymbol("O");
        board.grid[0][3].setSymbol("O");

        assertTrue(board.checkWin("O"));
    }

    @Test
    void checkWinReturnsFalseForThreeInARow() {
        for (int j = 0; j < 3; j++) {
            board.grid[5][j].setSymbol("X");
        }

        assertFalse(board.checkWin("X"));
    }

    @Test
    void checkWinNotTriggeredByObstacleRowForPlayerSymbols() {
        board.grid[5][0].setAsObstacle();
        board.grid[5][1].setAsObstacle();
        board.grid[5][2].setAsObstacle();
        board.grid[5][3].setAsObstacle();

        assertFalse(board.checkWin("X"));
        assertFalse(board.checkWin("O"));
    }

    @Test
    void isBoardFullReturnsFalseOnEmptyBoard() {
        assertFalse(board.isBoardFull());
    }

    @Test
    void isBoardFullReturnsFalseWhenTopRowPartiallyFilled() {
        for (int i = 0; i < ROWS; i++) {
            board.dropToken(0, "X");
        }

        assertFalse(board.isBoardFull());
    }

    @Test
    void isBoardFullReturnsTrueWhenTopRowFullyFilled() {
        for (int col = 0; col < COLUMNS; col++) {
            for (int i = 0; i < ROWS; i++) {
                board.dropToken(col, "X");
            }
        }

        assertTrue(board.isBoardFull());
    }

    @Test
    void placeObstacleMarksCellAsObstacle() {
        board.placeObstacle(3, 2);

        assertTrue(board.grid[3][2].isObstacle());
        assertEquals("#", board.grid[3][2].getSymbol());
    }

    @Test
    void placeObstacleNeverAffectsTopRow() {
        board.placeObstacle(0, 2);

        assertFalse(board.grid[0][2].isObstacle());
        assertEquals(".", board.grid[0][2].getSymbol());
    }

    @Test
    void placeObstacleWithOutOfBoundsColumnIsNoOp() {
        assertDoesNotThrow(() -> board.placeObstacle(2, COLUMNS));
        assertDoesNotThrow(() -> board.placeObstacle(2, -1));
    }

    @Test
    void placeObstacleAtRowEqualToGridLengthDoesNotThrow() {
        // Regression test for an off-by-one bug where row == grid.length
        // was incorrectly treated as in-bounds and threw ArrayIndexOutOfBoundsException.
        assertDoesNotThrow(() -> board.placeObstacle(ROWS, 0));

        for (int j = 0; j < COLUMNS; j++) {
            assertEquals(".", board.grid[ROWS - 1][j].getSymbol());
        }
    }

    @Test
    void loadCustomMapPlacesObstaclesMatchingMapDesign() {
        board.loadCustomMap(1);

        // mapDesign1 row 4: ".....#." -> obstacle only at column 5
        assertTrue(board.grid[4][5].isObstacle());
        for (int j = 0; j < COLUMNS; j++) {
            if (j != 5) {
                assertFalse(board.grid[4][j].isObstacle());
            }
        }

        // mapDesign1 row 5: "##.#.#." -> obstacles at columns 0, 1, 3, 5
        assertTrue(board.grid[5][0].isObstacle());
        assertTrue(board.grid[5][1].isObstacle());
        assertFalse(board.grid[5][2].isObstacle());
        assertTrue(board.grid[5][3].isObstacle());
        assertFalse(board.grid[5][4].isObstacle());
        assertTrue(board.grid[5][5].isObstacle());
        assertFalse(board.grid[5][6].isObstacle());
    }

    @Test
    void loadCustomMapWithEmptyDesignPlacesNoObstacles() {
        board.loadCustomMap(0);

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                assertFalse(board.grid[i][j].isObstacle());
            }
        }
    }
}
