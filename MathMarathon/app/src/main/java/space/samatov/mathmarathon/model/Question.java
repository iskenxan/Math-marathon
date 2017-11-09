package space.samatov.mathmarathon.model;

import java.util.ArrayList;

/**
 * Created by iskenxan on 11/8/17.
 */

public class Question {

    String question;
    ArrayList<String> answers=new ArrayList<>();
    int rightAnswerIndex;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public ArrayList<String> getAnswers() {
        return answers;
    }

    public void setAnswers(ArrayList<String> answers) {
        this.answers = answers;
    }

    public int getRightAnswerIndex() {
        return rightAnswerIndex;
    }

    public void setRightAnswerIndex(int rightAnswerIndex) {
        this.rightAnswerIndex = rightAnswerIndex;
    }
}
