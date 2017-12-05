package com.oops.view;

import com.oops.model.GameOfLifeBoard;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Game of life panel.
 */
class GameOfLifePanel extends JPanel {
    /**
     * Array of buttons
     */
    private JButton[][] grid;
    /**
     *
     */
    private GameOfLifeBoard board;

    /**
     * Columns of game of life panel.
     */
    private int columns;

    /**
     * Rows of game of life panel.
     */
    private int rows;

    /**
     * Constructor of game of life panel.
     * @param columns - The number of rows in game of life panel.
     * @param rows - The number of cloumns in game of life panel.
     */
    GameOfLifePanel(int columns, int rows) {
        this.columns = columns;
        this.rows = rows;
        setLayout(new GridLayout(rows, columns));
        setupBoard();
        setupGrid();
    }

    /**
     * Clears the game of life board and updates grid.
     */
    void clear() {
        board.clearBoard();
        updateGrid();
    }

    /**
     * Displays the next generation on the board.
     */
    void showNextGeneration() {
        board = board.nextGeneration();
        updateGrid();
    }

    /**
     * set the board with rows and column of cell buttons
     */
    private void setupGrid() {
        grid = new JButton[rows][columns];
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                grid[row][col] = cellButton(row, col);
                add(grid[row][col]);
            }
        }
    }

    /**
     * Update grid, change color when the cell is populated.
     */
    private void updateGrid() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                setButtonBackground(grid[row][col], board.isPopulated(row, col));
            }
        }
    }

    /**
     * Advance to game of life panel to set up board.
     */
    private void setupBoard() {
        board = new GameOfLifeBoard(rows, columns);
    }

    /**
     * Buttons for the panel with row and column.
     * @param row - The row of the cell.
     * @param col - The column of the cell.
     * @return - The cell buttons.
     */
    private JButton cellButton(final int row, final int col) {
        final JButton button = new JButton();
        button.setBorder(new EmptyBorder(4, 4, 4, 4));
        button.setOpaque(true);
        setButtonBackground(button, board.isPopulated(row, col));

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                board.togglePopulated(row, col);
                setButtonBackground(button, board.isPopulated(row, col));
            }
        });

        return button;
    }

    /**
     * Change button background color if cell populated.
     * @param button - The cell Button in the panel.
     * @param isPopulated - true if cell populated and change color, other wise false.
     */
    private void setButtonBackground(JButton button, boolean isPopulated) {
        button.setBackground(isPopulated ? Color.orange : Color.gray);
    }
}
