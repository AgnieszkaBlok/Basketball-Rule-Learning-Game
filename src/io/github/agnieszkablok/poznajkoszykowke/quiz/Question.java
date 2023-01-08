package io.github.agnieszkablok.poznajkoszykowke.quiz;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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

    public Question(String filepath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filepath));
        questionBody = reader.readLine();
        correctAnswer = reader.readLine();
        incorrectAnswer = reader.readLine();
        reader.close();
    }
}
