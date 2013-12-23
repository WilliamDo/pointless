package uk.co.ultimaspin.pointless;

import uk.co.ultimaspin.pointless.quiz.Question;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: william
 * Date: 23/12/2013
 * Time: 20:47
 * To change this template use File | Settings | File Templates.
 */
public class Quiz {

    private List<Question> questionDeck;

    private List<Question> discardedDeck;

    private Question currentQuestion;

    private static int x = 0;

    public Quiz(List<Question> questions) {
        this.questionDeck = questions;
    }

    public Question nextQuestion() {
        Question question = questionDeck.iterator().next();
        this.currentQuestion = question;
        return question;
    }

    public Question currentQuestion() {
        return this.currentQuestion;
    }

    public boolean isFinished() {
        return questionDeck.isEmpty();
    }

}
