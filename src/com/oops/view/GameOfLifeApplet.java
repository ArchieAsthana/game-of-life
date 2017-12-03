package com.oops.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameOfLifeApplet extends JApplet {

    private int TICK_DELAY = 500;
    private GameOfLifePanel gameOfLifePanel;
    private Timer gameTimer;

    public void init() {
        Container container = getContentPane();
        container.setLayout(new GridLayout(2, 1));

        // Add the game of life panel to the applet.
        gameOfLifePanel = new GameOfLifePanel(50, 50);
        container.add(gameOfLifePanel, BorderLayout.PAGE_END);

        // Add the game of life control panel to the applet.
        container.add(gameMenu(), BorderLayout.PAGE_END);

        gameTimer = new Timer(TICK_DELAY, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                gameOfLifePanel.showNextGeneration();
                gameOfLifePanel.updateUI();
            }
        });
    }

    private JPanel gameMenu() {
        JPanel menu = new JPanel();
        menu.add(startButton());
        menu.add(stopButton());
        menu.add(nextGenButton());
        menu.add(clearButton());

        return menu;
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