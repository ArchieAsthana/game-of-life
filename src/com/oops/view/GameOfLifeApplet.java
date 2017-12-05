package com.oops.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Game of life Applet.
 */
public class GameOfLifeApplet extends JApplet {
    /**
     * Game of life applet with row size 50.
     */
    private final int ROWS = 50;

    /**
     * Game of life applet with column size 50.
     */
    private final int COLS = 50;

    /**
     * Delay between each generation.
     */
    private final int TICK_DELAY = 250;

    /**
     * Game of life panel.
     */
    private GameOfLifePanel gameOfLifePanel;

    /**
     * Game Timer for game of life panel.
     */
    private Timer gameTimer;


    public void init() {
        Container container = getContentPane();
        JPanel gameContainerPanel = gameContainerPanel();
        container.add(gameContainerPanel);
        container.setSize(gameContainerPanel.getSize());
    }

    /**
     * Game of life container panel.
     *
     * @return - The next generation on game of life panel.
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
     * Contains buttons in a toolbar.
     *
     * @return - toolbar menu.
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
     * The next generation is not shown when stopButton is clicked.
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
     * Show next generation on button click.
     *
     * @return - next generation.
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

    /** Clears the screen of game of life panel.
     * @return - clear the game of life panel screen.
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