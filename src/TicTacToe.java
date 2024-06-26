import java.util.Scanner;
import java.util.InputMismatchException;

public class TicTacToe {
    private static char[][] board = new char[3][3];
    private static char currentPlayer = 'X';

    public static void main(String[] args) {
        initializeBoard();
        System.out.println("Welcome to Tic-Tac-Toe!");
        System.out.println("Player X and Player O take turns to place their marks on the board.");
        printBoard();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            int row = -1, col = -1;
            boolean validInput = false;

            while (!validInput) {
                try {
                    System.out.println("Player " + currentPlayer + ", enter your move (row [1-3] column [1-3]):");
                    row = scanner.nextInt() - 1;
                    col = scanner.nextInt() - 1;

                    if (isValidMove(row, col)) {
                        validInput = true;
                    } else {
                        System.out.println("Invalid move. The cell is either occupied or out of range. Please try again.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter numbers between 1 and 3.");
                    scanner.next(); // Clear the invalid input
                }
            }

            board[row][col] = currentPlayer;
            printBoard();

            if (isWinner()) {
                System.out.println("Player " + currentPlayer + " wins!");
                break;
            } else if (isBoardFull()) {
                System.out.println("It's a draw!");
                break;
            }

            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        }

        scanner.close();
    }

    private static void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
    }

    private static void printBoard() {
        System.out.println("  1 2 3");
        System.out.println("  ------");
        for (int i = 0; i < 3; i++) {
            System.out.print((i + 1) + "| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("  ------");
    }

    private static boolean isValidMove(int row, int col) {
        return row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == '-';
    }

    private static boolean isWinner() {
        // Check rows, columns, and diagonals
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) {
                return true; // Row win
            }
            if (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer) {
                return true; // Column win
            }
        }
        if (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) {
            return true; // Diagonal win (top-left to bottom-right)
        }
        if (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer) {
            return true; // Diagonal win (top-right to bottom-left)
        }
        return false;
    }

    private static boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }
}
