package uk.co.ultimaspin.pointless;

import uk.co.ultimaspin.pointless.quiz.Question;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: william
 * Date: 23/12/2013
 * Time: 20:47
 * To change this template use File | Settings | File Templates.
 */
public class Quiz {

    private List<Question> questionDeck;

    private Iterator<Question> iterator;

    private List<Question> discardedDeck;

    private Question currentQuestion;

    private static int x = 0;

    public Quiz(List<Question> questions) {
        this.questionDeck = randomizeQuestions(questions);
        iterator = questionDeck.iterator();
    }

    public Question nextQuestion() {
        if (iterator.hasNext()) {
            Question question = iterator.next();
            this.currentQuestion = question;
        }
        return this.currentQuestion;
    }

    public Question currentQuestion() {
        return this.currentQuestion;
    }

    public boolean isFinished() {
        return questionDeck.isEmpty();
    }

    private List<Question> randomizeQuestions(List<Question> questions) {
        List<Question> result = new ArrayList<Question>();

        while (!questions.isEmpty()) {
            Random random = new Random();
            int i = random.nextInt(questions.size());
            result.add(questions.remove(i));
        }

        return result;
    }

}
