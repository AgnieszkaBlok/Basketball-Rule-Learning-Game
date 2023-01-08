package io.github.agnieszkablok.poznajkoszykowke.quiz;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * A class that is managing questions used in the game.
 */
public class QuestionManager {

    private static final String QUESTION_FILE_EXT = "question";
    private final List<Question> playerQuestions;
    private final List<Question> refereeQuestions;

    /**
     * Checks if file extension is ".question"
     * @param file file to be checked
     * @return is file extension ".question"
     */
    private boolean checkIfIsAQuestionFile(File file){
        String filename = file.getName();
        String[] tokens = filename.split("\\.");

        if(tokens.length < 2)
            return false;
        return tokens[tokens.length - 1].equals(QUESTION_FILE_EXT);
    }

    /**
     * Returns a list of questions loaded from the file from the directory provided
     * @param directory directory of the questions
     * @return list of Question objects
     */
    private List<Question> getQuestionsFromDirectory(File directory){
        return Arrays.stream(Objects.requireNonNull(directory.listFiles()))
                .filter(this::checkIfIsAQuestionFile)
                .map(File::getName)
                .map(file -> {
                    try{
                        return new Question(directory + "/" + file);
                    } catch (IOException e) {
                        return null;
                    }
                }).filter(Objects::nonNull)
                .collect(Collectors.toList());
    }


    /**
     * Constructs a Question manager
     * @param pathToPlayerQuestions path to folder with player questions
     * @param pathToRefereeQuestions path to folder with referee questions
     * @throws IOException if the path points to the file or the specified folder is non-existent
     */
    public QuestionManager(String pathToPlayerQuestions, String pathToRefereeQuestions) throws IOException {
        File playerQuestionsDir = new File(pathToPlayerQuestions);
        File refereeQuestionsDir = new File(pathToRefereeQuestions);

        if(!playerQuestionsDir.exists() || !playerQuestionsDir.isDirectory()){
            throw new IOException("Player questions directory " + pathToPlayerQuestions + " does not exists!");
        }
        if(!refereeQuestionsDir.exists() || !refereeQuestionsDir.isDirectory()){
            throw new IOException("Referee questions directory " + pathToRefereeQuestions + " does not exists!");
        }

        playerQuestions = getQuestionsFromDirectory(playerQuestionsDir);
        Collections.shuffle(playerQuestions);

        refereeQuestions = getQuestionsFromDirectory(refereeQuestionsDir);
        Collections.shuffle(refereeQuestions);
    }


    /**
     * Returns a single player question from the list and removes it
     * @return player question
     */
    public Question getPlayerQuestion(){
        Question question = playerQuestions.get(playerQuestions.size() - 1);
        playerQuestions.remove(playerQuestions.size() - 1);
        return question;
    }

    /**
     * Returns a single referee question from the list and removes it
     * @return referee question
     */
    public Question getRefereeQuestion(){
        Question question = refereeQuestions.get(refereeQuestions.size() - 1);
        refereeQuestions.remove(refereeQuestions.size() - 1);
        return question;
    }

}
