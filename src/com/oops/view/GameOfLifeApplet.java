package com.oops.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameOfLifeApplet extends JApplet {
    private final int ROWS = 50;
    private final int COLS = 50;
    private final int TICK_DELAY = 250;
    private GameOfLifePanel gameOfLifePanel;
    private Timer gameTimer;

    /**
     * Holds all the buttons in a container on a panel.
     */
    public void init() {
        Container container = getContentPane();
        JPanel gameContainerPanel = gameContainerPanel();
        container.add(gameContainerPanel);
        container.setSize(gameContainerPanel.getSize());
    }

    /**
     * The panel has game of life panel and a game timer
     *
     * @return - panel
     */
    private JPanel gameContainerPanel() {
        JPanel panel = new JPanel();

        // Add the game of life panel to the applet.
        gameOfLifePanel = new GameOfLifePanel(ROWS, COLS);
        panel.add(gameOfLifePanel, BorderLayout.PAGE_END);

        // Add the game of life control panel to the applet.
        panel.add(toolBar(), BorderLayout.PAGE_END);

        gameTimer = new Timer(TICK_DELAY, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                gameOfLifePanel.showNextGeneration();
                gameOfLifePanel.updateUI();
            }
        });

        return panel;
    }

    /**
     * Contains all the buttons in a toolbar
     *
     * @return toolbar
     */
    private JToolBar toolBar() {
        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);

        toolBar.add(startButton());
        toolBar.add(stopButton());
        toolBar.add(nextGenButton());
        toolBar.add(clearButton());

        return toolBar;
    }

    /**
     * starts the game timer
     *
     * @return - start button
     */
    private JButton startButton() {
        JButton startButton = new JButton("Start");
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gameTimer.start();
            }
        });

        return startButton;
    }

    /**
     * Stop from creating the next generation
     *
     * @return - stop action
     */
    private JButton stopButton() {
        JButton stopButton = new JButton("Stop");
        stopButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gameTimer.stop();
            }
        });

        return stopButton;
    }

    /**
     * Show the next generation on click
     *
     * @return - next generation
     */
    private JButton nextGenButton() {
        JButton nextGenButton = new JButton("Next Generation");
        nextGenButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gameOfLifePanel.showNextGeneration();
                gameOfLifePanel.updateUI();
            }
        });

        return nextGenButton;
    }

    /** Clears the screen
     * @return - clear screen
     */
    private JButton clearButton() {
        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gameOfLifePanel.clear();
                gameOfLifePanel.updateUI();
            }
        });

        return clearButton;
    }
}