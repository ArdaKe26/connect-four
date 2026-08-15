package io.github.ardake26.connectfour;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GameDisplay {
    private JFrame jFrame = new JFrame();
    private JPanel jPanel = new JPanel();
    private JButton[][] buttons = new JButton[Constants.BoardConstants.NUMBER_OF_ROWS][Constants.BoardConstants.NUMBER_OF_COLUMNS];
    private GameController game;
    private Board board;

    public GameDisplay(GameController _game) {
        this.game = _game;
        game.loadMap(promptForMapSelection());
        this.board = game.getBoard();

        jPanel.setLayout(
                new GridLayout(
                        Constants.BoardConstants.NUMBER_OF_ROWS,
                        Constants.BoardConstants.NUMBER_OF_COLUMNS));

        for (int i = 0; i < Constants.BoardConstants.NUMBER_OF_ROWS; i++) {
            for (int j = 0; j < Constants.BoardConstants.NUMBER_OF_COLUMNS; j++) {
                JButton cellButton = new JButton();
                buttons[i][j] = cellButton;
                int columnIndex = j;
                cellButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        handleColumnClick(columnIndex);
                    }
                });
                jPanel.add(cellButton);
            }
        }

        jFrame.add(jPanel);
        jFrame.setSize(
                Constants.DisplayConstants.SCREEN_DIMENSION_SIZES[0],
                Constants.DisplayConstants.SCREEN_DIMENSION_SIZES[1]);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        updateBoardVisuals();
        jFrame.setVisible(true);
    }

    private void handleColumnClick(int columnIndex) {
        Player mover = game.getCurrentPlayer();

        if (!board.dropToken(columnIndex, mover.getSymbol())) {
            return;
        }

        updateBoardVisuals();
        disableColumnIfFull(columnIndex);

        if (board.checkWin(mover.getSymbol())) {
            endRound(mover.getName() + " wins!");
        } else if (board.isBoardFull()) {
            endRound("The game was a draw due to board being full.");
        } else {
            game.switchPlayer();
        }
    }

    private void disableColumnIfFull(int columnIndex) {
        if (!board.grid[0][columnIndex].getSymbol().equals(".")) {
            for (int i = 0; i < Constants.BoardConstants.NUMBER_OF_ROWS; i++) {
                buttons[i][columnIndex].setEnabled(false);
            }
        }
    }

    private void endRound(String message) {
        setAllButtonsEnabled(false);

        int choice = JOptionPane.showConfirmDialog(
                jFrame,
                message + "\nPlay again?",
                "Game Over",
                JOptionPane.YES_NO_OPTION);

        if (choice == JOptionPane.YES_OPTION) {
            game.reset();
            board = game.getBoard();
            setAllButtonsEnabled(true);
            updateBoardVisuals();
        }
    }

    private void setAllButtonsEnabled(boolean enabled) {
        for (int i = 0; i < Constants.BoardConstants.NUMBER_OF_ROWS; i++) {
            for (int j = 0; j < Constants.BoardConstants.NUMBER_OF_COLUMNS; j++) {
                buttons[i][j].setEnabled(enabled);
            }
        }
    }

    private int promptForMapSelection() {
        while (true) {
            String input = JOptionPane.showInputDialog(
                    null,
                    "Please select the map you want to play in (0-4)",
                    "Select Map",
                    JOptionPane.QUESTION_MESSAGE);

            if (input == null) {
                System.exit(0);
            }

            try {
                int mapIndex = Integer.parseInt(input.trim());
                if (mapIndex >= 0 && mapIndex <= 4) {
                    return mapIndex;
                }
            } catch (NumberFormatException e) {
                // fall through to re-prompt
            }

            JOptionPane.showMessageDialog(null, "Please enter a valid map number (0-4).");
        }
    }

    public void updateBoardVisuals() {
        for (int i = 0; i < Constants.BoardConstants.NUMBER_OF_ROWS; i++) {
            for (int j = 0; j < Constants.BoardConstants.NUMBER_OF_COLUMNS; j++) {
                JButton currentCell = buttons[i][j];
                String symbol = board.grid[i][j].getSymbol();
                currentCell.setText(symbol);

                if (symbol.equals("X")) {
                    currentCell.setBackground(Color.RED);
                } else if (symbol.equals("O")) {
                    currentCell.setBackground(Color.YELLOW);
                } else if (symbol.equals("#")) {
                    currentCell.setBackground(Color.GREEN);
                } else {
                    currentCell.setBackground(Color.CYAN);
                }
            }
        }
    }
}
