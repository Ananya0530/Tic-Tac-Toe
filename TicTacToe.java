import java.util.*;

public class TicTacToe {

    static String[] board;
    static String turn;

    static final String PLAYER_X = "X";
    static final String PLAYER_O = "O";

    // Winning combinations
    static final int[][] WINNING_COMBINATIONS = {
        {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, // rows
        {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, // columns
        {0, 4, 8}, {2, 4, 6}             // diagonals
    };

    static String checkWinner() {
        for (int[] combination : WINNING_COMBINATIONS) {
            if (board[combination[0]].equals(board[combination[1]]) &&
                board[combination[1]].equals(board[combination[2]])) {
                if (board[combination[0]].equals(PLAYER_X)) {
                    return PLAYER_X;
                } else if (board[combination[0]].equals(PLAYER_O)) {
                    return PLAYER_O;
                }
            }
        }
        for (String cell : board) {
            if (cell.equals("1") || cell.equals("2") || cell.equals("3") ||
                cell.equals("4") || cell.equals("5") || cell.equals("6") ||
                cell.equals("7") || cell.equals("8") || cell.equals("9")) {
                return null; // Game continues
            }
        }
        return "draw"; // No more moves
    }

    static void printBoard() {
        System.out.println("|---|---|---|");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i * 3 + j] + " | ");
            }
            System.out.println("\n|---|---|---|");
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        board = new String[9];
        turn = PLAYER_X;
        String winner = null;

        for (int a = 0; a < 9; a++) {
            board[a] = String.valueOf(a + 1);
        }

        System.out.println("Welcome to 3x3 Tic Tac Toe.");
        printBoard();

        while (winner == null) {
            System.out.println(turn + "'s turn; enter a slot number to place " + turn + " in:");
            int numInput;

            // Loop until a valid input is received
            while (true) {
                try {
                    numInput = in.nextInt();
                    if (numInput < 1 || numInput > 9) {
                        System.out.println("Invalid input; re-enter slot number:");
                    } else if (!board[numInput - 1].equals(String.valueOf(numInput))) {
                        System.out.println("Slot already taken; re-enter slot number:");
                    } else {
                        break; // valid input
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input; re-enter slot number:");
                    in.next(); // clear invalid input
                }
            }

            board[numInput - 1] = turn;
            winner = checkWinner();
            printBoard();

            if (winner == null) {
                turn = turn.equals(PLAYER_X) ? PLAYER_O : PLAYER_X;
            }
        }

        if (winner.equals("draw")) {
            System.out.println("It's a draw! Thanks for playing.");
        } else {
            System.out.println("Congratulations! " + winner + " has won! Thanks for playing.");
        }
        in.close();
    }
}
