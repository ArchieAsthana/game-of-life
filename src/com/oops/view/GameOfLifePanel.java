package com.oops.view;

import com.oops.model.GameOfLifeBoard;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOfLifePanel extends JPanel {
    private JButton[][] grid;
    private GameOfLifeBoard board;
    private int columns;
    private int rows;

    public GameOfLifePanel(int columns, int rows) {
        this.columns = columns;
        this.rows = rows;
        setLayout(new GridLayout(rows, columns));
        setupBoard();
        setupGrid();
    }

    public void clear() {
        removeAll();
        setupGrid();
        this.board.clearBoard();
    }

    public void showNextGeneration() {
        board = board.nextGeneration();
        removeAll();
        setupGrid();
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

    private void setupBoard() {
        board = new GameOfLifeBoard(rows, columns);
    }

    private JButton cellButton(int row, int col) {
        JButton button = new JButton();
        button.setBorder(new EmptyBorder(0, 0, 0, 0));
        button.setOpaque(true);
        button.setBackground(board.isPopulated(row, col) ? Color.YELLOW : Color.WHITE);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                board.togglePopulated(row, col);
                button.setBackground(board.isPopulated(row, col) ? Color.YELLOW : Color.WHITE);
            }
        });

        return button;
    }
}
