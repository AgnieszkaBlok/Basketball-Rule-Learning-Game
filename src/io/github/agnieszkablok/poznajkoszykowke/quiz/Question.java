package io.github.agnieszkablok.poznajkoszykowke.quiz;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *  A class representing a question. Reads a question from .question file
 */
public class Question {

    String questionBody;
    String correctAnswer;
    String incorrectAnswer;

    public String getQuestionBody() {
        return questionBody;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public String getIncorrectAnswer() {
        return incorrectAnswer;
    }

    public boolean checkIfCorrect(String answer){

        return answer.equals(correctAnswer);
    }


    /**
     * Read a question from .question file
     * @param filepath filepath to file
     * @throws IOException if I/O problems are encountered while reading file
     */
    public Question(String filepath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filepath));
        questionBody = reader.readLine();
        correctAnswer = reader.readLine();
        incorrectAnswer = reader.readLine();
        reader.close();
    }


    @Override
    public String toString() {
        return "Question{" +
                "questionBody='" + questionBody + '\'' +
                ", correctAnswer='" + correctAnswer + '\'' +
                ", incorrectAnswer='" + incorrectAnswer + '\'' +
                '}';
    }
}
