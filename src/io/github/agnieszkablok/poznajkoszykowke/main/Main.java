package io.github.agnieszkablok.poznajkoszykowke.main;

import javax.swing.*;

public class Main {

    public static void main(String[] args){
        JFrame menu = new JFrame();
        boolean newGame = GameMenu.showGameMenu(menu);

        if(!newGame){
            menu.dispose();
            return;
        }


        JFrame window = new JFrame();

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Poznaj koszykówkę");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.setupGame();

        gamePanel.startGameThread();
    }
}
