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

    GameOfLifePanel(int columns, int rows) {
        this.columns = columns;
        this.rows = rows;
        setLayout(new GridLayout(rows, columns));
        setupBoard();
        setupGrid();
    }

    void clear() {
        board.clearBoard();
        updateGrid();
    }

    void showNextGeneration() {
        board = board.nextGeneration();
        updateGrid();
    }

    private void setupGrid() {
        grid = new JButton[rows][columns];
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                grid[row][col] = cellButton(row, col);
                add(grid[row][col]);
            }
        }
    }

    private void updateGrid() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                setButtonBackground(grid[row][col], board.isPopulated(row, col));
            }
        }
    }

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

    private void setButtonBackground(JButton button, boolean isPopulated) {
        button.setBackground(isPopulated ? Color.orange : Color.gray);
    }
}
