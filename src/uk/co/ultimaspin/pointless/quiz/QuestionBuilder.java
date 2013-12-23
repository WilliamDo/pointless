package uk.co.ultimaspin.pointless.quiz;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: william
 * Date: 23/12/2013
 * Time: 21:58
 * To change this template use File | Settings | File Templates.
 */
public class QuestionBuilder {

    private String question;
    private List<Answer> answers;

    private QuestionBuilder(String question) {
        this.question = question;
        this.answers = new ArrayList<Answer>();

    }

    public static QuestionBuilder newQuestion(String question) {
        return new QuestionBuilder(question);
    }

    public QuestionBuilder answer(String answer, int score) {
        answers.add(new Answer(answer, score));
        return this;
    }

    public Question build() {
        return new Question(question, answers);
    }

}
