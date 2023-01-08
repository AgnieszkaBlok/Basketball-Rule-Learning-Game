import io.github.agnieszkablok.poznajkoszykowke.quiz.Question;

import java.io.IOException;

public class ReadQuestionsTest {
    public static void main(String[] args) throws IOException {
        Question question = new Question("res/questions/questionsPlayer/b1.question");

        System.out.println("Pytanie: " + question.getQuestionBody());
        System.out.println("Poprawna odpowiedź: " + question.getCorrectAnswer());
        System.out.println("Niepoprawna odpowiedź: " + question.getIncorrectAnswer());
    }
}
