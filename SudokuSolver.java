import java.util.Scanner;

public class SudokuSolver {

    // Function to check if placing 'num' is safe at board[row][col]
    private static boolean isSafe(int[][] board, int row, int col, int num) {
        // Row check
        for (int x = 0; x < 9; x++) {
            if (board[row][x] == num) return false;
        }
        // Column check
        for (int x = 0; x < 9; x++) {
            if (board[x][col] == num) return false;
        }
        // 3x3 subgrid check
        int startRow = row - row % 3;
        int startCol = col - col % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i + startRow][j + startCol] == num) return false;
            }
        }
        return true;
    }

    // Main solver function (Backtracking)
    private static boolean solveSudoku(int[][] board, int row, int col) {
        if (row == 9 - 1 && col == 9) return true;
        if (col == 9) {
            row++;
            col = 0;
        }
        if (board[row][col] != 0) {
            return solveSudoku(board, row, col + 1);
        }
        for (int num = 1; num <= 9; num++) {
            if (isSafe(board, row, col, num)) {
                board[row][col] = num;
                if (solveSudoku(board, row, col + 1)) return true;
                board[row][col] = 0; // Backtrack
            }
        }
        return false;
    }

    // Utility to print Sudoku board
    private static void printBoard(int[][] board) {
        for (int r = 0; r < 9; r++) {
            for (int d = 0; d < 9; d++) {
                System.out.print(board[r][d] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[][] board = new int[9][9];

        System.out.println("Enter the Sudoku puzzle (9x9). Use 0 for empty cells:");

        // Taking input
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board[i][j] = sc.nextInt();
            }
        }

        // Solve and print
        if (solveSudoku(board, 0, 0)) {
            System.out.println("Solved Sudoku:");
            printBoard(board);
        } else {
            System.out.println("No solution exists.");
        }

        sc.close();
    }
}
