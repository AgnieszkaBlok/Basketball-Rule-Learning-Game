package io.github.agnieszkablok.poznajkoszykowke.quiz;

import javax.swing.*;
import java.awt.Component;

public class QuestionWindow {

    private static final String PLAYER_QUESTION_HEADER = "Pytanie o koszykówce";
    private static final String REFEREE_QUESTION_HEADER = "Pytanie o przepisy koszykówki";

    /**
     * Shows a referee question window to user. Waits for user input and returns if the user input was correct
     * @param component parent frame of the window
     * @param playerQuestion question to be displayed
     * @return true if user answered correctly
     */
    public static boolean showPlayerQuestion(Component component, Question playerQuestion) {
        return showQuestionDialog(component, PLAYER_QUESTION_HEADER, playerQuestion);
    }

    /**
     * Shows a referee question window to user. Waits for user input and returns if the user input was correct
     * @param component parent component of the window
     * @param refereeQuestion question to be displayed
     * @return true if user answered correctly
     */
    public static boolean showRefereeQuestion(Component component, Question refereeQuestion) {
        return showQuestionDialog(component, REFEREE_QUESTION_HEADER, refereeQuestion);
    }


    private static String[] getAnswersInRandomOrder(Question question){
        if(Math.random() > 0.5) return new String[] {question.correctAnswer, question.incorrectAnswer};
        return new String[] {question.incorrectAnswer, question.correctAnswer};
    }
    private static boolean showQuestionDialog(Component component, String windowHeader, Question question) {
        String[] options = getAnswersInRandomOrder(question);

        int chosenOption = JOptionPane.showOptionDialog(component,
                question.getQuestionBody(),
                windowHeader,
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                null);

        if(chosenOption != 0 && chosenOption != 1) return false;

        return question.checkIfCorrect(options[chosenOption]);
    }
}
