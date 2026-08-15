package io.github.ardake26.connectfour;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GameController controller = new GameController();
            new GameDisplay(controller);
        });
    }
}
