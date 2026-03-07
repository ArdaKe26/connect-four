Connect Four Game

A robust, object-oriented implementation of the classic Connect Four strategy game. This project demonstrates core Java concepts, including multi-dimensional array manipulation, turn-based game logic, and win-condition algorithms.


Technical Implementation:
    This version of Connect Four is built with a focus on Clean Code and Modular Design.

Core Components
    Board Logic: Managed via a 2D primitive array char[][] or int[][] representing the $6 \times 7$ grid.
    
    Win Detection: A specialized algorithm that scans for four consecutive markers in four directions

    Input Validation: Ensures players cannot drop discs into full columns or select out-of-bounds indices.

Getting Started
    Prerequisites
        Java Development Kit (JDK) 8 or higher
        An IDE (IntelliJ IDEA, Eclipse, or VS Code) or Terminal

    Installation & Running
        1. Clone the repo:
            '''bash
            git clone https://github.com/ArdaKe26/connect-four.git
            '''

        2. Navigate to the source folder:
            '''bash
            cd connect-four
            '''
        
        3. Compile the java files
            '''bash
            javac Main.java
            '''

        4. Run the application
            '''bsh
            java Main
            '''

How to Play
    1. The game starts with an empty 6 x 7 vertical grid.
    2. Player 1 (Red/X) and Player 2 (Yellow/O) alternate turns.
    3. On your turn, enter the column number (typically 1-7) where you wish to drop your disc.
    4. The disc falls to the lowest available slot in that column.
    5. Winning: Be the first to connect four discs in any direction!

Code Structure
    Main.java: The entry point of the application.
    Board.java: Handles the grid state, rendering the board to the console/UI, and checking for a full board.
    GameLogic.java: Contains the "referee" logic—switching players and validating win conditions.
    Player.java: (Optional) Defines player attributes and move-entry methods.


License
    This project is licensed under the MIT License - see the LICENSE file for details.

Created by ArdaKe26