import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GameDisplay {
    private JFrame jFrame = new JFrame();
    private JPanel jPanel = new JPanel();
    private JButton[][] buttons = new JButton[Constants.BoardConstants.NUMBER_OF_ROWS][Constants.BoardConstants.NUMBER_OF_COLUMNS];
    private GameManager game;
    private Board board = Container.BoardContainer.board;

    public GameDisplay(GameManager _game) {
        this.game = _game;

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
                        board.dropToken(columnIndex, game.getCurrentSymbol());
                        updateBoardVisuals();
                        System.out.println(columnIndex);
                        game.switchPlayer();
                    }
                });
                jPanel.add(cellButton);
            }
        }

        jFrame.add(jPanel);
        jFrame.setSize(
                Constants.displayConstants.SCREEN_DIMENSION_SIZES[0],
                Constants.displayConstants.SCREEN_DIMENSION_SIZES[1]);
        // jFrame.pack();
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }

    public void updateBoardVisuals() {
        for (int i = 0; i < Constants.BoardConstants.NUMBER_OF_ROWS; i++) {
            for (int j = 0; j < Constants.BoardConstants.NUMBER_OF_COLUMNS; j++) {
                JButton currentCell = buttons[i][j];
                currentCell.setText(board.grid[i][j].getSymbol());

                // makes all the buttons same color, correcct it
                // if (game.getCurrentSymbol().equals("X")) {
                // currentCell.setBackground(Color.RED);
                // } else if (game.getCurrentSymbol().equals("O")) {
                // currentCell.setBackground(Color.YELLOW);
                // } else {
                // currentCell.setBackground(Color.CYAN);
                // }
            }
        }
    }
}
