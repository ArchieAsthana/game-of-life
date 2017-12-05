package com.oops.view;

import com.oops.model.GameOfLifeBoard;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class GameOfLifePanel extends JPanel {
    private JButton[][] grid;
    private GameOfLifeBoard board;
    private int columns;
    private int rows;

    /**
     * Constructor of game of life panel
     * @param columns - returns columns
     * @param rows - rows
     */
    GameOfLifePanel(int columns, int rows) {
        this.columns = columns;
        this.rows = rows;
        setLayout(new GridLayout(rows, columns));
        setupBoard();
        setupGrid();
    }

    /**
     * Clear board and upgrades the grid
     */
    void clear() {
        board.clearBoard();
        updateGrid();
    }

    /**
     * Displays the next generation on the board
     */
    void showNextGeneration() {
        board = board.nextGeneration();
        updateGrid();
    }

    /**
     * set the board with cell buttons
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
     * Change the cell color when populated
     */
    private void updateGrid() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                setButtonBackground(grid[row][col], board.isPopulated(row, col));
            }
        }
    }

    /**
     *
     */
    private void setupBoard() {
        board = new GameOfLifeBoard(rows, columns);
    }

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
     * If Cell is Populated change cell color
     * @param button - cell Button
     * @param isPopulated -
     */
    private void setButtonBackground(JButton button, boolean isPopulated) {
        button.setBackground(isPopulated ? Color.orange : Color.gray);
    }
}
