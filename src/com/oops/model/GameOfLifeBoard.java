/*
 * @ author Archana Srivastava, Harsh Raghuvansi, Surbhi Patel, CS:580 OOPS, Instructor: James Fuller
 * @ version December 1 2017
 */
package com.oops.model;

/**
 * Game of Life Board Class.
 */
public class GameOfLifeBoard {

    /**
     * Rows of the game of life board.
     */
    private int rows;

    /**
     * Columns of the game of life board.
     */
    private int cols;

    /**
     * An array of rows and columns of cell.
     */
    private int[][] grid;

    /**
     * Constructor for game of life board.
     *
     * @param rows - the number of rows in the game of life board. (Must be positive)
     * @param cols - the number of cols in the game of life board. (Must be positive)
     */
    public GameOfLifeBoard(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.grid = new int[rows][cols];
    }

    /**
     * Clears the game of life board.
     */
    public void clearBoard() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                grid[row][col] = 0;
            }
        }
    }

    /**
     * Advance the game of life to the next generation.
     *
     * @return the next generation GameOfLifeBoard
     */
    public GameOfLifeBoard nextGeneration() {
        GameOfLifeBoard nextBoard = new GameOfLifeBoard(rows, cols);

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                nextBoard.grid[row][col] = nextGenCellValue(row, col);
            }
        }

        return nextBoard;
    }

    /**
     * Toggles the cell at the given row and column.
     *
     * @param row - The row of the cell. (Must be positive)
     * @param col - The column of the cell. (Must be positive)
     */
    public void togglePopulated(int row, int col) {
        grid[row][col] = isPopulated(row, col) ? 0 : 1;
    }

    /**
     * Checks if the cell is populated at the given row and column.
     *
     * @param row - The row of the cell. (Must be positive)
     * @param col - The column of the cell. (Must be positive)
     * @return true if the cell is populated, otherwise false.
     */
    public boolean isPopulated(int row, int col) {
        return grid[row][col] == 1;
    }

    /**
     * Gives the next generation of the cell at given row and column.
     *
     * @param row - The row of the cell.
     * @param col - The column of the cell.
     * @return returns zero or one for the nextGenCellValue.
     */
    private int nextGenCellValue(int row, int col) {
        int neighbors = neighbors(row, col);
        if (isPopulated(row, col)) {
            return (neighbors < 2 || neighbors > 3) ? 0 : 1;
        } else {
            return (neighbors(row, col) == 3) ? 1 : 0;
        }
    }

    /**
     * Gives the adjacent neighbours for the given cell.
     *
     * @param row - The row of the cell.
     * @param col - The column of the cell.
     * @return - returns neighbor if the condition if true.
     */
    private int neighbors(int row, int col) {
        int neighbors = 0;

        if (row - 1 >= 0 && col - 1 >= 0) {
            neighbors += grid[row - 1][col - 1];
        }
        if (row - 1 >= 0) {
            neighbors += grid[row - 1][col];
        }
        if (row - 1 >= 0 && col + 1 < cols) {
            neighbors += grid[row - 1][col + 1];
        }

        if (col - 1 >= 0) {
            neighbors += grid[row][col - 1];
        }
        if (col + 1 < cols) {
            neighbors += grid[row][col + 1];
        }

        if (row + 1 < rows && col - 1 >= 0) {
            neighbors += grid[row + 1][col - 1];
        }
        if (row + 1 < rows) {
            neighbors += grid[row + 1][col];
        }
        if (row + 1 < rows && col + 1 < cols) {
            neighbors += grid[row + 1][col + 1];
        }

        return neighbors;
    }
}
