package com.oops.model;

public class GameOfLifeBoard {
    private int rows;
    private int cols;
    private int[][] grid;

    public GameOfLifeBoard(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.grid = new int[rows][cols];
    }

    public void clearBoard() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                grid[row][col] = 0;
            }
        }
    }

    public GameOfLifeBoard nextGeneration() {
        GameOfLifeBoard nextBoard = new GameOfLifeBoard(rows, cols);

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int status = nextGenStatus(row, col);
                nextBoard.setStatus(row, col, status);
            }
        }

        return nextBoard;
    }

    public void setStatus(int row, int col, int value) {
        grid[row][col] = value;
    }

    public void togglePopulated(int row, int col) {
        grid[row][col] = isPopulated(row, col) ? 0 : 1;
    }

    public boolean isPopulated(int row, int col) {
        return grid[row][col] == 1;
    }

    private int nextGenStatus(int row, int col) {
        int neighbors = neighbors(row, col);
        if (isPopulated(row, col)) {
            return (neighbors < 2 || neighbors > 3) ? 0 : 1;
        } else {
            return (neighbors(row, col) == 3) ? 1 : 0;
        }
    }

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

//        for (int i = row - 1; i <= row + 1; i++) {
//            for (int j = col - 1; j <= col + 1; j++) {
//                if (i >= 0 && i < cols && j >= 0 && j < cols && (i != row && j != col)) {
//                    neighbors += grid[i][j];
//                }
//            }
//        }

        return neighbors;
    }
}
