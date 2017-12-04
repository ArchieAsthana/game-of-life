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

    public void init() {
        Container container = getContentPane();
        JPanel gameContainerPanel = gameContainerPanel();
        container.add(gameContainerPanel);
        container.setSize(gameContainerPanel.getSize());
    }

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

    private JToolBar toolBar() {
        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);

        toolBar.add(startButton());
        toolBar.add(stopButton());
        toolBar.add(nextGenButton());
        toolBar.add(clearButton());

        return toolBar;
    }

    private JButton startButton() {
        JButton startButton = new JButton("Start");
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gameTimer.start();
            }
        });

        return startButton;
    }

    private JButton stopButton() {
        JButton stopButton = new JButton("Stop");
        stopButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gameTimer.stop();
            }
        });

        return stopButton;
    }

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