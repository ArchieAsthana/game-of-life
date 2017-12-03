package com.oops.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameOfLifeApplet extends JApplet {

    private GameOfLifePanel gameOfLifePanel;

    public void init() {
        Container container = getContentPane();
        container.setLayout(new GridLayout(2, 1));

        // Add the game of life panel to the applet.
        gameOfLifePanel = new GameOfLifePanel(10, 10);
        container.add(gameOfLifePanel, BorderLayout.PAGE_END);

        // Add the game of life control panel to the applet.
        container.add(gameMenu(), BorderLayout.PAGE_END);
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
            }
        } );

        return startButton;
    }

    private JButton stopButton() {
        JButton stopButton = new JButton("Stop");
        stopButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        } );

        return stopButton;
    }

    private JButton nextGenButton() {
        JButton nextGenButton = new JButton("Next Generation");
        nextGenButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gameOfLifePanel.showNextGeneration();
                gameOfLifePanel.updateUI();
            }
        } );

        return nextGenButton;
    }

    private JButton clearButton() {
        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gameOfLifePanel.clear();
                gameOfLifePanel.updateUI();
            }
        } );

        return clearButton;
    }
}