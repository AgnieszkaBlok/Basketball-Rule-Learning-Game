package io.github.agnieszkablok.poznajkoszykowke.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class GameMenu {
    private static JFrame menuWindow;

    private static void startNewGame(){
        JFrame gameWindow = new JFrame();

        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWindow.setResizable(false);
        gameWindow.setTitle("Poznaj koszykówkę");

        GamePanel gamePanel = new GamePanel();
        gameWindow.add(gamePanel);

        gameWindow.pack();

        gameWindow.setLocationRelativeTo(null);
        gameWindow.setVisible(true);

        gamePanel.setupGame();

        gamePanel.startGameThread();
    }


    private static void handleNewGameButtonPress(ActionEvent e){
        startNewGame();
        menuWindow.dispose();
    }

    private static void handleExitButtonPress(ActionEvent e){
        menuWindow.setVisible(false);
        menuWindow.dispose();
    }

    private static void handleAboutGameButtonPress(ActionEvent e){
        JOptionPane.showMessageDialog(menuWindow, "  Gra polega na dotarciu do skrzyni ze skarbem gdzie znajdują się zaginone fragmenty przepisów do koszykówki\n " +
                " Jednak na przeszkodzie do skarbu stoją Ci bramy. Żeby je otworzyć musisz zebrać klucze.\n" +
                " Czasami leżą one na Twojej drodze, a czasami dopiero po poprawnej odpowiedzi na pytanie,\n ukryte pod piłką do koszykówki lub gwizdkiem, możesz je otrzymać.\n" +
                "                                                                                 Powodzenia" +
                "\n");
    }


    public static void showGameMenu(){
        menuWindow = new JFrame("Poznaj koszykówkę");

        JLabel gameTitle = new JLabel("Poznaj koszykówkę");
        gameTitle.setFont(new Font("Bahnschrift", Font.BOLD, 23));
        gameTitle.setBounds(50, 50, 300, 50);
        gameTitle.setHorizontalAlignment(SwingConstants.CENTER);
        menuWindow.add(gameTitle);

        JButton newGameButton = new JButton("Nowa gra");
        gameTitle.setFont(new Font("Bahnschrift", Font. PLAIN, 23));
        newGameButton.setBounds(100,125,200,50);
        menuWindow.add(newGameButton);
        newGameButton.addActionListener(GameMenu::handleNewGameButtonPress);

        JButton aboutGameButton = new JButton("O grze");
        gameTitle.setFont(new Font("Bahnschrift", Font.PLAIN, 23));
        aboutGameButton.setBounds(100, 200, 200, 50);
        menuWindow.add(aboutGameButton);
        aboutGameButton.addActionListener(GameMenu::handleAboutGameButtonPress);

        JButton exitButton = new JButton("Wyjście");
        gameTitle.setFont(new Font("Bahnschrift", Font.PLAIN, 23));
        exitButton.setBounds(100, 275, 200, 50);
        menuWindow.add(exitButton);
        exitButton.addActionListener(GameMenu::handleExitButtonPress);

        menuWindow.setSize(400,400);
        menuWindow.setResizable(false);
        menuWindow.setLayout(null);
        menuWindow.setVisible(true);
    }

    /*public static boolean showGameMenu(Component component) {
        String[] options = {"Nowa gra", "Wyjście"};
        int chosenOption = JOptionPane.showOptionDialog(component,
                "Poznaj koszykówkę",
                "Poznaj koszykówkę",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                null);

        return chosenOption == 0;

    }*/
}
