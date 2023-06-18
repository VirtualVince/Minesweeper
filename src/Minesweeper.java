import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Minesweeper {
    private Cell[][] board;
    private int rows;
    private int columns;
    private int numMines;
    private boolean gameover;
    private boolean won;

    public void initializeGame(int startRow, int startColumn) {
        // Create the game board
        board = new Cell[rows][columns];
        for (int i = 0; i < rows; i++) {
            Arrays.fill(board[i], new Cell());
        }

        // Randomly place mines
        Random random = new Random();
        int minesPlaced = 0;
        while (minesPlaced < numMines) {
            int row = random.nextInt(rows);
            int column = random.nextInt(columns);
            if (!board[row][column].isMine && (row != startRow || column != startColumn)) {
                board[row][column].isMine = true;
                minesPlaced++;
            }
        }

        // Calculate adjacent mine counts for non-mine cells
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                if (!board[row][column].isMine) {
                    board[row][column].adjacentMines = countAdjacentMines(row, column);
                }
            }
        }
    }

    public int countAdjacentMines(int row, int column) {
        int count = 0;
        for (int r = row - 1; r <= row + 1; r++) {
            for (int c = column - 1; c <= column + 1; c++) {
                if ((r != row || c != column) && isValidCell(r, c) && board[r][c].isMine) {
                    count++;
                }
            }
        }
        return count;
    }

    public boolean isValidCell(int row, int column) {
        return row >= 0 && row < rows && column >= 0 && column < columns;
    }

    public void revealCell(int row, int column) {
        Cell cell = board[row][column];
        if (cell.isRevealed || cell.isFlagged) {
            return;
        }

        cell.isRevealed = true;

        if (cell.isMine) {
            gameover = true;
        } else if (cell.adjacentMines == 0) {
            for (int r = row - 1; r <= row + 1; r++) {
                for (int c = column - 1; c <= column + 1; c++) {
                    if (isValidCell(r, c)) {
                        revealCell(r, c);
                    }
                }
            }
        }

        checkWin();
    }

    public void toggleFlag(int row, int column) {
        Cell cell = board[row][column];
        if (!cell.isRevealed) {
            cell.isFlagged = !cell.isFlagged;
        }
    }

    public void checkWin() {
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                Cell cell = board[row][column];
                if (!cell.isMine && !cell.isRevealed) {
                    return;
                }
            }
        }
        gameover = true;
        won = true;
    }

    public void displayBoard() {
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                Cell cell = board[row][column];
                if (cell.isRevealed) {
                    if (cell.isMine) {
                        System.out.print("* ");
                    } else if (cell.adjacentMines > 0) {
                        System.out.print(cell.adjacentMines + " ");
                    } else {
                        System.out.print("  ");
                    }
                } else {
                    if (cell.isFlagged) {
                        System.out.print("F ");
                    } else {
                        System.out.print("# ");
                    }
                }
            }
            System.out.println();
        }
    }

    public void gameLoop() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Minesweeper!");
        System.out.println("Instructions:");
        System.out.println("Enter row, column coordinates and 'r' to reveal a cell (e.g., 1 2 r).");
        System.out.println("Enter row, column coordinates and 'f' to flag/unflag a cell (e.g., 1 2 f).");
        System.out.println("Let's begin!\n");

        while (!gameover) {
            displayBoard();

            System.out.print("Enter your move: ");
            String inputStr = scanner.nextLine();
            String[] inputs = inputStr.split(" ");
            if (inputs.length != 3) {
                System.out.println("Invalid input. Please try again.");
                continue;
            }

            try {
                int row = Integer.parseInt(inputs[0]) - 1;
                int column = Integer.parseInt(inputs[1]) - 1;
                String action = inputs[2].toLowerCase();

                if (!isValidCell(row, column)) {
                    System.out.println("Invalid cell coordinates. Please try again.");
                    continue;
                }

                if (action.equals("r")) {
                    revealCell(row, column);
                } else if (action.equals("f")) {
                    toggleFlag(row, column);
                } else {
                    System.out.println("Invalid action. Please try again.");
                    continue;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please try again.");
            }
        }

        displayBoard();
        if (won) {
            System.out.println("Congratulations! You won!");
        } else {
            System.out.println("Game over. You lost!");
        }

        scanner.close();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of rows: ");
        rows = scanner.nextInt();
        System.out.print("Enter the number of columns: ");
        columns = scanner.nextInt();
        System.out.print("Enter the number of mines: ");
        numMines = scanner.nextInt();

        initializeGame(0, 0);
        gameLoop();
        scanner.close();
    }

    public static void main(String[] args) {
        Minesweeper minesweeper = new Minesweeper();
        minesweeper.start();
    }
}

class Cell {
    boolean isMine;
    boolean isRevealed;
    boolean isFlagged;
    int adjacentMines;

    Cell() {
        isMine = false;
        isRevealed = false;
        isFlagged = false;
        adjacentMines = 0;
    }
}