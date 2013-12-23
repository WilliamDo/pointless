package uk.co.ultimaspin.pointless.quiz;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: william
 * Date: 23/12/2013
 * Time: 18:21
 * To change this template use File | Settings | File Templates.
 */
public class Question {

    private final String question;

    private static int counter;

    private List<Answer> answers;

    public Question(String question) {
        this.question = question;
        this.answers = new ArrayList<Answer>();
        answers.add(new Answer("Answer " + ++counter, 100));
    }

    public Question(String question, List<Answer> answers) {
        this.question = question;
        this.answers = answers;
    }


    public String getQuestion() {
        return this.question;
    }

    public List<Answer> getAnswers() {
        return this.answers;
    }



}
