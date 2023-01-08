package io.github.agnieszkablok.poznajkoszykowke.main;

import javax.swing.*;
import java.awt.*;

public class GameMenu {

    public static boolean showGameMenu(Component component){
        String[] options = { "Nowa gra", "Wyjście" };
        int chosenOption = JOptionPane.showOptionDialog(component,
                "Poznaj koszykówkę",
                "Poznaj koszykówkę",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                null);

        return chosenOption == 0;

    }
}
